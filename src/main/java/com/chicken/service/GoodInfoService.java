package com.chicken.service;

import com.chicken.dao.GoodInfoDao;
import com.chicken.model.GoodInfo;
import com.chicken.vo.GoodInfoRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodInfoService {

    @Autowired
    GoodInfoDao goodInfoDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return goodInfoDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(GoodInfo record) {
        return goodInfoDao.insert(record);
    }

    public GoodInfo selectByPrimaryKey(Integer id) {
        return goodInfoDao.selectByPrimaryKey(id);
    }


    public List<GoodInfo> selectAll() {
        return goodInfoDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(GoodInfo record) {
        return goodInfoDao.updateByPrimaryKey(record);
    }


    public PageInfo<GoodInfo> selectByGoodInfo(GoodInfoRequest goodInfoRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<GoodInfo> userLists = goodInfoDao.selectByGoodInfo(goodInfoRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
