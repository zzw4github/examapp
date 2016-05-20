package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.QuestionType;
import com.infosea.examApp.pojo.TestPaperDefine;
import com.infosea.examApp.pojo.Type;
import com.infosea.examApp.service.TestPaperDefineService;
import com.infosea.examApp.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by infosea on 2016/5/20.
 */
@Controller
@RequestMapping("/testPaper")
public class TestPaperController {
    @Autowired
    TestPaperDefineService testPaperDefineService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/")
    public String index(){
        List<TestPaperDefine> testPaperDefines =testPaperDefineService.findAll();
        return "";
     }

    @RequestMapping("add")
    public String add(Model model){
//        Type type = new Type();
        Type type = typeService.findById(1L);
        int score =2;
        int amount =20;
        String defineName ="";
        QuestionType questionType = new QuestionType(type , score , amount);
        List<QuestionType> questionTypes = new ArrayList<>();
        questionTypes.add(questionType);
        TestPaperDefine testPaperDefine = new TestPaperDefine(defineName ,new Date());
        testPaperDefine.setQuestionTypes(questionTypes);
        testPaperDefineService.save(testPaperDefine);
        List<TestPaperDefine> testPaperDefines = testPaperDefineService.findAll();
        model.addAttribute(testPaperDefines);
        return "";
    }

    @RequestMapping("update")
    public String update(ModelMap model){
        long id = 1;
        TestPaperDefine testPaperDefine = testPaperDefineService.findById(id);

        testPaperDefine.setName("");
        testPaperDefineService.update(testPaperDefine);
        List<TestPaperDefine> testPaperDefines = testPaperDefineService.findAll();
        model.put("defines",testPaperDefines );
        model.addAttribute("message" , "success");
        return "";
    }

    @RequestMapping("delete")
    public String delete(Model model){
        int id = 1;
        testPaperDefineService.del(id);
        List<TestPaperDefine> testPaperDefines =  testPaperDefineService.findAll();
        model.addAttribute("defines",testPaperDefines);
        model.addAttribute("message" , "success");
        return "";
    }
}
