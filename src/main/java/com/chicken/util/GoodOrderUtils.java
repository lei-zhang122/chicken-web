package com.chicken.util;

import com.chicken.model.GoodOrder;
import com.chicken.service.GoodOrderService;
import com.chicken.vo.GoodOrderRequest;
import org.apache.commons.lang3.StringUtils;


public class GoodOrderUtils {


    public static String checkGoodOrderInsertRequest(GoodOrderRequest goodOrderRequest) {
        if (goodOrderRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(goodOrderRequest.getId())) {
            GoodOrderService goodOrderService = SpringContextHolderUtils.getBean(GoodOrderService.class);
            GoodOrder rcBusinessOrder = goodOrderService.selectByPrimaryKey(Integer.valueOf(goodOrderRequest.getId()));
            if (rcBusinessOrder == null) {
                return "商品不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(goodOrderRequest.getExchangeStatus())) {
            return "兑换状态不能为空";
        }

        if (StringUtils.isBlank(goodOrderRequest.getGoodId())) {
            return "商品不能为空";
        }

        if (StringUtils.isBlank(goodOrderRequest.getUserId())) {
            return "用户ID不能为空";
        }

        return null;
    }
}
