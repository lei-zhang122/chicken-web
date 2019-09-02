package com.chicken.service;

import com.chicken.dao.UserAddressDao;
import com.chicken.model.UserAddress;
import com.chicken.vo.UserAddressRequest;
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
public class UserAddressService {

    @Autowired
    UserAddressDao userAddressDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return userAddressDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(UserAddress record) {
        return userAddressDao.insert(record);
    }

    public UserAddress selectByPrimaryKey(Integer id) {
        return userAddressDao.selectByPrimaryKey(id);
    }


    public List<UserAddress> selectAll() {
        return userAddressDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(UserAddress record) {
        return userAddressDao.updateByPrimaryKey(record);
    }


    public PageInfo<Map> selectByAccountUser(UserAddressRequest userAddressRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = userAddressDao.selectByUserAddress(userAddressRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
