package com.user.usermanage.user.service;

import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.dto.FindIdentifierResponseDto;

public interface VerifyService {
    ResponseDto identifierVerify(String identifier) throws CustomException;

    FindIdentifierResponseDto identifierFind(String email) throws  CustomException;
}
