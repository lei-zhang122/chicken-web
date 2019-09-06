package com.chicken.dao;

import com.chicken.model.AccountSigned;
import com.chicken.vo.AccountDetailRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AccountSignedDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountSigned record);

    AccountSigned selectByPrimaryKey(Integer id);

    List<AccountSigned> selectAll();

    int updateByPrimaryKey(AccountSigned record);

    List<Map> selectByAccountSigned(AccountDetailRequest request);
}