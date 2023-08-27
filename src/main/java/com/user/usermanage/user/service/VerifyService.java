package com.user.usermanage.user.service;

import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.dto.FindIdentifierResponseDto;
import com.user.usermanage.user.dto.VerifyEmailRequestDto;
import com.user.usermanage.user.dto.VerifyEmailResponseDto;

public interface VerifyService {
    ResponseDto identifierVerify(String identifier) throws CustomException;

    FindIdentifierResponseDto identifierFind(String email) throws  CustomException;


    VerifyEmailResponseDto verifyEmail(VerifyEmailRequestDto email) throws  CustomException;
}
