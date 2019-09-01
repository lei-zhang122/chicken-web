package com.chicken.dao;

import com.chicken.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPermissionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);

    /**
     * 根据条件查询数据
     */
    List<SysPermission> selectBySysPermission(SysPermission sysPermission);

    /**
     * 根据角色id查询功能
     * @param roleId
     * @return
     */
    List<SysPermission> selectByRoleId(Integer roleId);

    /**
     * 查询用户功能权限
     * @param userId
     * @return
     */
    List<SysPermission> selectByUserId(Integer userId);

    /**
     * 查询用户权限
     * @param userId
     * @param parentId
     * @return
     */
    List<SysPermission> selectByUserIdAndParentId(@Param("userId") Integer userId, @Param("parentId") Integer parentId);

    /**
     * 根据父id查询所有权限
     * @param parentId
     * @return
     */
    List<SysPermission> selectByParentId(Integer parentId);
}