package com.user.usermanage.user.service;

import com.user.usermanage.user.dto.ChangeNicknameRequestDto;
import com.user.usermanage.user.dto.ChangePasswordRequestDto;
import com.user.usermanage.user.dto.ResponseDto;

public interface ChangeService {

    ResponseDto changeNickname(ChangeNicknameRequestDto ChangeNicknameRequest, Long userId);

    ResponseDto changePassword(ChangePasswordRequestDto ChangeNicknameRequest, Long userId);
}
