package com.chicken.common;


/**
 * Created by liweibo on 17/5/23.
 */
public class Result {

    // 0 -> success
    // 1 -> failure
    private int code;

    // if code is 0, the message will always be '成功',
    // otherwise the message will be error content
    private String message;

    public Result(){

    }
    public Result(int code, String message){
        this.code = code;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
