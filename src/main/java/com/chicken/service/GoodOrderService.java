package com.chicken.service;

import com.chicken.dao.GoodOrderDao;
import com.chicken.model.GoodOrder;
import com.chicken.vo.GoodOrderRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GoodOrderService {

    @Autowired
    GoodOrderDao goodOrderDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return goodOrderDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(GoodOrder record) {
        return goodOrderDao.insert(record);
    }

    public GoodOrder selectByPrimaryKey(Integer id) {
        return goodOrderDao.selectByPrimaryKey(id);
    }


    public List<GoodOrder> selectAll() {
        return goodOrderDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(GoodOrder record) {
        return goodOrderDao.updateByPrimaryKey(record);
    }


    public PageInfo<Map> selectByGoodOrder(GoodOrderRequest goodOrderRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = goodOrderDao.selectByGoodOrder(goodOrderRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }

    public void updateExchangeStatusById(Integer id, String exchangeStatus, Date exchangeTime, Integer modifyUser) {
        goodOrderDao.updateExchangeStatusById(id, exchangeStatus, exchangeTime, modifyUser);
    }

    public Map selectCount(GoodOrderRequest goodOrderRequest){
        return goodOrderDao.selectCount(goodOrderRequest);
    }
}
