package com.chicken.service.impl;

import com.chicken.dao.UserDao;
import com.chicken.model.User;
import com.chicken.service.UserService;
import com.chicken.vo.UserRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglei on 2018/7/5.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer id) {
        return userDao.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(User record) {
        return userDao.insert(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(User record) {
        return userDao.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Map> findByPage(UserRequest userRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = userDao.selectByUser(userRequest);
        PageInfo result = new PageInfo(userLists);

        return result;
    }

    @Override
    public User selectByLoginName(String loginName) {
        return userDao.selectByLoginName(loginName);
    }

    @Override
    public String checkLogin(String phoneNumber, String password,String code) {

        if(null == code){
            return "0";
        }

        if(!password.equals(code)){
            return "1";
        }

        /**
         * 查询用户是否存在
         */
        User user = this.selectByLoginName(phoneNumber);
        if(null == user){
            return "3";
        }

        return "666";
    }

    @Override
    @Transactional
    public int updatePwdById(String userPwd, Integer id) {
        return userDao.updatePwdById(userPwd,id);
    }

}
