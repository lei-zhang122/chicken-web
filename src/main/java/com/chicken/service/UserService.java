package com.chicken.service;

import com.chicken.model.User;
import com.chicken.vo.UserRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglei11527 on 2018/7/4.
 */

public interface UserService {


    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    PageInfo<Map> findByPage(UserRequest userRequest, int pageNum, int pageSize);

    User selectByLoginName(String loginName);

    String checkLogin(String phoneNumber, String password, String code);

    int updatePwdById(String userPwd, Integer id);
}
