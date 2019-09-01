package com.chicken.dao;

import com.chicken.model.GoodDetail;
import com.chicken.vo.GoodDetailRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodDetail record);

    GoodDetail selectByPrimaryKey(Integer id);

    List<GoodDetail> selectAll();

    int updateByPrimaryKey(GoodDetail record);

    /**
     * 查询订单明细
     * @param goodDetail
     * @return
     */
    List<Map> selectByGoodDetail(GoodDetailRequest goodDetail);
}