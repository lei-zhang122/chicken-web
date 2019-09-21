package com.chicken.dao;

import com.chicken.model.GoodInfo;
import com.chicken.vo.GoodInfoRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodInfo record);

    GoodInfo selectByPrimaryKey(Integer id);

    List<GoodInfo> selectAll();

    int updateByPrimaryKey(GoodInfo record);

    List<GoodInfo> selectByGoodInfo(GoodInfoRequest request);

    /**
     * 修改商品状态
     * @param id
     * @param goodStatus
     * @return
     */
    int updateGoodStatusById(@Param("id") Integer id, @Param("goodStatus") String goodStatus);

    /**
     * 修改商品库存
     * @param id
     * @param goodNum
     * @return
     */
    int updateGoodNumById(@Param("id") Integer id, @Param("goodNum") Integer goodNum);

    /**
     * 查询需要最少的积分商品
     * @return
     */
    Map selectMinVirtual();
}