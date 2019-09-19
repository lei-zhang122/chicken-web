package com.chicken.dao;

import com.chicken.model.UserInvite;
import com.chicken.vo.UserInviteRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserInviteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInvite record);

    UserInvite selectByPrimaryKey(Integer id);

    List<UserInvite> selectAll();

    int updateByPrimaryKey(UserInvite record);

    List<Map> selectByUserInvite(UserInviteRequest userInviteRequest);

    Map selectCount(UserInviteRequest userInviteRequest);
}