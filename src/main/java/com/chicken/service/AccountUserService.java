package com.chicken.service;

import com.chicken.dao.AccountUserDao;
import com.chicken.dao.WechatUserDao;
import com.chicken.model.AccountUser;
import com.chicken.model.WechatUser;
import com.chicken.vo.AccountUserRequest;
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
public class AccountUserService {

    @Autowired
    AccountUserDao accountUserDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return accountUserDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(AccountUser record) {
        return accountUserDao.insert(record);
    }

    public AccountUser selectByPrimaryKey(Integer id) {
        return accountUserDao.selectByPrimaryKey(id);
    }


    public List<AccountUser> selectAll() {
        return accountUserDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(AccountUser record) {
        return accountUserDao.updateByPrimaryKey(record);
    }


    public PageInfo<Map> selectByAccountUser(AccountUserRequest accountUser, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = accountUserDao.selectByAccountUser(accountUser);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
