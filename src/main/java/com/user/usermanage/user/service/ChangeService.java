package com.user.usermanage.user.service;

import com.user.usermanage.user.Exception.CustomException;
import com.user.usermanage.user.dto.ChangeNicknameRequestDto;
import com.user.usermanage.user.dto.ChangePasswordRequestDto;
import com.user.usermanage.user.dto.ChangeTemporaryPasswordRequestDto;
import com.user.usermanage.user.dto.ResponseDto;

public interface ChangeService {

    ResponseDto changeNickname(ChangeNicknameRequestDto ChangeNicknameRequest, Long userId);

    ResponseDto changePassword(ChangePasswordRequestDto ChangeNicknameRequest, Long userId);

    ResponseDto changeTemporaryPassword(ChangeTemporaryPasswordRequestDto changeTemporaryPasswordRequestDto) throws Exception;
}
