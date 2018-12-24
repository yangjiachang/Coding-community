package com.coding.security.code.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 默认的短信验证码发送逻辑
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @param code   短信验证码
     */
    @Override
    public void send(String mobile, String code) {
        logger.info("向手机号 " + mobile + " 发送短信验证码 " + code);
    }
}
