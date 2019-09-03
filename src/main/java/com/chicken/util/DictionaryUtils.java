package com.chicken.util;

import com.chicken.model.AccountUser;
import com.chicken.model.Dictionary;
import com.chicken.service.AccountUserService;
import com.chicken.service.DictionaryService;
import com.chicken.vo.AccountUserRequest;
import com.chicken.vo.DictionaryRequest;
import org.apache.commons.lang3.StringUtils;


public class DictionaryUtils {


    public static String checkDictionaryInsertRequest(DictionaryRequest dictionaryRequest) {
        if (dictionaryRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(dictionaryRequest.getId())) {
            DictionaryService dictionaryService = SpringContextHolderUtils.getBean(DictionaryService.class);
            Dictionary dictionary = dictionaryService.selectByPrimaryKey(Integer.valueOf(dictionaryRequest.getId()));
            if (dictionary == null) {
                return "字典不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(dictionaryRequest.getDictName())) {
            return "字典名称不能为空";
        }

        if (StringUtils.isBlank(dictionaryRequest.getDictType())) {
            return "字典分类不能为空";
        }

        if (StringUtils.isBlank(dictionaryRequest.getDictContent())) {
            return "字典内容不能为空";
        }

        if (StringUtils.isBlank(dictionaryRequest.getDictOrder())) {
            return "顺序不能为空";
        }

        return null;
    }
}
