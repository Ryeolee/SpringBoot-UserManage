package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.config.security.JwtTokenProvider;
import com.user.usermanage.user.dto.ChangeNicknameRequestDto;
import com.user.usermanage.user.dto.ChangePasswordRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.ChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ChangeServiceImpl implements ChangeService {

    public PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Autowired
    public ChangeServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(ChangeServiceImpl.class);


    @Override
    public ResponseDto changeNickname(ChangeNicknameRequestDto changeNicknameRequest, Long userId) {

        userRepository.updateNickname(changeNicknameRequest.getNickname(), userId);

        ResponseDto changeNicknameResponse = new ResponseDto();

        changeNicknameResponse.setCode(200);
        changeNicknameResponse.setMessage("완료");


        return changeNicknameResponse;
    }

    @Override
    public ResponseDto changePassword(ChangePasswordRequestDto changePasswordRequest, Long userId) {


        userRepository.updatePassword(passwordEncoder.encode(changePasswordRequest.getPassword()), userId);

        ResponseDto changePasswordResponse = new ResponseDto();

        changePasswordResponse.setCode(200);
        changePasswordResponse.setMessage("완료");


        return changePasswordResponse;
    }
}
