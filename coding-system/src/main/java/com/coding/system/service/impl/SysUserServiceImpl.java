package com.coding.system.service.impl;

import com.coding.common.utils.CodingUtil;
import com.coding.system.domain.SysRole;
import com.coding.system.domain.SysUser;
import com.coding.system.mapper.SysUserMapper;
import com.coding.system.service.ISysRoleService;
import com.coding.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 杨佳畅
 * @Description: 用户业务层处理
 * @Date: Created in 2018/12/14 上午12:02
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public SysUser findByNameOrMobile(String username) {
        SysUser myUser = null;
        // 是否为手机号
        boolean isMobile = CodingUtil.isPhoneNo(username);
        if (isMobile) {
            myUser = sysUserMapper.findByMobile(username);
        } else {
            myUser = sysUserMapper.findByUsername(username);
        }

        if (myUser == null) {
            return null;
        }

        // 根据用户id查询角色信息
        String roles = sysRoleService.findRolesByUserId(myUser.getUserId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        // 权限列表
        myUser.setRoleName(roles);
        return myUser;
    }

    /**
     * 新增用户
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(SysUser user) {
        // 新增用户信息
        int rows = sysUserMapper.insertUser(user);
        return rows;
    }
}
