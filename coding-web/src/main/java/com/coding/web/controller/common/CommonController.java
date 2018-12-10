package com.coding.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 杨佳畅
 * @Description: 通用请求梳理
 * @Date: Created in 2018/12/10 下午10:24
 */
@Controller
public class CommonController {

    @RequestMapping("/index")
    public String page() {
        return "index";
    }
}
