package com.user.usermanage.user.service;

import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {


     ResponseDto signUp(SignUpRequestDto signUpRequest) throws CustomException;

     SignInResponseDto signIn(SignInRequestDto signInRequestDto) throws CustomException;

     ResponseDto logout(Long userId) throws CustomException;

     ReissueTokenResponseDto reissueToken(String refreshToken) throws CustomException;
}
