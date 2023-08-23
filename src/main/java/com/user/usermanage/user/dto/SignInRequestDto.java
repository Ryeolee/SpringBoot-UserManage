package com.user.usermanage.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class SignInRequestDto {

    @NotBlank
    private String identifier;

    @NotBlank
    private String password;


}
