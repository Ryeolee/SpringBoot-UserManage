package com.user.usermanage.user.controller;


import com.user.usermanage.user.config.security.CustomUser;
import com.user.usermanage.user.dto.ChangeNicknameRequestDto;
import com.user.usermanage.user.dto.ChangePasswordRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.service.ChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/change")
public class ChangeController {

    ChangeService changeService;

    @Autowired
    ChangeController(ChangeService changeService){
        this.changeService = changeService;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(ChangeController.class);

    @PostMapping("/nickname")
    public ResponseDto changeNickname (
            @AuthenticationPrincipal CustomUser customUser,
            @Validated @RequestBody ChangeNicknameRequestDto changeIdentifierRequest) throws RuntimeException {


        ResponseDto responseDto = changeService.changeNickname(changeIdentifierRequest,customUser.getUserId());

        LOGGER.info("닉네임변경 완료");

        return responseDto;
    }

    @PostMapping("/password")
    public ResponseDto changePassword (
            @AuthenticationPrincipal CustomUser customUser,
            @Validated @RequestBody ChangePasswordRequestDto changePasswordRequest) throws RuntimeException {


        ResponseDto responseDto = changeService.changePassword(changePasswordRequest,customUser.getUserId());
        LOGGER.info("비밀번호변경 완료");
        return responseDto;
    }
}
