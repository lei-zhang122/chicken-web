package com.chicken.service;

import com.chicken.dao.UserInviteDao;
import com.chicken.vo.GoodOrderRequest;
import com.chicken.vo.UserInviteRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2019-09-01 21:10
 */
@Service
public class UserInviteService {

    @Autowired
    UserInviteDao userInviteDao;

    public PageInfo<Map> selectByUserInvite(UserInviteRequest userInviteRequest, int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了
        PageHelper.startPage(pageNum, pageSize);
        List<Map> userLists = userInviteDao.selectByUserInvite(userInviteRequest);
        PageInfo result = new PageInfo(userLists);
        return result;
    }

    public Map selectCount(UserInviteRequest userInviteRequest){
        return userInviteDao.selectCount(userInviteRequest);
    }
}
