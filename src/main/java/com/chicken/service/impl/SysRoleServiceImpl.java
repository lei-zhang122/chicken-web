package com.chicken.service.impl;

import com.chicken.dao.SysRoleDao;
import com.chicken.model.RolePermission;
import com.chicken.model.SysRole;
import com.chicken.model.UserRole;
import com.chicken.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglei11527 on 2018/7/5.
 */
@Service(value = "sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleDao sysRoleDao;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        return sysRoleDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(SysRole record) {
        return sysRoleDao.insert(record);
    }

    @Override
    public SysRole selectByPrimaryKey(Integer id) {
        return sysRoleDao.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> selectAll() {
        return sysRoleDao.selectAll();
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(SysRole record) {
        return sysRoleDao.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<SysRole> findByPage(SysRole sysRole, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysRole> userLists = sysRoleDao.selectBySysRole(sysRole);
        PageInfo result = new PageInfo(userLists);
        return result;
    }

    @Override
    public List<SysRole> selectSysRole(Integer userId) {
        return sysRoleDao.selectSysRole(userId);
    }

    @Override
    public List<Map> selectPermissionByRole(Integer roleId) {
        return sysRoleDao.selectPermissionByRole(roleId);
    }

    @Override
    @Transactional
    public int deletePermissionRole(Integer id) {
        return sysRoleDao.deletePermissionRole(id);
    }

    @Override
    @Transactional
    public void insertBatch(List<RolePermission> map) {
        sysRoleDao.insertBatch(map);
    }

    @Override
    @Transactional
    public void insertUserRole(UserRole userRole) {
        sysRoleDao.insertUserRole(userRole);
    }

    @Override
    @Transactional
    public void deleteUserRole(Integer userId) {
        sysRoleDao.deleteUserRole(userId);
    }
}
