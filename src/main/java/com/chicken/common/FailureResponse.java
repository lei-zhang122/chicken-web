package com.chicken.common;


public class FailureResponse {
    private Result result ;

    public FailureResponse(){
        result = new Result();
        result.setCode(1);
        result.setMessage("服务器错误");
    }

    public FailureResponse(String errorMessage){
        result = new Result();
        result.setCode(1);
        result.setMessage(errorMessage);
    }

    public FailureResponse(String errorMessage,Integer code){
        result = new Result();
        result.setCode(code);
        result.setMessage(errorMessage);
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setErrorMessage(String errorMessage){
        this.result.setMessage(errorMessage);
    }

    public void setErrorMessageAndCode(String errorMessage,Integer code){
        this.result.setMessage(errorMessage);
        this.result.setCode(code);
    }
}
