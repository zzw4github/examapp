package com.infosea.examApp.controller;

import com.google.common.base.Strings;
import com.infosea.examApp.dao.PageUtil;
import com.infosea.examApp.pojo.*;
import com.infosea.examApp.service.*;


import com.infosea.examApp.vo.PageBean;
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

    Map<String, String> qidMap = new HashMap<>(); //qidMap
    Map<String, String> answerMap = new HashMap<>();//answerMap

    /**
     * 读者登录后返回一套试卷
     * @TODO 点击开始考试后返回一套试卷
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main1(@ModelAttribute("user") User user, HttpServletRequest request) {
        user = userService.findUser(user);
        String testPaperDefineId = request.getParameter("testPaperDefineID");
        if(Strings.isNullOrEmpty(testPaperDefineId)){
            testPaperDefineId = "1";
        }
        Exam exam = examService.produceExam(user,Integer.parseInt(testPaperDefineId));
        TestPaper testPaper = exam.getTestPaper();
        long testPaperId = testPaper.getId();
        String qids = exam.getTestPaper().getQuestions_id();
         qids = qids.substring(0, qids.lastIndexOf(","));
        //返回 试题 第一条数据

       PageBean<Question> pageBean = questionService.find(1,1,qids);
        request.getSession().setAttribute("answerMap", answerMap);
        request.getSession().setAttribute("qidMap", qidMap);
        request.getSession().setAttribute("exam", exam);
        request.getSession().setAttribute("testPaperId", testPaperId);
        request.getSession().setAttribute("qids", qids);
        request.getSession().setAttribute("pageBean", pageBean);
        return "main";
    }

    /**
     * 单个问题提交时，保存到session中，并返回下一个问题
     * @TODO 将答案保存到数据库
     * @param request
     * @return
     */
    @RequestMapping(value = "/main/single", method = RequestMethod.POST)
    public String page(HttpServletRequest request) {
        String qids = (String) request.getSession().getAttribute("qids");
//      request.getParameterMap()中将包含你表单里面所有input标签的数据
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
                qidMap.put(qid, sb.toString());
                answerMap.put(s, sb.toString());
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
        request.getSession().setAttribute("qidMap", qidMap);
        request.getSession().setAttribute("temp2", answerMap);
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
     *  @description 提交试卷
     * 将答案保存到数据库中
     * @param request
     * @param response
     */
    @RequestMapping(value = "/main/flash", method = RequestMethod.GET)
    public void flash(HttpServletRequest request, HttpServletResponse response) {
        Exam exam = (Exam)request.getSession().getAttribute("exam");
        User user = (User)request.getSession().getAttribute("user");
        int totalScore = 0;
        Map<String, String> map = (Map<String, String>) request.getSession().getAttribute("answerMap");
        List<Question> questions = questionService.findAll();
        for (Iterator<Question> item = questions.iterator(); item.hasNext();) {
            Question question = item.next();
            String qid = String.valueOf(question.getId());
            long typeid = question.getType().getId();

            int score = Integer.parseInt(exam.getTestPaper().getTestPaperDefine().getQuestionTypes()getgetScore());
//          保存答案
            String answer = question.getStdAnswer();
            String answer1 = map.get(qid);
            Answer answer2 = new Answer();
            answer2.setQuestion(question);
            answer2.setAnswer(answer1);
            answer2.setExam(exam);
            answer2.setUser(user);
            answerService.save(answer2);
//          保存成绩
            if (answer.equals(answer1)) {
                toatalScore += score;
            }
        }
        //保存成绩
        exam.setScore(totalScore);
        examService.update(exam);
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
//        question.setQuestionType(questionType);
        question.setContent("问题一");
        question.setDesc("问题一");
        question.setOption(option);
        questionService.save(question);
        Exam exam = new Exam();
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        exam.setDate(new Date());
        user.setId(1L);
        User user1 = userService.find(user.getId());
        exam.setUser(user1);
        examService.save(exam);
        request.setAttribute("exam", exam);
        return "main";
    }
}
