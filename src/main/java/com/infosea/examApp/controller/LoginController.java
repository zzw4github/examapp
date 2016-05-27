package com.infosea.examApp.controller;

import com.google.common.base.Strings;
import com.infosea.examApp.enums.DictEnum;
import com.infosea.examApp.pojo.User;
import com.infosea.examApp.service.UserService;
import com.infosea.examApp.vo.PageBean;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by infosea on 2016/4/19.
 */
@Controller
@SessionAttributes("user")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }


    /**
     * 验证用户登录
     * 管理员跳转到admin.jsp
     * 其他用户跳转到main.jsp
     *
     * @param user
     * @param attr
     * @return
     * @TODO 不同用户用不同的登录页面，现在是同一个页面
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String login( User user, RedirectAttributes attr) {
        User user1 = userService.login(user);
        if (user1 != null) {
            attr.addFlashAttribute("user", user1);
            if (DictEnum.UserType.ADMIN.equals(user1.getPermission())) {
                return "redirect:/admin";
            } else {
                System.out.println(user1.getId());
                return "redirect:/user/"+user1.getId()+"/test";
//                return "redirect:/test/user/"+user1.getId();
            }
        } else {
            return "login";
        }
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin( User user ,Model model) {
        model.addAttribute("user", user);
        System.out.println(user.getName());
        return "gl/glzjm";
    }

    @RequestMapping(value = "/ks", method = RequestMethod.GET)
    public String ks( User user  ,Model model) {
        model.addAttribute("user", user);
        System.out.println(user.getName());
        return "/user/"+user.getId();
    }


    /**
     * 查询用户接口
     * 根据查询条件返回结果
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/user/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    private
    @ResponseBody
    PageBean<User> queryUser(HttpServletRequest request) {
        String tmh = request.getParameter("tmh");
        String yjdw = request.getParameter("yjdw");
        String ejdw = request.getParameter("ejdw");
        String pageCount = request.getParameter("pageCount");
        String pageNum = request.getParameter("pageNum");
        Map<String, Object> map = new HashMap<>();
        if (!Strings.isNullOrEmpty(tmh)) {
            map.put("tmh", tmh);
        }
        if (!Strings.isNullOrEmpty(yjdw)) {
            map.put("yjdw", yjdw);
        }
        if (!Strings.isNullOrEmpty(ejdw)) {
            map.put("ejdw", ejdw);
        }
        if (Strings.isNullOrEmpty(pageCount)) {
            pageCount = "5";
        }
        if (Strings.isNullOrEmpty(pageNum)) {
            pageNum = "1";
        }
        PageBean<User> pageBean = userService.findPageBean(Integer.parseInt(pageCount), Integer.parseInt(pageNum), map);
        return pageBean;
    }


    /**
     * 根据用户名删除用户
     *
     * @param userName
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/{userName}", method = RequestMethod.DELETE)
    public String del(@PathVariable(value = "userName") String userName, HttpServletRequest request) {
        if (userService.deleteUser(userName)) {
            return "{'message':'success'}";
        } else {
            return "{'message':'fail'}";
        }
    }

    /**
     * 根据用户名删除用户
     *
     * @param userId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/{userId}")
    public String delById(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        if (userService.deleteById(userId)) {
            return "{'message':'success'}";
        } else {
            return "{'message':'fail'}";
        }
    }

    /**
     * 修改用户
     * 将 pwd 的 updatable属性设置为false，让密码可以不更改
     *
     * @param user
     * @return
     * @TODO 不返回 pojo 返回 dto ，dto中不包含密码属性
     */
    @ResponseBody
    @RequestMapping(value = "/user/{uid}", method = RequestMethod.POST, headers = {"content-type=application/json"})
    public User update(@PathVariable("uid") long uid, @RequestBody User user) {
        User user1 = null;
        User usr = userService.findUserByTmh(user.getTmh());
        String message = "";
        if (usr != null) {
            user1.setName(usr.getTmh());
            user1.setPwd(usr.getPwd());
            userService.saveOrUpdate(user1);
        }
        return user1;
//        return "{\"message\":\""+message+"\"}";
    }
}
