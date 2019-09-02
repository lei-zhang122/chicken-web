package com.chicken.base;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 登陆拦截
 * @author: zhanglei
 * @create: 2018-08-16 19:17
 **/
@Configuration
public class WebSecurityConfig implements  WebMvcConfigurer {
    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/")
        .addResourceLocations("/js/**").addResourceLocations("/img/**").addResourceLocations("/css/**");
        registry.addResourceHandler("/OTA/**").addResourceLocations("file:///usr/local/OTA/");//D:/OTA/
    }

}
