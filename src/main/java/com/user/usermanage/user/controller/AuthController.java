package com.user.usermanage.user.controller;


import com.user.usermanage.user.dto.SignUpRequestDto;
import com.user.usermanage.user.dto.SignUpResponseDto;
import com.user.usermanage.user.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService){ this.authService = authService; }

    @PostMapping(value = "/sign-up")
    public SignUpResponseDto signUp( @Validated  @RequestBody SignUpRequestDto signUpRequest){

        SignUpResponseDto signUpResponseDto = authService.signUp(signUpRequest);
        
        LOGGER.info("회원가입 완료");
        
        return signUpResponseDto;
    }
}
