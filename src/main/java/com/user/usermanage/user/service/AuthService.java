package com.user.usermanage.user.service;

import com.user.usermanage.user.dto.SignUpRequestDto;
import com.user.usermanage.user.dto.SignUpResponseDto;

public interface AuthService {


     SignUpResponseDto signUp(SignUpRequestDto signUpRequest);
}
