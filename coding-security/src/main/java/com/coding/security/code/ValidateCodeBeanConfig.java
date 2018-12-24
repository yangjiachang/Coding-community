package com.coding.security.code;

import com.coding.security.code.sms.DefaultSmsCodeSender;
import com.coding.security.code.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码Bean配置类
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 使用者可以向系统注册一个名称为smsCodeSender的Bean
     * 来覆盖默认的短信验证码配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
