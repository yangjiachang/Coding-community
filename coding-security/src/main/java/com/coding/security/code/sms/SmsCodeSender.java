package com.coding.security.code.sms;

/**
 * 短信验证码发送接口
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
