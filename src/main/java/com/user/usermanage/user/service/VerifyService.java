package com.user.usermanage.user.service;

import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.*;

public interface VerifyService {
    ResponseDto identifierVerify(String identifier) throws CustomException;

    FindIdentifierResponseDto identifierFind(String email) throws  CustomException;


    VerifyCodeResponseDto verifyCode(VerifyCodeRequestDto verifyCodeRequestDto) throws  CustomException;

    ResponseDto verifyEmail(VerifyEmailRequestDto verifyEmailRequestDto) throws  CustomException;
}
