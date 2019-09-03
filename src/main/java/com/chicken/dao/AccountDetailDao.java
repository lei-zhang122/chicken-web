package com.chicken.dao;

import com.chicken.model.AccountDetail;
import com.chicken.vo.AccountDetailRequest;
import com.chicken.vo.AccountUserRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountDetail record);

    AccountDetail selectByPrimaryKey(Integer id);

    List<AccountDetail> selectAll();

    int updateByPrimaryKey(AccountDetail record);

    List<Map> selectByAccountDetail(AccountDetailRequest request);
 }