package com.chicken.service;

import com.chicken.dao.AccountSignedDao;
import com.chicken.model.AccountSigned;
import com.chicken.model.WechatUser;
import com.chicken.vo.AccountDetailRequest;
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
public class AccountSignedService {

    @Autowired
    AccountSignedDao accountSignedDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return accountSignedDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(AccountSigned record) {
        return accountSignedDao.insert(record);
    }

    public AccountSigned selectByPrimaryKey(Integer id) {
        return accountSignedDao.selectByPrimaryKey(id);
    }


    public List<AccountSigned> selectAll() {
        return accountSignedDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(AccountSigned record) {
        return accountSignedDao.updateByPrimaryKey(record);
    }

    public Long selectCountByTips() {
        return accountSignedDao.selectCountByTips();
    }

    public PageInfo<Map> selectTips(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = accountSignedDao.selectTips();
        PageInfo result = new PageInfo(userLists);
        return result;
    }

    public PageInfo<Map> selectByAccountSigned(AccountDetailRequest accountDetailRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = accountSignedDao.selectByAccountSigned(accountDetailRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
