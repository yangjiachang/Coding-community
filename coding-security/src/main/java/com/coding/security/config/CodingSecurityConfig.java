package com.coding.security.config;

import com.coding.security.authentication.CodingAuthenticationFailHandler;
import com.coding.security.authentication.CodingAuthenticationSuccessHandler;
import com.coding.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.coding.security.code.ValidateCodeGenerator;
import com.coding.security.code.ValidateCodeSecurityConfig;
import com.coding.security.code.img.ImageCodeGenerator;
import com.coding.security.properties.CodingSecurityProperties;
import com.coding.security.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: 杨佳畅
 * @Description: Security相关配置类
 * @Date: Created in 2018/12/11 上午10:51
 */
@Configuration
public class CodingSecurityConfig extends WebSecurityConfigurerAdapter {

    // 属性配置文件
    @Autowired
    private CodingSecurityProperties codingSecurityProperties;

    // 登录成功处理器
    @Autowired
    private CodingAuthenticationSuccessHandler codingAuthenticationSuccessHandler;

    // 登录失败处理器
    @Autowired
    private CodingAuthenticationFailHandler codingAuthenticationFailHandler;

    // 验证码校验安全配置
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    // 短信验证码认证安全配置
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    // 密码加密
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 配置此项后由UsernamePasswordAuthenticationFilter来处理表单的post请求
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM) // 处理表单登录的url
                .successHandler(codingAuthenticationSuccessHandler) // 处理登录成功
                .failureHandler(codingAuthenticationFailHandler) // 处理登录失败
            .and()
                // 验证码配置
                .apply(validateCodeSecurityConfig)
            .and()
                // 短信验证码登录的配置
                .apply(smsCodeAuthenticationSecurityConfig)
            .and()
                .authorizeRequests() // 以下是授权配置
                .antMatchers(codingSecurityProperties.getUserLoginPage()).permitAll() // 前台登录页面
                .antMatchers(codingSecurityProperties.getAdminLoginPage()).permitAll() // 后台登录页面
                .antMatchers(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*").permitAll() // 验证码处理
                .antMatchers("/index").permitAll()
                .antMatchers("/user/login").permitAll()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/sms/login").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "COMMON")
                .antMatchers("/favicon.ico", "/static/**", "/css/**", "/js/**",
                        "/ruoyi/**", "/ajax/**", "/img/**", "/fonts/**").permitAll()  // 静态资源
                .anyRequest() // 所有请求
                .authenticated() //都需要认证
            .and()
                .exceptionHandling() // 配置登录入口控制器
                .authenticationEntryPoint(urlEntryPoint())
                .accessDeniedPage("/403"); // 无权访问的页面

        http.csrf().disable(); // 关闭csrf
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    public LoginUrlEntryPoint urlEntryPoint() {
        return new LoginUrlEntryPoint("/user/login");
    }

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(codingSecurityProperties);
        return imageCodeGenerator;
    }
}
