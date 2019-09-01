package com.chicken.service;

import com.chicken.dao.GoodDetailDao;
import com.chicken.model.GoodDetail;
import com.chicken.model.GoodInfo;
import com.chicken.vo.GoodDetailRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-01 21:10
 */
@Service
public class GoodDetailService {

    @Autowired
    GoodDetailDao goodDetailDao;

    @Transactional(rollbackFor = Exception.class)
    public int insert(GoodDetail record) {
        return goodDetailDao.insert(record);
    }

    public PageInfo<Map> selectByGoodDetail(GoodDetailRequest goodDetail, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = goodDetailDao.selectByGoodDetail(goodDetail);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
