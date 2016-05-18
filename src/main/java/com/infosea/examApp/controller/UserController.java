package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.User;
import com.infosea.examApp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by infosea on 2016/5/9.
 */
@Controller

public class UserController {
    @Autowired
    private UserService userService;


}
