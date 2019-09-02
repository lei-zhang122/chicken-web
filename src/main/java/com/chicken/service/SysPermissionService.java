package com.chicken.service;

import com.chicken.model.SysPermission;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by zhanglei on 2018/7/4.
 */

public interface SysPermissionService {


    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);

    PageInfo<SysPermission> findByPage(SysPermission sysPremission, int pageNum, int pageSize);

    List<SysPermission> selectByRoleId(Integer roleId);

    List<SysPermission> selectByUserId(Integer userId);

    List<SysPermission> selectByUserIdAndParentId(Integer userId, Integer parentId);

    List<SysPermission> selectByParentId(Integer parentId);
}
