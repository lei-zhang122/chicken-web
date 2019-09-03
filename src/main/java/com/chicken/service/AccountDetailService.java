package com.chicken.service;

import com.chicken.dao.AccountDetailDao;
import com.chicken.dao.AccountUserDao;
import com.chicken.model.AccountDetail;
import com.chicken.model.AccountUser;
import com.chicken.vo.AccountDetailRequest;
import com.chicken.vo.AccountUserRequest;
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
public class AccountDetailService {

    @Autowired
    AccountDetailDao accountDetailDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return accountDetailDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(AccountDetail record) {
        return accountDetailDao.insert(record);
    }

    public AccountDetail selectByPrimaryKey(Integer id) {
        return accountDetailDao.selectByPrimaryKey(id);
    }


    public List<AccountDetail> selectAll() {
        return accountDetailDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(AccountDetail record) {
        return accountDetailDao.updateByPrimaryKey(record);
    }


    public PageInfo<Map> selectByAccountDetail(AccountDetailRequest accountDetailRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = accountDetailDao.selectByAccountDetail(accountDetailRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }
}
