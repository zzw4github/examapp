package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.*;
import com.infosea.examApp.service.TestPaperDefineService;
import com.infosea.examApp.service.TypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * testPaperDefine
 * 应该是TestPaperDefineController
 * Created by infosea on 2016/5/20.
 */
@Controller
public class TestPaperController {
    Logger logger = LogManager.getLogger(TestPaperController.class);
    @Autowired
    TestPaperDefineService testPaperDefineService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/")
    public String index(){
        List<TestPaperDefine> testPaperDefines =testPaperDefineService.findAll();
        return "";
     }

    @RequestMapping(value = "/testPaper",method = RequestMethod.POST)
    @ResponseBody
//    name=test&valid=YES&defineDate=2015-01-01&questionTypes[0].type.id=1&questionTypes[0].type.name=panduanti&questionTypes[0].score=2&questionTypes[0].amount=30
    public List<TestPaperDefine> add(  TestPaperDefine testPaperDefine ,  Model model){
//        Type type = new Type();
//        Type type = typeService.findById(1L);
//        int score =2;
//        int amount =20;
//        String defineName ="";
//        QuestionType questionType = new QuestionType(type , score , amount);
//        List<QuestionType> questionTypes = new ArrayList<>();
//        questionTypes.add(questionType);
//         testPaperDefine = new TestPaperDefine(defineName ,new Date());
//        testPaperDefine.setQuestionTypes(questionTypes);
        testPaperDefineService.save(testPaperDefine);
        List<TestPaperDefine> testPaperDefines = testPaperDefineService.findAll();
        model.addAttribute(testPaperDefines);
        return testPaperDefines;
    }
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public String test(  TestObj test ,  Model model) {
        return  test.toString();
    }


    /**
     * 试卷定义
     * @param testPaperDefine
     * @param request
     * @return
     */
    @RequestMapping(value = "/testPaper", method = RequestMethod.PUT)
    public String add(@RequestParam("testPaperDefine")TestPaperDefine testPaperDefine, HttpServletRequest request) {
        testPaperDefineService.save(testPaperDefine);
        return "main";
    }

    @RequestMapping(value = "/testPaper/{id}",method = RequestMethod.POST)
    public String update(@PathVariable("id") long id, @RequestParam TestPaperDefine testPaperDefine ,  ModelMap model){

         testPaperDefine = testPaperDefineService.findById(id);
        testPaperDefineService.update(testPaperDefine);
        List<TestPaperDefine> testPaperDefines = testPaperDefineService.findAll();
        model.put("defines",testPaperDefines );
        model.addAttribute("message" , "success");
        return "";
    }

    @RequestMapping(value = "/testPaper/{tid}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("tid") long tid ,  Model model){
        testPaperDefineService.del(tid);
        List<TestPaperDefine> testPaperDefines =  testPaperDefineService.findAll();
        model.addAttribute("defines",testPaperDefines);
        model.addAttribute("message" , "success");
        return "";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }
}
