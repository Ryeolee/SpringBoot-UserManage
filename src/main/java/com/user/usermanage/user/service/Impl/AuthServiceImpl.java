package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.Exception.Constants;
import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.config.security.JwtTokenProvider;
import com.user.usermanage.user.dto.*;
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

            signUpResponse.setCode(200);
            signUpResponse.setMessage("회원가입 완료");

        } else {
            throw new CustomException(Constants.ExceptionClass.AUTH, 500,"회원가입 실패");
        }

        return signUpResponse;

    }

    @Override
    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) throws CustomException{


        Optional<User> user = userRepository.findByIdentifier(signInRequestDto.getIdentifier());

        if(user.isEmpty()){
            throw new CustomException(Constants.ExceptionClass.AUTH, 404, "아이디가 옳바르지 않습니다.");
        }


        if(!passwordEncoder.matches(signInRequestDto.getPassword(), user.get().getPassword())){
            throw new CustomException(Constants.ExceptionClass.AUTH, 405, "비밀번호가 옳바르지 않습니다.");
        }

        String refreshToken = "Bearer " +jwtTokenProvider.createRereshToken();

        SignInResponseDto signInResponseDto = SignInResponseDto.builder()
                .code(200)
                .message("OK")
                .data(SignInResponseDto.Data.builder()
                        .accessToken("Bearer " +jwtTokenProvider.createAccessToken(user.get().getUserId(),user.get().getRole()))
                        .refreshToken(refreshToken)
                        .build())
                .build();


        LOGGER.info(user.get().getRole());


        redisTemplate.opsForHash().put(jwtTokenProvider.createRereshToken(),"userId", String.valueOf(user.get().getUserId()));
        redisTemplate.opsForHash().put(jwtTokenProvider.createRereshToken(),"role", String.valueOf(user.get().getRole()));



        return signInResponseDto;
    }

    @Override
    public ResponseDto logout(Long userId) throws CustomException {


        deleteValueByKey(String.valueOf(userId));

        ResponseDto logoutResponse = new ResponseDto();
        logoutResponse.setCode(200);
        logoutResponse.setMessage("완료");


        return logoutResponse;
    }

    @Override
    public ReissueTokenResponseDto reissueToken(String refreshToken) throws CustomException {


        if(!jwtTokenProvider.validateToken(refreshToken)){

            throw new CustomException(Constants.ExceptionClass.AUTH, 406, "재로그인 해주세요.");


        }

        String userId = (String) redisTemplate.opsForHash().get(refreshToken, "userId");
        String role = (String) redisTemplate.opsForHash().get(refreshToken, "role");

        ReissueTokenResponseDto reissueTokenResponse = ReissueTokenResponseDto
                .builder()
                .code(200)
                .message("OK")
                .accessToken("Bearer " +jwtTokenProvider.createAccessToken(Long.valueOf(userId),role))
                .refreshToken("Bearer " +refreshToken)
                .build();

        return reissueTokenResponse;


    }

    public void deleteValueByKey(String key) {
        redisTemplate.delete(key);
    }
}
