package com.coding.system.service;

import com.coding.system.domain.SysRole;

import java.util.List;

/**
 * @Author: 杨佳畅
 * @Description: 角色业务
 * @Date: Created in 2018/12/14 下午7:11
 */
public interface ISysRoleService {

    /**
     * 根据用户id查询用户角色
     * @param userId 用户id
     * @return 角色列表
     */
    public String findRolesByUserId(Long userId);
}
