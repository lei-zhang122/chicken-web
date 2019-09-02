package com.chicken.service;

import com.chicken.dao.WechatUserDao;
import com.chicken.model.WechatUser;
import com.chicken.vo.WechatUserRequest;
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
public class WechatUserService {

    @Autowired
    WechatUserDao wechatUserDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return wechatUserDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(WechatUser record) {
        return wechatUserDao.insert(record);
    }

    public WechatUser selectByPrimaryKey(Integer id) {
        return wechatUserDao.selectByPrimaryKey(id);
    }


    public List<WechatUser> selectAll() {
        return wechatUserDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(WechatUser record) {
        return wechatUserDao.updateByPrimaryKey(record);
    }


    public PageInfo<Map> selectByWechatUser(WechatUserRequest wechatUserRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = wechatUserDao.selectByWechatUser(wechatUserRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
