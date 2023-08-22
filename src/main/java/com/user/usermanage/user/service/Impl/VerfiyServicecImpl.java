package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.common.IdentifierVerifyResponse;
import com.user.usermanage.user.controller.VerifiyController;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.entity.User;
import com.user.usermanage.user.repository.UserRepository;
import com.user.usermanage.user.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class VerfiyServicecImpl implements VerifyService {

    private final Logger LOGGER = LoggerFactory.getLogger(VerfiyServicecImpl.class);
    UserRepository userRepository;


    @Autowired
    public VerfiyServicecImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public ResponseDto idenfierVerify(String identifier) {

        User user = userRepository.findByIdentifier(identifier);

        ResponseDto idenfierVerifyResponse = new ResponseDto();

        LOGGER.info(String.valueOf(user));


        if(user != null){
            setFailResult(idenfierVerifyResponse);
        }else{
            setSuccessResult(idenfierVerifyResponse);
        }
        return idenfierVerifyResponse;
    }


    private void setSuccessResult(ResponseDto result){
        result.setSuccess(true);
        result.setCode(IdentifierVerifyResponse.SUCCESS.getCode());
        result.setMsg(IdentifierVerifyResponse.SUCCESS.getMsg());
    }

    private void setFailResult(ResponseDto result){
        result.setSuccess(false);
        result.setCode(IdentifierVerifyResponse.FAIL.getCode());
        result.setMsg(IdentifierVerifyResponse.FAIL.getMsg());
    }
}
