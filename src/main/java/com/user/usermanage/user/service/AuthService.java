package com.user.usermanage.user.service;

import com.user.usermanage.user.dto.SignUpRequestDto;
import com.user.usermanage.user.dto.ResponseDto;

public interface AuthService {


     ResponseDto signUp(SignUpRequestDto signUpRequest);
}
