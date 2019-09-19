package com.chicken.dao;

import com.chicken.model.GoodOrder;
import com.chicken.vo.GoodOrderRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface GoodOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodOrder record);

    GoodOrder selectByPrimaryKey(Integer id);

    List<GoodOrder> selectAll();

    int updateByPrimaryKey(GoodOrder record);

    List<Map> selectByGoodOrder(GoodOrderRequest goodOrderRequest);

    Map selectCount(GoodOrderRequest goodOrderRequest);

    void updateExchangeStatusById(@Param("id") Integer id, @Param("exchangeStatus") String exchangeStatus, @Param("exchangeTime") Date exchangeTime, @Param("modifyUser") Integer modifyUser);
}