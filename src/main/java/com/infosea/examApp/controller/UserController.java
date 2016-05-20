package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.User;
import com.infosea.examApp.service.UserService;
import com.infosea.examApp.vo.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/5/9.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){

        return "";
    }

    @RequestMapping("/query")
    public String query(ModelMap model){

        int pageNo = 1 ;
        int pageSize = 5 ;
        Map<Object ,Object > paramMap = new HashMap<>();
        PageBean<User> users = userService.find(pageNo , pageSize , paramMap);
        model.put("users" , users);
        return "";
    }
}
