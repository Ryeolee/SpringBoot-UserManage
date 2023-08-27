package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.Exception.Constants;
import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.config.email.MailSenderRunner;
import com.user.usermanage.user.dto.*;
import com.user.usermanage.user.entity.User;
import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;


@Service
public class VerfiyServicecImpl implements VerifyService {

    private final Logger LOGGER = LoggerFactory.getLogger(VerfiyServicecImpl.class);
    UserRepository userRepository;

    MailSenderRunner mailSenderRunner;

    public RedisTemplate<String, String> redisTemplate;


    @Autowired
    public VerfiyServicecImpl(UserRepository userRepository, MailSenderRunner mailSenderRunner, RedisTemplate<String, String> redisTemplate){
        this.userRepository = userRepository;
        this.mailSenderRunner = mailSenderRunner;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public ResponseDto identifierVerify(String identifier) throws CustomException {

       Optional<User> user = userRepository.findByIdentifier(identifier);

        ResponseDto idenfierVerifyResponse = new ResponseDto();

        LOGGER.info(String.valueOf(user));


        if(user.isPresent()){
           throw new CustomException(Constants.ExceptionClass.VERIFY,404 ,"아이디가 중복됩니다.");
        }

        idenfierVerifyResponse.setCode(200);
        idenfierVerifyResponse.setMessage("아이디 사용 가능");
        return idenfierVerifyResponse;
    }

    @Override
    public FindIdentifierResponseDto identifierFind(String email) throws CustomException {

        Optional<User> user = userRepository.getByEmail(email);

        FindIdentifierResponseDto findIdentifierResponseDto = new FindIdentifierResponseDto();

        findIdentifierResponseDto.setCode(200);
        findIdentifierResponseDto.setMessage("OK");
        findIdentifierResponseDto.setIdentifier(user.get().getIdentifier());
        return findIdentifierResponseDto;
    }

    @Override
    public VerifyCodeResponseDto verifyCode(VerifyCodeRequestDto verifyCodeRequestDto) throws CustomException {

        ValueOperations<String, String> redis = redisTemplate.opsForValue();

        try {

            mailSenderRunner.setTo(verifyCodeRequestDto.getEmail());

            String verifyNumber = generateVerifyNumber();

            redis.set(verifyCodeRequestDto.getEmail(),verifyNumber);

            setKeyExpiration(verifyCodeRequestDto.getEmail(),180);


            mailSenderRunner.sendEmail("인증번호", verifyNumber);

            LOGGER.info("이메일 전송 완료");


            VerifyCodeResponseDto verifyEmailResponse = new VerifyCodeResponseDto();

            verifyEmailResponse.setMessage("OK");
            verifyEmailResponse.setCode(200);
            verifyEmailResponse.setVerifyNumber(verifyNumber);


            return verifyEmailResponse;


        } catch (Exception e) {
            // 이메일 전송에 실패한 경우에 대한 처리
            LOGGER.error("이메일 전송 실패: " + e.getMessage());
            throw new CustomException(Constants.ExceptionClass.VERIFY, 500,"이메일 전송 실패");
        }

    }

    @Override
    public ResponseDto verifyEmail(VerifyEmailRequestDto verifyEmailRequestDto) throws CustomException {

        ValueOperations<String, String> redis = redisTemplate.opsForValue();

        String code = redis.get(verifyEmailRequestDto.getEmail());

        LOGGER.info(code);

        if(code == null){
            throw new CustomException(Constants.ExceptionClass.VERIFY,402,"검증 유효시간이 지났습니다.");
        }

        if(code.equals(verifyEmailRequestDto.getCode())){
            ResponseDto verifyEmailResponse = new ResponseDto();

            verifyEmailResponse.setCode(200);
            verifyEmailResponse.setMessage("인증 성공");

            return verifyEmailResponse;


        }

        throw new CustomException(Constants.ExceptionClass.VERIFY, 407," 코드가 옳바르지 않습니다.");
    }

    public static String generateVerifyNumber() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomNumber = random.nextInt(max - min + 1) + min;

        // 생성된 숫자를 6자리 문자열로 변환하여 반환
        return String.format("%06d", randomNumber);
    }

    public void setKeyExpiration(String key, long seconds) {
        try {
            // 유효 시간을 초 단위로 설정
            redisTemplate.expire(key, Duration.ofSeconds(seconds));
        } catch (Exception e) {
            // 오류 처리
            e.printStackTrace();
        }
    }



}
