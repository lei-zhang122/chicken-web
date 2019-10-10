package com.chicken.service;

import com.chicken.dao.BlackUserDao;
import com.chicken.dao.GoodOrderDao;
import com.chicken.model.BlackUser;
import com.chicken.model.GoodOrder;
import com.chicken.vo.BlackUserRequest;
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
public class BlackUserService {

    @Autowired
    BlackUserDao blackUserDao;

    @Autowired
    RedisService redisService;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        BlackUser blackUser = selectByPrimaryKey(id);
        redisService.deleteKey("b:u:".concat(blackUser.getUserId().toString()));
        return blackUserDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(BlackUser record) {
        redisService.set("b:u:".concat(record.getUserId().toString()),"1");
        return blackUserDao.insert(record);
    }

    public BlackUser selectByPrimaryKey(Integer id) {
        return blackUserDao.selectByPrimaryKey(id);
    }


    public List<BlackUser> selectAll() {
        return blackUserDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(BlackUser record) {

        BlackUser blackUser = selectByPrimaryKey(record.getId());
        redisService.deleteKey("b:u:".concat(blackUser.getUserId().toString()));

        redisService.set("b:u:".concat(record.getUserId().toString()),"1");

        return blackUserDao.updateByPrimaryKey(record);
    }


    public PageInfo<BlackUser> selectByBlackUser(BlackUserRequest request, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<BlackUser> userLists = blackUserDao.selectByBlackUser(request);
        PageInfo result = new PageInfo(userLists);
        return result;
    }

}
