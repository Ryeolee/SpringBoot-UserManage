package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.Exception.Constants;
import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.config.email.MailSenderRunner;
import com.user.usermanage.user.config.security.JwtTokenProvider;
import com.user.usermanage.user.dto.ChangeNicknameRequestDto;
import com.user.usermanage.user.dto.ChangePasswordRequestDto;
import com.user.usermanage.user.dto.ChangeTemporaryPasswordRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.entity.User;
import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.ChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;


@Service
public class ChangeServiceImpl implements ChangeService {

    public PasswordEncoder passwordEncoder;
    public UserRepository userRepository;
    MailSenderRunner mailSenderRunner;

    @Autowired
    public ChangeServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailSenderRunner mailSenderRunner)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderRunner = mailSenderRunner;
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

    @Override
    public ResponseDto changeTemporaryPassword(ChangeTemporaryPasswordRequestDto changeTemporaryPasswordRequestDto) throws Exception {

        Optional<User> user = userRepository.findByIdentifierAndEmail(changeTemporaryPasswordRequestDto.getIdentifier(),changeTemporaryPasswordRequestDto.getEmail());


        if(user.isEmpty()){
            throw new CustomException(Constants.ExceptionClass.CHANGE, 404, "아이디가 옳바르지 않습니다.");
        }

        String temporaryPassword = temporaryPassword();

        userRepository.updateTemporaryPassword(passwordEncoder.encode(temporaryPassword),changeTemporaryPasswordRequestDto.getIdentifier(),changeTemporaryPasswordRequestDto.getEmail());

        mailSenderRunner.setTo(changeTemporaryPasswordRequestDto.getEmail());


        mailSenderRunner.sendEmail("임시번호 발급입니다.", temporaryPassword);

        LOGGER.info("이메일 전송 완료");

        ResponseDto responseDto = new ResponseDto();

        responseDto.setCode(200);
        responseDto.setMessage("OK");


        return responseDto;
    }

    public static String temporaryPassword() {

        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final int LENGTH = 10;

        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }








        }
