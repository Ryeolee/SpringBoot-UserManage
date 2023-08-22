package com.user.usermanage.user.common;

public enum IdentifierVerifyResponse {

    SUCCESS(200, "아이디 사용 가능"), FAIL(400, "아이디가 중복됩니다.");

    int code;
    String msg;
    IdentifierVerifyResponse(int code, String msg){
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
