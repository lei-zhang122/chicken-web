package com.chicken.dao;

import com.chicken.model.AccountHit;
import com.chicken.vo.AccountDetailRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountHitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountHit record);

    AccountHit selectByPrimaryKey(Integer id);

    List<AccountHit> selectAll();

    int updateByPrimaryKey(AccountHit record);

    List<Map> selectByAccountHit(AccountDetailRequest request);
}