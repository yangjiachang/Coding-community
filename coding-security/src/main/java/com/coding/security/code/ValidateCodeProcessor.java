package com.coding.security.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同的校验码的处理逻辑
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入Session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     *
     * @param request Spring提供的工具类 封装了 request和response
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 验证码的校验
     * @param request Spring提供的工具类 封装了 request和response
     */
    void validate(ServletWebRequest request);
}
