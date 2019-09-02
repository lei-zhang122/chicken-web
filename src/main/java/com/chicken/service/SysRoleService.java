package com.chicken.service;

import com.chicken.model.RolePermission;
import com.chicken.model.SysRole;
import com.chicken.model.UserRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglei on 2018/7/4.
 */

public interface SysRoleService {


    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);

    PageInfo<SysRole> findByPage(SysRole sysRole, int pageNum, int pageSize);

    List<SysRole> selectSysRole(Integer userId);

    List<Map> selectPermissionByRole(Integer roleId);

    int deletePermissionRole(Integer id);

    void insertBatch(List<RolePermission> map);

    void insertUserRole(UserRole userRole);

    void deleteUserRole(Integer userId);
}
