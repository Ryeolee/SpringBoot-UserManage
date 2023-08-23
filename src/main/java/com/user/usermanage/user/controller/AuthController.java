package com.user.usermanage.user.controller;


import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.SignInRequestDto;
import com.user.usermanage.user.dto.SignInResponseDto;
import com.user.usermanage.user.dto.SignUpRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/auth")
public class AuthController {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){ this.authService = authService; }

    @PostMapping(value = "/sign-up")
    public ResponseDto signUp(@Validated  @RequestBody SignUpRequestDto signUpRequest) throws CustomException {

        ResponseDto signUpResponseDto = authService.signUp(signUpRequest);
        
        LOGGER.info("회원가입 완료");
        
        return signUpResponseDto;
    }

    @PostMapping(value = "/sign-in")
    public SignInResponseDto signIn(@Validated @RequestBody SignInRequestDto signInRequestDto) throws  CustomException {

        SignInResponseDto signInResponseDto = authService.signIn(signInRequestDto);

        LOGGER.info("로그인 완료");

        return signInResponseDto;
    }





}
