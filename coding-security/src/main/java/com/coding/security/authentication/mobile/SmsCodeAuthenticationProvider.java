package com.coding.security.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 提供短信验证码校验逻辑
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * @param authentication 用户信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken anthenticationToken = (SmsCodeAuthenticationToken) authentication;
        // 根据手机号获取用户信息
        UserDetails user = userDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        // 传入用户信息 标记为已认证
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        // 这时候已经认证成功 将未认证的信息复制到已认证的信息中
        authenticationResult.setDetails(authenticationResult.getDetails());

        return authenticationResult;

    }

    // 判断传入的anthentication是否是SmsCodeAuthenticationToken类型
    @Override
    public boolean supports(Class<?> anthentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(anthentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
