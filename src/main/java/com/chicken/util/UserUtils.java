package com.chicken.util;


import com.chicken.model.User;
import com.chicken.service.UserService;
import com.chicken.vo.UserRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * @program: rc-realtime-ruleengine
 * @description: 用户工具类
 * @author: zhanglei11527
 * @create: 2018-07-30 15:02
 **/
public class UserUtils {

    public static String checkUserInsertRequest(UserRequest userRequest) {
        if (userRequest == null) {
            return "系统内部错误";
        }

        //判断id 存在则修改内容
        if (!StringUtils.isBlank(userRequest.getId())) {
            UserService userService = SpringContextHolderUtils.getBean(UserService.class);
            User user = userService.selectByPrimaryKey(Integer.valueOf(userRequest.getId()));
            if (user == null) {
                return "用户不存在,无法修改内容";
            }
        }

        if (StringUtils.isBlank(userRequest.getUserName())) {
            return "真实姓名不能为空";
        }

        if (StringUtils.isBlank(userRequest.getLoginName())) {
            return "用户名不能为空";
        }

        if (StringUtils.isBlank(userRequest.getUserPwd())) {
            return "用户密码不能为空";
        }

        if (StringUtils.isBlank(userRequest.getStatus())) {
            return "业务方状态不能为空";
        }

        if (StringUtils.isBlank(userRequest.getPhone())) {
            return "手机号不能为空";
        }

        return null;
    }
}
