package com.coding.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Codign论坛属性配置文件
 */
@Component
@ConfigurationProperties(prefix = "coding.security")
public class CodingSecurityProperties {

    // 前台登录页面地址
    private String userLoginPage;

    // 后台登录页面地址
    private String adminLoginPage;

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public String getUserLoginPage() {
        return userLoginPage;
    }

    public void setUserLoginPage(String userLoginPage) {
        this.userLoginPage = userLoginPage;
    }

    public String getAdminLoginPage() {
        return adminLoginPage;
    }

    public void setAdminLoginPage(String adminLoginPage) {
        this.adminLoginPage = adminLoginPage;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
