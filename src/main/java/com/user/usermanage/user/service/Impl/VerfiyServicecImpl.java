package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.Exception.Constants;
import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.entity.User;
import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class VerfiyServicecImpl implements VerifyService {

    private final Logger LOGGER = LoggerFactory.getLogger(VerfiyServicecImpl.class);
    UserRepository userRepository;


    @Autowired
    public VerfiyServicecImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public ResponseDto identifierVerify(String identifier) throws CustomException {

       Optional<User> user = userRepository.findByIdentifier(identifier);

        ResponseDto idenfierVerifyResponse = new ResponseDto();

        LOGGER.info(String.valueOf(user));


        if(user.isPresent()){
           throw new CustomException(Constants.ExceptionClass.VERIFY, HttpStatus.BAD_REQUEST,"아이디가 중복됩니다.");
        }

        idenfierVerifyResponse.setCode(200);
        idenfierVerifyResponse.setMessage("아이디 사용 가능");
        return idenfierVerifyResponse;
    }



}
