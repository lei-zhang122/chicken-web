package com.chicken.util;

import com.chicken.model.UserAddress;
import com.chicken.service.UserAddressService;
import com.chicken.vo.UserAddressRequest;
import org.apache.commons.lang3.StringUtils;


public class UserAddressUtils {


    public static String checkUserAddressInsertRequest(UserAddressRequest addressRequest) {
        if (addressRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(addressRequest.getId())) {
            UserAddressService userAddressService = SpringContextHolderUtils.getBean(UserAddressService.class);
            UserAddress userAddress = userAddressService.selectByPrimaryKey(Integer.valueOf(addressRequest.getId()));
            if (userAddress == null) {
                return "地址不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(addressRequest.getUserAddress())) {
            return "地址不能为空";
        }

        if (StringUtils.isBlank(addressRequest.getContact())) {
            return "联系人不能为空";
        }

        if (StringUtils.isBlank(addressRequest.getPhone())) {
            return "手机号不能为空";
        }

        return null;
    }
}
