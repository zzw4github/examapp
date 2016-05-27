package com.infosea.examApp.controller;

import com.google.common.base.Strings;
import com.infosea.examApp.dao.PageUtil;
import com.infosea.examApp.dto.*;
import com.infosea.examApp.pojo.*;
import com.infosea.examApp.service.*;


import com.infosea.examApp.vo.PageBean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by infosea on 2016/4/19.
 */
@Controller
public class TestController {
    @Autowired
    ExamService examService;
    @Autowired
    TypeService typeService;
    @Autowired
    QuestionService questionService;
    @Autowired
    QuestionTypeService questionTypeService;
    @Autowired
    UserService userService;
    @Autowired
    PageUtil<Subject> pageUtil;
    @Autowired
    CommonService commonService;
    @Autowired
    TestPaperService testPaperService;
    @Autowired
    AnswerService answerService;

    @Autowired
    TestPaperDefineService testPaperDefineService;

    @Autowired
    TestService testService;

    Map<String, String> answerMap = new HashMap<>();//answerMap

    @ResponseBody
    @RequestMapping("/success")
    public String test1(){
        return "success";
    }

    /**
     * 登录完成后进入参加考试页面
     * @param userId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/{userId}/test", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String exam(@PathVariable("userId") long userId ,HttpServletRequest request,Model model) {

        String testPaperDefineId = request.getParameter("testPaperDefineID");
        String examId = request.getParameter("examId");

        if (Strings.isNullOrEmpty(examId)) {
            examId = "1";
        }
        if (Strings.isNullOrEmpty(testPaperDefineId)) {
            testPaperDefineId = "1";
        }

        TestDTO testDTO = testService.userTest(userId,Long.valueOf(examId));
        request.getSession().setAttribute("testPaperDefineId", testPaperDefineId);
        request.getSession().setAttribute("testDTO", testDTO);
        model.addAttribute("testDTO",testDTO);
        return "ks/jrks";
    }
    /**
     * 点击进入考试后返回一套试卷, 并只返回第一个问题
     * 从请求中获取
     * 试卷定义ID
     * 考试ID
     * @param request
     * @return
     */
    @RequestMapping(value = "/test/user/{userId}", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
    public String test(@PathVariable("userId") long userId ,HttpServletRequest request,Model model) {

        String testPaperDefineId = request.getParameter("testPaperDefineID");
        String examId = request.getParameter("examId");

        if (Strings.isNullOrEmpty(examId)) {
            examId = "1";
        }
        if (Strings.isNullOrEmpty(testPaperDefineId)) {
            testPaperDefineId = "1";
        }

        TestDTO testDTO = testService.test(userId, Long.valueOf(examId));
        request.getSession().setAttribute("answerMap", answerMap);
      request.getSession().setAttribute("testPaperDefineId", testPaperDefineId);
        request.getSession().setAttribute("testDTO", testDTO);
//        model.addAttribute("testDTO",testDTO);
        System.out.println("abc");
        return "ks/ks";
    }

    /**
     * 单个问题提交时，保存到session中，并返回下一个问题
     * @param request
     * @return
     * @TODO 将答案保存到数据库
     */
    @RequestMapping(value = "/test/user/{userId}/pageNo/{pageNo}",
                    method = {RequestMethod.POST, RequestMethod.POST},
                    produces="application/json;charset=UTF-8")
    @ResponseBody
    public TestDTO page(
                         @PathVariable("userId") long userId,
                         @PathVariable("pageNo") int pageNo,
                         HttpServletRequest request) {
        long beginTime = new Date().getTime();
        TestDTO testDTO =(TestDTO) request.getSession().getAttribute("testDTO");
        System.out.println(testDTO.getTestPaperDTO().getId()+"---");
        long testPaperId = testDTO.getTestPaperDTO().getId();
//      request.getParameterMap()中将包含你表单里面所有input标签的数据
//      input checkbox radio
//      key 标签名
//      value 标签值
//      点提交按钮后提交

        Map map1 = request.getParameterMap();
        Set<String> key = map1.keySet();
        String tMinute =(String) request.getParameter("tMinute");
        String tSecond = (String) request.getParameter("tSecond");
        String qId = (String) request.getParameter("questionId");
        String qAnswer = (String) request.getParameter("questionAnswer");
        answerMap.put(qId, qAnswer);

        request.getSession().setAttribute("answerMap", answerMap);
        String hql = "select testPaper.subjects from TestPaper testPaper where testPaper.id=" + testPaperId;
        List<Subject> subjects = pageUtil.findPageByHql(pageNo, 1, hql);
        System.out.println("---------");
        PageBean<Subject> pageBean = testDTO.getPageBean();
        pageBean.setCurPage(pageNo);
        pageBean.setObjects(subjects);
        testDTO.setPageBean(pageBean);
        testDTO.settMinute(tMinute);
        testDTO.settSecond(tSecond);
//        request.getSession().setAttribute("TestDTO", testDTO);
//        return "ks/ks";
        return testDTO;

    }

    /**
     * @param request
     * @param response
     * @description 提交试卷 将答案保存到数据库中
     */
    @RequestMapping(value = "/test/user/{userId}/commit",
                    method = RequestMethod.POST,
                    produces="application/json;charset=UTF-8"
                    )
    public String commit(HttpServletRequest request, HttpServletResponse response,Model model) {
        TestDTO testDTO =(TestDTO) request.getSession().getAttribute("testDTO");
        long testPaperId = testDTO.getTestPaperDTO().getId();
        long userId = testDTO.getUserDTO().getId();
        Map<String, String> answerMap = (Map<String, String>) request.getSession().getAttribute("answerMap");
        ResultDTO resultDTO = testService.saveAnswer(testPaperId, userId , answerMap);
        resultDTO.setUserId(userId);
        model.addAttribute("resultDTO", resultDTO);
        return "ks/kw";
    }

    /**
     * 考完获取问题内容
     * @param userId
     * @param questionId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/test/user/{userId}/question/{questionId}",
            method = RequestMethod.GET,
            produces="application/json;charset=UTF-8"
    )
    @ResponseBody
    public QuestionDTO getQuestion(@PathVariable("userId")long userId,
                                   @PathVariable("questionId")long questionId,
                                   HttpServletRequest request,
                                   Model model){
        TestDTO testDTO =(TestDTO) request.getSession().getAttribute("testDTO");
        long testPaperId = testDTO.getTestPaperDTO().getId();
        List<Integer> ids = testDTO.getIds();
        int currentIndex = ids.indexOf((int)questionId);
        QuestionDTO questionDTO = testService.getQuestion( userId,  testPaperId,  questionId);
        int preIndex = currentIndex - 1;
        if(preIndex<0){
            preIndex = currentIndex;
        }
        int nextIndex = currentIndex + 1;
        if(nextIndex > ids.size()){
            nextIndex = ids.size();
        }
        questionDTO.setPreId(ids.get(preIndex));
        questionDTO.setNextId(ids.get(nextIndex));
        questionDTO.setUserId(userId);
//        model.addAttribute("questionDTO", questionDTO);
        return questionDTO;
    }

}
