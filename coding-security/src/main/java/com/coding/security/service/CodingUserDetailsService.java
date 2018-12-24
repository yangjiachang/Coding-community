package com.coding.security.service;

import com.coding.security.domain.CodingUserDetails;
import com.coding.security.exception.CodingCredentialExcetion;
import com.coding.security.properties.LoginType;
import com.coding.system.domain.SysUser;
import com.coding.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import static com.coding.common.utils.CodingUtil.isPhoneNo;

@Component
public class CodingUserDetailsService implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录的用户名为：" + username);
        // 根据用户名查找用户信息
        SysUser myUser = sysUserService.findByNameOrMobile(username);
        // 是否为手机号码
        boolean isMobile = isPhoneNo(username);
        if (myUser != null) {
            CodingUserDetails userDetails = new CodingUserDetails(myUser.getLoginName(), myUser.getPassword(), true, true, true, true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(myUser.getRoleName()));
            userDetails.setAvatar(myUser.getAvatar());
            userDetails.setEmail(myUser.getEmail());
            userDetails.setUserId(myUser.getUserId());
            userDetails.setPassword(myUser.getPassword());
            if (isMobile)
                userDetails.setLoginType(LoginType.sms);
            return userDetails;
        } else {
            if (isMobile) throw new CodingCredentialExcetion("该手机号还未绑定账号！");
            throw new UsernameNotFoundException("");
        }
    }
}
