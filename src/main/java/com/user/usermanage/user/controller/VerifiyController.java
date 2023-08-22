package com.user.usermanage.user.controller;


import com.user.usermanage.user.dto.ResponseDto;
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
    public ResponseDto identifierVerify(@Validated @RequestParam String identifier){

        ResponseDto identifierVerifyResponseDto = verifyService.idenfierVerify(identifier);

        LOGGER.info("아이디 중복 확인 완료");

        return identifierVerifyResponseDto;
    }
}
