package com.coding.security.code.sms;

import com.coding.security.code.ValidateCode;
import com.coding.security.code.impl.AbstractValidateCodeProcessor;
import com.coding.security.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * 短信验证码处理器
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode>{

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;


    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        // 获取参数名称
        String parameName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        // 获取手机号
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), parameName);
        // 发送短信验证码
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
