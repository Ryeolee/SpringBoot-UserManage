package com.user.usermanage.user.controller;


import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.dto.FindIdentifierResponseDto;
import com.user.usermanage.user.dto.VerifyEmailRequestDto;
import com.user.usermanage.user.dto.VerifyEmailResponseDto;
import com.user.usermanage.user.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/verifiy")
public class VerifiyController {

    private final Logger LOGGER = LoggerFactory.getLogger(VerifiyController.class);
    VerifyService verifyService;

    @Autowired
    VerifiyController(VerifyService verifyService){
        this.verifyService = verifyService;
    }


    @GetMapping()
    public ResponseDto identifierVerify(@Validated @RequestParam String identifier) throws CustomException {

        ResponseDto identifierVerifyResponseDto = verifyService.identifierVerify(identifier);

        LOGGER.info("아이디 중복 확인 완료");

        return identifierVerifyResponseDto;
    }


    @GetMapping("/findIdentifier")
    public FindIdentifierResponseDto identifierFind(@Validated @RequestParam String email) throws CustomException {

        FindIdentifierResponseDto identifierVerifyResponseDto = verifyService.identifierFind(email);

        LOGGER.info("아이디 중복 확인 완료");

        return identifierVerifyResponseDto;
    }


    @PostMapping("/email-issue")
    public VerifyEmailResponseDto verifyEmail(@Validated @RequestBody VerifyEmailRequestDto email) throws CustomException {

        VerifyEmailResponseDto verifyEmailResponseDto = verifyService.verifyEmail(email);

      LOGGER.info("아이디 중복 확인 완료");

        return verifyEmailResponseDto;
    }






}
