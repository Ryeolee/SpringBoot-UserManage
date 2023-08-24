package com.user.usermanage.user.service.Impl;

import com.user.usermanage.user.controller.ChangeController;
import com.user.usermanage.user.dto.ChangeIdentifierRequestDto;
import com.user.usermanage.user.dto.ResponseDto;
import com.user.usermanage.user.service.ChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class ChangeServiceImpl implements ChangeService {

    private final Logger LOGGER = LoggerFactory.getLogger(ChangeServiceImpl.class);
    @Override
    public ResponseDto changeIdentifier(ChangeIdentifierRequestDto identifier) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 현재 사용자의 이름 (예: 사용자 이름 또는 ID) 가져오기
        String currentUserName = authentication.getName();

        LOGGER.info(currentUserName);


        return null;
    }
}
