package com.user.usermanage.user.service;

import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.dto.SignUpRequestDto;

public interface VerifyService {
    ResponseDto idenfierVerify(String idenfier);
}
