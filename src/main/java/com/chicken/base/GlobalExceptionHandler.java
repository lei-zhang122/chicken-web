package com.chicken.base;

import com.chicken.common.FailureResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Created by zhanglei11527 on 2018/7/24.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常拦截，封装报错信息
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object runtimeExceptionHandler(Exception exception) {

        String ex = exception.getMessage();
        if(ex.contains("Subject does not have permission")){
            log.error(exception.getMessage(),exception);
            FailureResponse failureResponse = new FailureResponse();
            failureResponse.setErrorMessageAndCode("出现异常，请稍后再试。",1000);
            failureResponse.setErrorMessage("403 没有权限");
            return failureResponse;
        }

        // 打印 报错堆栈轨迹
        exception.printStackTrace();
        log.error(exception.getMessage(),exception);

        FailureResponse failureResponse = new FailureResponse();
        failureResponse.setErrorMessageAndCode("出现异常，请稍后再试。",1000);
        return failureResponse;
    }
}
