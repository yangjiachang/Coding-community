package com.coding.system.service;

import com.coding.system.domain.SysUser;

/**
 * @Author: 杨佳畅
 * @Description: 用户业务层
 * @Date: Created in 2018/12/13 下午11:57
 */
public interface ISysUserService {
    /**
     * 通过用户名或手机号查找用户
     * @param username 用户名/手机号
     * @return
     */
    SysUser findByNameOrMobile(String username);

    /**
     * 新增用户
     * @param user 用户信息
     * @return
     */
    int insertUser(SysUser user);
}
