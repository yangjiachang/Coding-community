package com.coding.security.authentication.mobile;

import com.coding.security.service.CodingUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 针对短信验证码认证的安全配置
 */
@Component
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    // 登录成功处理器
    @Autowired
    private AuthenticationSuccessHandler codingAuthenticationSuccessHandler;

    // 登录失败处理器
    @Autowired
    private AuthenticationFailureHandler codingAuthenticationFailHandler;

    @Autowired
    private CodingUserDetailsService codingUserDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        // 配置AuthenticationManager 因为需要它来调用对应的AuthenticationProvider
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        // 设置成功处理器
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(codingAuthenticationSuccessHandler);
        // 设置失败处理器
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(codingAuthenticationFailHandler);

        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(codingUserDetailsService);

        // 将smsCodeAuthenticationProvider加入到AuthenticationManager的集合中
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
