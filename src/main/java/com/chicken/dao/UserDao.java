package com.chicken.dao;

import com.chicken.model.User;
import com.chicken.vo.UserRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 根据条件查询数据
     */
    List<Map> selectByUser(UserRequest userRequest);

    /**
     * 根据用户名查询
     * @param loginName
     * @return
     */
    User selectByLoginName(@Param("loginName") String loginName);

    /**
     * 更新用户密码
     * @param userPwd
     * @param id
     * @return
     */
    int updatePwdById(@Param("userPwd") String userPwd, @Param("id") Integer id);
}
