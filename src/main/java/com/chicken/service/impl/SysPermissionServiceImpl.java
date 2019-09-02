package com.chicken.service.impl;

import com.chicken.dao.SysPermissionDao;
import com.chicken.model.SysPermission;
import com.chicken.service.SysPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhanglei on 2018/7/5.
 */
@Service(value = "sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    SysPermissionDao sysPermissionDao;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        return sysPermissionDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(SysPermission record) {
        return sysPermissionDao.insert(record);
    }

    @Override
    public SysPermission selectByPrimaryKey(Integer id) {
        return sysPermissionDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysPermission> selectAll() {
        return sysPermissionDao.selectAll();
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(SysPermission record) {
        return sysPermissionDao.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<SysPermission> findByPage(SysPermission sysPremission, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysPermission> userLists = sysPermissionDao.selectBySysPermission(sysPremission);
        PageInfo result = new PageInfo(userLists);
        return result;
    }

    @Override
    public List<SysPermission> selectByRoleId(Integer roleId) {
        return sysPermissionDao.selectByRoleId(roleId);
    }

    @Override
    public List<SysPermission> selectByUserId(Integer userId) {
        return sysPermissionDao.selectByUserId(userId);
    }

    @Override
    public List<SysPermission> selectByUserIdAndParentId(Integer userId, Integer parentId) {
        return sysPermissionDao.selectByUserIdAndParentId(userId,parentId);
    }

    @Override
    public List<SysPermission> selectByParentId(Integer parentId) {
        return sysPermissionDao.selectByParentId(parentId);
    }
}
