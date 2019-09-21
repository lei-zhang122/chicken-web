package com.chicken.util;

/**
 * @program:
 * @description: 常量工具类
 * @author: zhanglei
 * @create: 2018-07-25 10:39
 **/
public class ContantUtil {

    public static Integer DEFAULT_PAGE_NUM = 1;

    public static Integer DEFAULT_PAGE_SIZE = 20;

    public static String DEFAULT_USER_NAME = "user_session";

    public static String DEFAULT_LOGIN_CODE = "login_code";

    public static String NO_LOGIN_MESSAGE = "请登陆之后，再操作。";

    public static String INVOKE_URL = "https://www.ipoult.com/mp/pushNotice";

    public static Integer PAGE_SIZE = 2000;

    /**
     * 返回pagesize 和pageNum
     *
     * @param count
     * @return
     */
    public static Integer[] getPageNumAndPageSize(Long count) {

        //0=pageNum 1=pageSize
        Integer[] val = new Integer[2];

        //进行分页
        if (count < PAGE_SIZE) {
            val[0] = 1;
            val[1] = count.intValue();
        } else {
            int pageNumTemp = Math.toIntExact(count / PAGE_SIZE);
            val[0] = (count % PAGE_SIZE == 0) ? pageNumTemp : pageNumTemp + 1;
            val[1] = PAGE_SIZE;
        }
        return val;
    }
}
