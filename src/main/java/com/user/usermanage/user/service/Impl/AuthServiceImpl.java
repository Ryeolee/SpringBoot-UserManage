package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.Exception.Constants;
import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.config.security.JwtTokenProvider;
import com.user.usermanage.user.dto.SignInRequestDto;
import com.user.usermanage.user.dto.SignInResponseDto;
import com.user.usermanage.user.dto.SignUpRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.entity.User;
import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
    public UserRepository userRepository;
    public PasswordEncoder passwordEncoder;
    public JwtTokenProvider jwtTokenProvider;
    public RedisTemplate<String, String> redisTemplate;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider,  RedisTemplate<String, String> redisTemplate )
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.redisTemplate = redisTemplate;
    }
    @Override
    public ResponseDto signUp(SignUpRequestDto signUpRequest) throws CustomException {

        User user;

        user = User.builder()
                .identifier(signUpRequest.getIdentifier())
                .role(signUpRequest.getRole())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .nickname(signUpRequest.getNickname())
                .build();

        User savedUser = userRepository.save(user);
        ResponseDto signUpResponse = new ResponseDto();

        if (!savedUser.getIdentifier().isEmpty()) {
            LOGGER.info("정상 처리 완료");

            signUpResponse.setCode(200);
            signUpResponse.setMessage("회원가입 완료");

        } else {
            LOGGER.info("실패 처리 완료");
            throw new CustomException(Constants.ExceptionClass.AUTH, HttpStatus.BAD_GATEWAY,"회원가입 실패");

        }

        return signUpResponse;

    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) throws CustomException{


        Optional<User> user = userRepository.findByIdentifier(signInRequestDto.getIdentifier());

        if(user.isEmpty()){
            throw new CustomException(Constants.ExceptionClass.AUTH, HttpStatus.BAD_REQUEST, "아이디가 옳바르지 않습니다.");
        }


        if(!passwordEncoder.matches(signInRequestDto.getPassword(), user.get().getPassword())){
            throw new CustomException(Constants.ExceptionClass.AUTH, HttpStatus.BAD_REQUEST, "비밀번호가 옳바르지 않습니다.");
        }

        String refreshToken = jwtTokenProvider.createRereshToken();


        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .code(200)
                .message("OK")
                .data(SignInResponseDto.Data.builder()
                        .accessToken(jwtTokenProvider.createAccessToken(user.get().getUserId(),user.get().getRole()))
                        .refreshToken(refreshToken)
                        .build())
                .build();

        ValueOperations<String, String> redis = redisTemplate.opsForValue();

        redis.set(String.valueOf(user.get().getUserId()), refreshToken);

        LOGGER.info("레디스 토큰 장착 완료");

        return signInResponseDto;
    }




}
