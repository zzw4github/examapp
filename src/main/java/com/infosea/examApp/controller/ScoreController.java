package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.Exam;
import com.infosea.examApp.service.ExamService;


import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

/**
 * Created by infosea on 2016/5/5.
 */
@Controller
public class ScoreController {
    @Autowired
    ExamService examService;
    @RequestMapping("/score")
    public String score(){
        return "score";
    }

    @RequestMapping("/score/all")
    public ModelAndView getExamListByUserName(@RequestParam String username ){
//        Exam exam =(Exam)commonService.selectById(Exam.class,1L);
        List<Exam> examList = examService.findALl("from Exam e left join fetch e.user");
        ModelAndView modelAndView = new ModelAndView("score");
//        Exam[] exams = examList.toArray(new Exam[examList.size()]);
//        modelAndView.addObject(exams);
        modelAndView.addObject("exams",ScoreController.obj2Json(examList));
    return  modelAndView;

    }

    public static String obj2Json(List<Exam> exams) {
//        JSONArray array = new JSONArray();
//        for (Exam exam: exams) {
//            JSONObject obj = new JSONObject();
//            try {
//                obj.put("id",exam.getId());
//                obj.put("desc", exam.getDesc());
//                obj.put("score",exam.getScore());
//                obj.put("userName", exam.getUser().getName());
//            } catch (Exception e) {
//            }
//            array.put(obj);
//        }
//        return array.toString();
        return "";

    }
}
