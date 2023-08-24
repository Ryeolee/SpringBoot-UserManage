package com.user.usermanage.user.controller;


import com.user.usermanage.user.dto.ChangeIdentifierRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.service.ChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/identifier")
    public ResponseDto changeIdentifier(@Validated @RequestBody ChangeIdentifierRequestDto changeIdentifierRequest){

        ResponseDto responseDto = changeService.changeIdentifier(changeIdentifierRequest);

        return responseDto;
    }
}
