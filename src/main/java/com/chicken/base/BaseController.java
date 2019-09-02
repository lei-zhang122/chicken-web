package com.chicken.base;

import com.chicken.model.User;
import com.chicken.util.ContantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class BaseController extends ApplicationObjectSupport {

    public Logger log = LoggerFactory.getLogger(this.getClass());

    private static Pattern pattern = Pattern.compile("[0-9]*");

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public User getUserSession() {
        Object obj = getSession().getAttribute(ContantUtil.DEFAULT_USER_NAME);
        if (obj instanceof User) {
            return (User) obj;
        }
        return null;
    }


    /**
     * 判断用户是否登陆
     *
     * @return
     */
    public boolean userLogin(){
        Object obj = getSession().getAttribute(ContantUtil.DEFAULT_USER_NAME);
        if (obj instanceof User) {
            return true;
        }
        return false;
    }



    public HttpSession getSession() {
        return getRequest().getSession();
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse getResponse() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 保存信息到request中
     *
     * @param key
     * @param value
     */
    public void setRequestAttribute(String key, Object value) {
        this.getRequest().setAttribute(key, value);
    }

    /**
     * 获取cookie
     * @param cookieName
     * @return
     */
    protected String getCookie(String cookieName) {
        Cookie[] cookies = getRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }


    /**
     * 设置 cookie
     *
     * @param cookieName
     * @param value
     * @param age
     */
    protected void setCookie(String cookieName, String value, int age) {
        Cookie cookie = new Cookie(cookieName, value);
        cookie.setMaxAge(age);
        // cookie.setHttpOnly(true);
        getResponse().addCookie(cookie);
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
