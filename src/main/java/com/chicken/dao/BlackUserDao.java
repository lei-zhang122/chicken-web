package com.chicken.dao;

import com.chicken.model.BlackUser;
import com.chicken.vo.BlackUserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlackUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BlackUser record);

    BlackUser selectByPrimaryKey(Integer id);

    List<BlackUser> selectAll();

    int updateByPrimaryKey(BlackUser record);

    List<BlackUser> selectByBlackUser(BlackUserRequest request);
}