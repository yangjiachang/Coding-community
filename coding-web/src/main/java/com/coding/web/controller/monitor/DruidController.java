package com.coding.web.controller.monitor;

import com.coding.framework.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 杨佳畅
 * @Description:
 * @Date: Created in 2018/12/10 下午10:36
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController
{
    private String prefix = "/monitor/druid";

    @GetMapping()
    public String index()
    {
        return redirect(prefix + "/index");
    }
}
