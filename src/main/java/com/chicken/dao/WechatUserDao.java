package com.chicken.dao;

import com.chicken.model.WechatUser;
import com.chicken.vo.WechatUserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WechatUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatUser record);

    WechatUser selectByPrimaryKey(Integer id);

    List<WechatUser> selectAll();

    int updateByPrimaryKey(WechatUser record);


    List<Map> selectByWechatUser(WechatUserRequest request);
}