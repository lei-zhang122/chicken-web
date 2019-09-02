package com.chicken.dao;

import com.chicken.model.AccountUser;
import com.chicken.vo.AccountUserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountUser record);

    AccountUser selectByPrimaryKey(Integer id);

    List<AccountUser> selectAll();

    int updateByPrimaryKey(AccountUser record);

    List<Map> selectByAccountUser(AccountUserRequest accountUser);
}