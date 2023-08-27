package com.user.usermanage.user.controller;


import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.*;
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
    public VerifyCodeResponseDto verifyCode(@Validated @RequestBody VerifyCodeRequestDto verifyCodeRequestDto) throws CustomException {

        VerifyCodeResponseDto verifyCodeResponseDto = verifyService.verifyCode(verifyCodeRequestDto);

      LOGGER.info("인증 코드 발급 완료");

        return verifyCodeResponseDto;
    }

    @PostMapping("/email-verify")
    public ResponseDto verifyEmail(@Validated @RequestBody VerifyEmailRequestDto verifyEmailRequestDto) throws CustomException {

        ResponseDto verifyEmailResponseDto = verifyService.verifyEmail(verifyEmailRequestDto);

        LOGGER.info("인증 코드 검증 완료");

        return verifyEmailResponseDto;
    }

}
