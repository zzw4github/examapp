package com.infosea.examApp.controller;

import com.google.common.base.Strings;
import com.infosea.examApp.dao.PageUtil;
import com.infosea.examApp.pojo.*;
import com.infosea.examApp.service.*;


import com.infosea.examApp.vo.PageBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by infosea on 2016/4/19.
 */
@Controller
public class MainController {
    @Autowired
    ExamService examService;
    @Autowired
    TypeService typeService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionTypeService questionTypeService;
    @Autowired
    OptionService optionService;
    @Autowired
    UserService userService;
    @Autowired
    PageUtil<Question> pageUtil;
    @Autowired
    CommonService commonService;
    @Autowired
    TestPaperService testPaperService;
    @Autowired
    AnswerService answerService;

    Map<String, String> answerMap = new HashMap<>();//answerMap

    /**
     * 读者登录后返回一套试卷
     * 从请求中获取
     * 试卷定义ID
     * 考试ID
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main1(@ModelAttribute("user") User user, HttpServletRequest request) {
        user = userService.findUser(user);
        String testPaperDefineId = request.getParameter("testPaperDefineID");
        String examId = request.getParameter("examId");

        if (Strings.isNullOrEmpty(examId)) {
            examId = "1";
        }
        if (Strings.isNullOrEmpty(testPaperDefineId)) {
            testPaperDefineId = "1";
        }
        Exam exam = examService.findByID(Integer.parseInt(examId));
        TestPaper testPaper = testPaperService.produceTestPaper(user, Integer.parseInt(testPaperDefineId) ,exam);
        long testPaperId = testPaper.getId();
        List<Subject> subjects = testPaper.getSubjects();

        //返回 试题 第一条数据
        PageBean<Subject> pageBean = new PageBean<>(subjects);
        pageBean.setCurPage(1);

        request.getSession().setAttribute("answerMap", answerMap);
        request.getSession().setAttribute("testPaper", testPaper);
        request.getSession().setAttribute("pageBean", pageBean);
        return "main";
    }

    /**
     * 单个问题提交时，保存到session中，并返回下一个问题
     * @param request
     * @return
     * @TODO 将答案保存到数据库
     */
    @RequestMapping(value = "/main/single", method = RequestMethod.POST)
    public String page(HttpServletRequest request) {
        String qids = (String) request.getSession().getAttribute("qids");
//      request.getParameterMap()中将包含你表单里面所有input标签的数据
//      点提交按钮后提交
        Map map1 = request.getParameterMap();

        Set<String> key = map1.keySet();
        String spage = "";

        for (Iterator it = key.iterator(); it.hasNext(); ) {
            String s = (String) it.next();
            if (s.contains("-")) {
                String qid = s.substring(0, s.indexOf("-"));
                String[] oidArr = (String[]) map1.get(s);
                if (oidArr == null) continue;
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < oidArr.length; i++) {
                    sb.append(oidArr[i]);
                }
                answerMap.put(qid, sb.toString());
            }
            if (s.equals("page")) {
                String[] ss = (String[]) map1.get(s);
                if (ss != null) {
                    spage = ss[0];
                } else {
                    spage = "";
                }
            }
        }
//        选择答案后就提交
        request.getSession().setAttribute("answerMap", answerMap);
        PageBean pageBean = ((PageBean) request.getSession().getAttribute("pageBean"));
        if (spage.equals("")) {
            int curPage = pageBean.getCurPage();
            if (curPage < pageBean.getPageCount())
                pageBean.setCurPage(curPage + 1);
            else
                pageBean.setCurPage(curPage);
        } else {
            pageBean.setCurPage(Integer.parseInt(spage));
        }
        List<Question> questions = pageUtil.findPageByHql(pageBean.getCurPage(), pageBean.getPageSize(), qids);
        request.getSession().setAttribute("pageBean", pageBean);
        request.getSession().setAttribute("questions", questions);
        return "main";
    }

    /**
     * @param request
     * @param response
     * @description 提交试卷 将答案保存到数据库中
     */
    @RequestMapping(value = "/main/flash", method = RequestMethod.GET)
    public void flash(HttpServletRequest request, HttpServletResponse response) {
        TestPaper testPaper = (TestPaper) request.getSession().getAttribute("testPaper");
        User user = (User)request.getSession().getAttribute("user");
        Map<String, String> answerMap = (Map<String, String>) request.getSession().getAttribute("answerMap");
        List<Subject> subjects = (List<Subject>) request.getSession().getAttribute("pageBean");
        int totalScore = 0;

        for (Iterator<Subject> item = subjects.iterator(); item.hasNext(); ) {
            Subject subject = item.next();
            Question question = subject.getQuestion();
            String qid = String.valueOf(question.getId());
            int score = subject.getScore();

//          保存答案
            String stdAnswer = question.getStdAnswer();
            String usrAnswer = answerMap.get(qid);
            Answer answer2 = new Answer();
            answer2.setQuestion(question);
            answer2.setAnswer(usrAnswer);
            answer2.setTestPaper(testPaper);
            answer2.setUser(user);
            answerService.save(answer2);

            if (stdAnswer.equals(usrAnswer)) {
                totalScore += score;
            }
        }
        //保存成绩
        testPaper.setScore(totalScore);
        testPaperService.update(testPaper);

        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write("你的成绩是 ：" + totalScore);
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     响应分页按钮
     */
    @RequestMapping(value = "/main/{pageCount}", method = RequestMethod.GET)
    public String page(@PathVariable int pageCount, HttpServletRequest request) {
        String qids = (String) request.getSession().getAttribute("qids");
        List<Question> questions = pageUtil.findPageByHql(pageCount, 1, qids);
        PageBean pageBean = ((PageBean) request.getSession().getAttribute("pageBean"));
        pageBean.setCurPage(pageCount);
        request.getSession().setAttribute("pageBean", pageBean);
        request.getSession().setAttribute("questions", questions);
        return "main";
    }

    @RequestMapping(value = "/getExam", method = RequestMethod.GET)
    public String getExam(HttpServletRequest request) {
        Exam exam = examService.findByEidAndUid(2L, 1L);
        request.setAttribute("exam", exam);
        return "exam";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("user") User user, HttpServletRequest request) {
        Type type = new Type();
        type.setName("单选题");
        typeService.save(type);

        Option option = new Option();
        optionService.save(option);

        Question question = new Question();
        question.setDate(new Date());
        question.setContent("问题一");
        question.setDesc("问题一");
        question.setOption(option);
        questionService.save(question);

        Exam exam = new Exam();
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        exam.setDate(new Date());
        user.setId(1L);
        examService.save(exam);
        request.setAttribute("exam", exam);
        return "main";
    }
}
