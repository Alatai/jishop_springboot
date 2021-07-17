package com.alatai.jishop.controller.shop;

import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 20:25
 */
@SuppressWarnings("DuplicatedCode")
@RestController("shopUserController")
@RequestMapping("/user")
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

    @GetMapping("/isLogin")
    public String checkLogin() {
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            return "fail";
        }

        return "success";
    }

    @PostMapping("/modalLogin")
    public String modalLogin(HttpSession session, @RequestBody Map<String, String> params) {
        String name = params.get("name");
        String password = params.get("password");

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        try {
            subject.login(token);
            User user = userService.findByName(name);
            session.setAttribute("user", user);

            return "success";
        } catch (AuthenticationException exp) {
            return "fail";
        }
    }
}
