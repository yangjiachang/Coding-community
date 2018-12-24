package com.coding.web.controller.system;

import com.coding.common.config.Global;
import com.coding.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: 杨佳畅
 * @Description: 首页 业务处理
 * @Date: Created in 2018/12/10 下午11:47
 */
@Controller
public class SystemIndexController extends BaseController {

    // 系统首页
//    @GetMapping("/index")
//    public String index(ModelMap mmap) {
//        // 取出身份信息
//        return null;
//    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("version", Global.getVersion());
        return "admin/main";
    }
}
