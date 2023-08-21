package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.common.SignUpResponse;
import com.user.usermanage.user.dto.SignUpRequestDto;
import com.user.usermanage.user.dto.SignUpResponseDto;
import com.user.usermanage.user.entity.User;
import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequest) {

        User user;

        user = User.builder()
                .identifier(signUpRequest.getIdentifier())
                .role(signUpRequest.getRole())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .nickname(signUpRequest.getNickname())
                .build();

        User savedUser = userRepository.save(user);
        SignUpResponseDto signUpResponse = new SignUpResponseDto();

        if (!savedUser.getIdentifier().isEmpty()) {
            LOGGER.info("정상 처리 완료");
            setSuccessResult(signUpResponse);
        } else {
            LOGGER.info("실패 처리 완료");
            setFailResult(signUpResponse);
        }

        return signUpResponse;

    }

    private void setSuccessResult(SignUpResponseDto result){
        result.setSuccess(true);
        result.setCode(SignUpResponse.SUCCESS.getCode());
        result.setMsg(SignUpResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResponseDto result){
        result.setSuccess(false);
        result.setCode(SignUpResponse.FAIL.getCode());
        result.setMsg(SignUpResponse.FAIL.getMsg());
    }




}
