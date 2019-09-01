package com.chicken.dao;

import com.chicken.model.RolePermission;
import com.chicken.model.SysRole;
import com.chicken.model.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysRoleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);

    /**
     * 根据条件查询数据
     */
    List<SysRole> selectBySysRole(SysRole sysRole);


    /**
     * 根据用户查询角色
     * @param userId
     * @return
     */
    List<SysRole> selectSysRole(Integer userId);

    /**
     * 查询角色功能
     * @param roleId
     * @return
     */
    List<Map> selectPermissionByRole(Integer roleId);

    /**
     * 删除角色权限
     * @param id
     * @return
     */
    int deletePermissionRole(Integer id);

    /**
     * 批量增加数据
     * @param map
     * @return
     */
    int insertBatch(List<RolePermission> map);

    /**
     * 插入角色用户
     * @param userRole
     */
    void insertUserRole(UserRole userRole);

    /**
     * 删除角色用户
     * @param userId
     */
    void deleteUserRole(Integer userId);
}