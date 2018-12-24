package com.coding.security.code.sms;

import com.coding.security.code.ValidateCode;
import com.coding.security.code.ValidateCodeGenerator;
import com.coding.security.properties.CodingSecurityProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private CodingSecurityProperties securityProperties;


    @Override
    public ValidateCode generate(ServletWebRequest request) {
        // 生成一个随机数
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public CodingSecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(CodingSecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
