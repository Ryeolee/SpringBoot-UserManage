package com.user.usermanage.user.common;

public enum SignUpResponse {

    SUCCESS(200, "Success"), FAIL(500, "Fail");

    int code;
    String msg;
    SignUpResponse(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }
}

