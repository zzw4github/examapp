package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.*;
import com.infosea.examApp.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/22.
 */
@Controller
public class ExamController {
    @Autowired
    ExamService examService;

    /**
     * 获取所有考试定义
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/exam/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Exam> exams = examService.findAll();
        model.addAttribute("exams", exams);
        return "/exam/index";
    }

    /**
     * 根据ID获取考试
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/exam/{eid}", method = RequestMethod.GET)
    public String exam(@PathVariable("eid") Long id, Model model) {
        Exam exam = examService.findByID(id);
        model.addAttribute("exam", exam);
        return "/exam/index";
    }

    /**
     * 根据考试ID删除考试
     *
     * @param eid
     * @param response
     */
    @RequestMapping(value = "/exam/{eid}", method = RequestMethod.DELETE)
    public void del(@PathVariable String eid, HttpServletResponse response) {
        examService.delete(Long.valueOf(eid));
        try {
            response.getWriter().write("删除成功");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 更新考试
     *
     * @param model
     * @param response
     */
    @RequestMapping(value = "/exam/{eid}", method = RequestMethod.POST)
    public void upd(@PathVariable("eid") long eid, @RequestParam Exam exam, Model model, HttpServletResponse response) {
        exam = examService.findByID(eid);
        examService.update(exam);
        List<Exam> exams = examService.findAll();
        model.addAttribute("exams", exams);
        try {
            response.getWriter().write("修改成功");
            response.getWriter().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /**
     * 使用jquery.tabledit插件 传过来的参数 修改/删除内容
     *
     * @param request
     * @param response
     */

    @RequestMapping(value = "/table", method = RequestMethod.POST)
    public void table(HttpServletRequest request, HttpServletResponse response) {
        String str = request.getParameter("action");
        String eid = request.getParameter("eid");
        if (str.equals("edit")) {
            try {
                String name = request.getParameter("name");
                String date = request.getParameter("date");
                String flag = request.getParameter("valid");
                Exam exam = examService.findByID(Long.valueOf(eid));
                exam.setDate(new Date(date));
                exam.setValidFlag(flag);
                exam.setDesc(name);
                examService.update(exam);
                response.getWriter().write("{\"success\":true,\"message\":\"修改成功\"}");
                response.getWriter().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (str.equals("delete")) {
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
