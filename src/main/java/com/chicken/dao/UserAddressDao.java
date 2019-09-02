package com.chicken.dao;

import com.chicken.model.UserAddress;
import com.chicken.vo.UserAddressRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserAddressDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAddress record);

    UserAddress selectByPrimaryKey(Integer id);

    List<UserAddress> selectAll();

    int updateByPrimaryKey(UserAddress record);

    List<Map> selectByUserAddress(UserAddressRequest userAddressRequest);

}