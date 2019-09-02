package com.chicken.util;

import com.chicken.model.WechatUser;
import com.chicken.service.WechatUserService;
import com.chicken.vo.WechatUserRequest;
import org.apache.commons.lang3.StringUtils;


public class WechatUserUtils {


    public static String checkWechatUserInsertRequest(WechatUserRequest wechatUserRequest) {
        if (wechatUserRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(wechatUserRequest.getId())) {
            WechatUserService wechatUserService = SpringContextHolderUtils.getBean(WechatUserService.class);
            WechatUser wechatUser = wechatUserService.selectByPrimaryKey(Integer.valueOf(wechatUserRequest.getId()));
            if (wechatUser == null) {
                return "用户不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(wechatUserRequest.getNickName())) {
            return "昵称不能为空";
        }

        if (StringUtils.isBlank(wechatUserRequest.getAvatar())) {
            return "头像不能为空";
        }

        if (StringUtils.isBlank(wechatUserRequest.getOpenid())) {
            return "openId不能为空";
        }

        if (StringUtils.isBlank(wechatUserRequest.getRegSource())) {
            return "注册来源不能为空";
        }

        if (StringUtils.isBlank(wechatUserRequest.getUnionid())) {
            return "unionid不能为空";
        }

        return null;
    }
}
