package com.coding.web.controller.security;

import com.coding.common.base.AjaxResult;
import com.coding.security.code.ValidateCodeProcessor;
import com.coding.security.code.ValidateCodeProcessorHolder;
import com.coding.security.properties.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @Author: 杨佳畅
 * @Description: 验证码Controller
 * @Date: Created in 2018/12/14 上午12:29
 */
@RestController
public class ValidateCodeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor} 接口实现
     *
     * @param request
     * @param type
     * @throws Exception
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public AjaxResult createCode(ServletWebRequest request, @PathVariable String type)  {
        try {
            validateCodeProcessorHolder.findValidateCodeProcessor(type).create(request);
            return AjaxResult.success("验证成功");
        } catch (Exception e) {
            logger.error("短信验证码发送失败", e);
            return AjaxResult.error();
        }
    }
}
