package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/22.
 */
@Controller
@RequestMapping(value = "/exam")
public class ExamController {
    @Autowired
    ExamService examService;
    @RequestMapping(value = "/start" ,method = RequestMethod.GET)
    public String start(Model model) {
       List<Exam> exams =examService.findAll();
        model.addAttribute("exams",exams);
        return "/exam/index";
    }
    @RequestMapping(value = "/del/{eid}" ,method = RequestMethod.GET)
    public void del(@PathVariable String eid, HttpServletResponse response) {
//        examService.delete(eid);
        try {
            response.getWriter().write("删除成功");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/upd" ,method = RequestMethod.GET)
    public void upd(Model model, HttpServletResponse response) {
        List<Exam> exams =examService.findAll();
        model.addAttribute("exams",exams);
        try {
            response.getWriter().write("修改成功");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @RequestMapping(value = "/table" ,method = RequestMethod.POST)
    public void table( HttpServletRequest request, HttpServletResponse response) {
        String str = request.getParameter("action");
        String eid = request.getParameter("eid");
        if(str.equals("edit")) {
            try {
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                String flag = request.getParameter("valid");
                Exam exam =examService.findExam(Long.valueOf(eid));
                exam.setDate(new Date(date));
                exam.setValidFlag(flag.charAt(0));
                exam.setDesc(name);
                examService.update(exam);
                response.getWriter().write("{\"success\":true,\"message\":\"修改成功\"}");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(str.equals("delete")){
            try {
                examService.delete(Long.valueOf(eid));
                response.getWriter().write("{\"success\":true,\"message\":\"删除成功\"}");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
