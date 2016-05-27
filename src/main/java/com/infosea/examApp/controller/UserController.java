package com.infosea.examApp.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.pojo.View;
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
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/user/query")
    @JsonView(View.Summary.class)
    @ResponseBody
    public List<User> query(User user ,ModelMap model){

        int pageNo = 1 ;
        int pageSize = 5 ;
        Map<String ,Object > paramMap = new HashMap<>();
        paramMap.put("yjdw",user.getYjdw());
//        paramMap.put("ejdw",user.getEjdw());
//        paramMap.put("dzlx",user.getDzlx());
        List<User> userList = userService.findList(pageNo,pageSize,paramMap);
        PageBean<User> users = userService.findPageBean(pageNo , pageSize , paramMap);
        System.out.println(users.getObjects().get(0).getId());
        return userList;
    }
}
