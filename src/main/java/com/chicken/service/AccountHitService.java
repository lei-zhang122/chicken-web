package com.chicken.service;

import com.chicken.dao.AccountHitDao;
import com.chicken.model.AccountHit;
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
public class AccountHitService {

    @Autowired
    AccountHitDao accountHitDao;

    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Integer id) {
        return accountHitDao.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(AccountHit record) {
        return accountHitDao.insert(record);
    }

    public AccountHit selectByPrimaryKey(Integer id) {
        return accountHitDao.selectByPrimaryKey(id);
    }


    public List<AccountHit> selectAll() {
        return accountHitDao.selectAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(AccountHit record) {
        return accountHitDao.updateByPrimaryKey(record);
    }


    public PageInfo<Map> selectByAccountHit(AccountDetailRequest accountDetailRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = accountHitDao.selectByAccountHit(accountDetailRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }

    public Map selectByUserIdAndHidUserId(AccountHit accountHit){
        return accountHitDao.selectByUserIdAndHidUserId(accountHit);
    }

    public List<Map> selectHitMyCount(AccountDetailRequest request){
        return accountHitDao.selectHitMyCount(request);
    }
}
