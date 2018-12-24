package com.coding.system.mapper;

import com.coding.system.domain.SysRole;

import java.util.List;

/**
 * @Author: 杨佳畅
 * @Description: 角色表数据层
 * @Date: Created in 2018/12/14 下午7:17
 */
public interface SysRoleMapper {

    public List<SysRole> findRolesByUserId(Long userId);
}
