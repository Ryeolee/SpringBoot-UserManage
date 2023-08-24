package com.user.usermanage.user.service;

import com.user.usermanage.user.dto.ChangeIdentifierRequestDto;
import com.user.usermanage.user.dto.ResponseDto;

public interface ChangeService {

    ResponseDto changeIdentifier(ChangeIdentifierRequestDto identifier);
}
