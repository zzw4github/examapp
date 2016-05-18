package com.infosea.examApp.controller;

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

    Map<String, String> map = new HashMap<>();
    Map<String, String> map2 = new HashMap<>();

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
        int maxTestPaperId = commonService.findMaxId("select max(id) from testPaper");
        Random random = new Random();
        int testPaperId = random.nextInt(maxTestPaperId) + 1;
        TestPaper testPaper = testPaperService.findTestPaperById(testPaperId);
        Exam exam = new Exam();
        exam.setDate(new Date());
        exam.setDesc("入馆教育考试");
        exam.setValidFlag('Y');
        exam.setUser(user);
        exam.setTime(1);
        exam.setTestPaper(testPaper);
        long examId = examService.save(exam);//加入 考试 一行

        //获得当前试卷的所有试题id
        String sglc_ids = testPaper.getSglc_ids();
        String mulc_ids = testPaper.getMulc_ids();
        String tof_ids = testPaper.getTof_ids();
        String qids = sglc_ids + tof_ids + mulc_ids;
        qids = qids.substring(0, qids.lastIndexOf(","));
        //返回 试题 第一条数据
        String hql = "select q from Question q left join fetch q.questionType left join fetch q.option where q.id in (" + qids + ") order by q.id asc";
        List<Question> questions = pageUtil.findPageByQuery(1, 1, hql);
        List<Question> all = questionService.findByHQL(hql);
        PageBean pageBean = new PageBean(all.size());
        pageBean.setCurPage(1);
        pageBean.setPageSize(1);

        request.getSession().setAttribute("temp2", map2);
        request.getSession().setAttribute("temp", map);
        request.getSession().setAttribute("exam", exam);
        request.getSession().setAttribute("testPaperId", testPaperId);
        request.getSession().setAttribute("qids", qids);
        request.getSession().setAttribute("pageBean", pageBean);
        request.getSession().setAttribute("questions", questions);

        return "main";
    }

    /**
     * 单个问题提交时，保存到session中，并返回下一个问题
     * @TODO 将答案保存到数据库
     * @param request
     * @return
     */
    //
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
                map.put(qid, sb.toString());
                map2.put(s, sb.toString());
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
        request.getSession().setAttribute("temp", map);
        request.getSession().setAttribute("temp2", map2);
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
        String hql = "select q from Question q left join fetch q.questionType left join fetch q.option where q.id in (" + qids + ") order by q.id asc";
        List<Question> questions = pageUtil.findPageByQuery(pageBean.getCurPage(), pageBean.getPageSize(), hql);
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
        int toatalScore = 0;
        Map<String, String> map = (Map<String, String>) request.getSession().getAttribute("temp");
        List<Question> questions = questionService.findAll();
        for (Iterator<Question> item = questions.iterator(); item.hasNext(); ) {
            Question question = item.next();
            String qid = String.valueOf(question.getId());
//            int score = Integer.parseInt(question.getQuestionType().getScore());
            String answer = question.getStdAnswer();
            String answer1 = map.get(qid);
            if (answer.equals(answer1)) {
//                toatalScore += score;
            }
        }
        Exam exam = (Exam)request.getSession().getAttribute("exam");
        exam.setScore(toatalScore);
        examService.update(exam);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write("你的成绩是 ：" + toatalScore);
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
        String hql = "select q from Question q left join fetch q.questionType left join fetch q.option where q.id in (" + qids + ") order by q.id asc";
        List<Question> questions = pageUtil.findPageByQuery(pageCount, 1, hql);
        PageBean pageBean = ((PageBean) request.getSession().getAttribute("pageBean"));
        pageBean.setCurPage(pageCount);
        request.getSession().setAttribute("pageBean", pageBean);
        request.getSession().setAttribute("questions", questions);
        return "main";
    }

    @RequestMapping(value = "/getExam", method = RequestMethod.GET)
    public String getExam(HttpServletRequest request) {
        Exam exam = examService.findExamByEidAndUid(2L, 1L);
        request.setAttribute("exam", exam);
        return "exam";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@ModelAttribute("user") User user, HttpServletRequest request) {
        QuestionType questionType = new QuestionType();
        questionType.setDesc("单选题");
        questionTypeService.save(questionType);
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
