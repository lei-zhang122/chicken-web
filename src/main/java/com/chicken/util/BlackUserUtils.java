package com.chicken.util;

import com.chicken.model.BlackUser;
import com.chicken.model.Dictionary;
import com.chicken.service.BlackUserService;
import com.chicken.service.DictionaryService;
import com.chicken.vo.BlackUserRequest;
import com.chicken.vo.DictionaryRequest;
import org.apache.commons.lang3.StringUtils;


public class BlackUserUtils {


    public static String checkBlackUserInsertRequest(BlackUserRequest blackUserRequest) {
        if (blackUserRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(blackUserRequest.getId())) {
            BlackUserService blackUserService = SpringContextHolderUtils.getBean(BlackUserService.class);
            BlackUser blackUser = blackUserService.selectByPrimaryKey(Integer.valueOf(blackUserRequest.getId()));
            if (blackUser == null) {
                return "黑名单不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(blackUserRequest.getUserId())) {
            return "用户ID不能为空";
        }

        return null;
    }
}
