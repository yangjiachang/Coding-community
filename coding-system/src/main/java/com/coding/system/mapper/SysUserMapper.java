package com.coding.system.mapper;

import com.coding.system.domain.SysUser;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author 杨佳畅
 */
public interface SysUserMapper {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return
     */
    SysUser findByUsername(String username);

    /**
     * 通过手机号查找用户
     * @param mobile 用户名
     * @return
     */
    SysUser findByMobile(String mobile);

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUser user);
}
