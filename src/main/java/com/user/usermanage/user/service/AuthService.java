package com.user.usermanage.user.service;

import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.SignInRequestDto;
import com.user.usermanage.user.dto.SignInResponseDto;
import com.user.usermanage.user.dto.SignUpRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {


     ResponseDto signUp(SignUpRequestDto signUpRequest) throws CustomException;

     SignInResponseDto signIn(SignInRequestDto signInRequestDto) throws CustomException;


}
