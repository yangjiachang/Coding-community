package com.coding.web.controller.system;

import com.coding.common.config.Global;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 杨佳畅
 * @Description: 首页 业务处理
 * @Date: Created in 2018/12/10 下午11:47
 */
@Controller
public class SystemIndex {

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}
