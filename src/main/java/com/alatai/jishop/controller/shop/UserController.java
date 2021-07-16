package com.alatai.jishop.controller.shop;

import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 20:25
 */
@RestController("shopUserController")
@RequestMapping("/data")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String name = user.getName();
        // 去除空白/特殊符号转义
        name = HtmlUtils.htmlEscape(name);

        boolean isExist = userService.isExist(name);

        if (isExist) {
            return "fail";
        }

        userService.register(user);

        return "success";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        String name = user.getName();
        String password = user.getPassword();
        // name = HtmlUtils.htmlEscape(name);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        try {
            subject.login(token);
            User findUser = userService.findByName(name);
            session.setAttribute("user", findUser);

            return "success";
        } catch (AuthenticationException exp) {
            return "fail";
        }
    }

}