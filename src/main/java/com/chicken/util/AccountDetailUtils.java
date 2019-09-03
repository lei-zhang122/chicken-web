package com.chicken.util;

import com.chicken.model.AccountUser;
import com.chicken.service.AccountUserService;
import com.chicken.vo.AccountUserRequest;
import org.apache.commons.lang3.StringUtils;


public class AccountDetailUtils {


    public static String checkAccountUserInsertRequest(AccountUserRequest accountUserRequest) {
        if (accountUserRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(accountUserRequest.getId())) {
            AccountUserService accountUserService = SpringContextHolderUtils.getBean(AccountUserService.class);
            AccountUser wechatUser = accountUserService.selectByPrimaryKey(Integer.valueOf(accountUserRequest.getId()));
            if (wechatUser == null) {
                return "用户不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(accountUserRequest.getAttentCount())) {
            return "总计积分不能为空";
        }

        if (StringUtils.isBlank(accountUserRequest.getConsumeCount())) {
            return "消费积分不能为空";
        }

        if (StringUtils.isBlank(accountUserRequest.getBalance())) {
            return "当前积分不能为空";
        }

        return null;
    }
}
