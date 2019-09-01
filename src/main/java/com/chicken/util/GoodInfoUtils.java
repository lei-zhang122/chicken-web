package com.chicken.util;

import com.chicken.model.GoodInfo;
import com.chicken.service.GoodInfoService;
import com.chicken.vo.GoodInfoRequest;
import org.apache.commons.lang3.StringUtils;


public class GoodInfoUtils {


    public static String checkGoodInfoInsertRequest(GoodInfoRequest goodInfoRequest) {
        if (goodInfoRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(goodInfoRequest.getId())) {
            GoodInfoService goodInfoService = SpringContextHolderUtils.getBean(GoodInfoService.class);
            GoodInfo rcBusinessInfo = goodInfoService.selectByPrimaryKey(Integer.valueOf(goodInfoRequest.getId()));
            if (rcBusinessInfo == null) {
                return "商品不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(goodInfoRequest.getGoodType())) {
            return "商品类型不能为空";
        }

        if (StringUtils.isBlank(goodInfoRequest.getGoodName())) {
            return "商品名称不能为空";
        }

        if (StringUtils.isBlank(goodInfoRequest.getGoodNum())) {
            return "商品数据不能为空";
        }

        if (StringUtils.isBlank(goodInfoRequest.getGoodPrice())) {
            return "商品价格不能为空";
        }

        if (StringUtils.isBlank(goodInfoRequest.getGoodVirtual())) {
            return "虚拟价格不能为空";
        }

        return null;
    }
}
