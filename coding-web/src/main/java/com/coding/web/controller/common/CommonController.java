package com.coding.web.controller.common;

import com.coding.common.base.AjaxResult;
import com.coding.framework.web.base.BaseController;
import com.coding.system.domain.SysUser;
import com.coding.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 杨佳畅
 * @Description: 通用请求处理
 * @Date: Created in 2018/12/10 下午10:24
 */
@Controller
public class CommonController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping({"/", "/index"})
    public String index(Authentication authentication, Model model) {
        model.addAttribute("user",authentication.getPrincipal());
        return "index";
    }

    @RequestMapping("/user/login")
    public String page() {
        return "user/login";
    }

    @RequestMapping("/user/register")
    public String register() {
        return "user/register";
    }

    @RequestMapping("/admin/login")
    public String adminLogin() {
        return "admin/login";
    }

    @RequestMapping("/admin/center")
    public String centerPage() {
        return "admin/center";
    }

    // 短信登录
    @RequestMapping("/sms/login")
    public String mobile() {
        return "user/mobile";
    }

    // 前台用户注册
    @PostMapping("/user/register")
    public AjaxResult userRegister(SysUser user) {
        System.out.println(passwordEncoder.encode(user.getPassword()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    @RequestMapping("/403")
    public String to403() {
        return "error/403";
    }

    @RequestMapping("/404")
    public String to404() {
        return "error/404";
    }
}
