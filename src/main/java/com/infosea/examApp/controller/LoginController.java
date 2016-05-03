package com.infosea.examApp.controller;

import com.infosea.examApp.pojo.User;
import com.infosea.examApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by infosea on 2016/4/19.
 */
@Controller
@SessionAttributes("user")
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login/index";
    }

    @RequestMapping(value = "/login" ,method = RequestMethod.POST )
    public String login(@ModelAttribute("user") User user,  RedirectAttributes attr) {
        if(userService.login(user)) {
            attr.addFlashAttribute("user",user);
            return "redirect:/main";
        }else {
            return "redirect:/";
        }
    }
}
