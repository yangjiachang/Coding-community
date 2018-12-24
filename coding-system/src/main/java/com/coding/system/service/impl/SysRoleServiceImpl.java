package com.coding.system.service.impl;

import com.coding.system.domain.SysRole;
import com.coding.system.mapper.SysRoleMapper;
import com.coding.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 杨佳畅
 * @Description: 角色业务处理
 * @Date: Created in 2018/12/14 下午7:15
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据用户id查询用户角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @Override
    public String findRolesByUserId(Long userId) {
        List<SysRole> roles = sysRoleMapper.findRolesByUserId(userId);
        String r = roles.stream().map(SysRole::getRoleKey).collect(Collectors.joining(","));
        return r;
    }
}
