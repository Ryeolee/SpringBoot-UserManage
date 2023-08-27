package com.user.usermanage.user.Exception;


import org.springframework.http.HttpStatus;


public class CustomException extends Exception{



    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus = HttpStatus.OK;
    private int code;

    private String message;

    public CustomException(Constants.ExceptionClass exceptionClass, int code,
                           String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.code = code;
        this.message = message;
    }

    public Constants.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

//    public String getHttpStatusType() {
//        return httpStatus.getReasonPhrase();
//    }
//
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }



}
