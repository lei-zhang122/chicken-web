package com.chicken.base;

import com.chicken.common.FailureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhanglei11527 on 2018/7/24.
 */
@RestController
public class MainsiteErrorController implements  ErrorController {

    Logger log = LoggerFactory.getLogger(MainsiteErrorController.class);

    @RequestMapping(value = "/error")
    @ResponseBody
    public Object handleError(HttpServletRequest request){
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        FailureResponse failureResponse = new FailureResponse();
        log.info("请求错误，请稍后再试，错误代码："+statusCode);
        if(statusCode == 401){
            failureResponse.setErrorMessageAndCode("请求错误，请稍后再试。",401);
        }else if(statusCode == 404){
            failureResponse.setErrorMessageAndCode("请求错误，请稍后再试。",404);
        }else if(statusCode == 403){
            failureResponse.setErrorMessageAndCode("请求错误，请稍后再试。",403);
        }else{
            failureResponse.setErrorMessageAndCode("请求错误，请稍后再试。",500);
        }

        return failureResponse;

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
