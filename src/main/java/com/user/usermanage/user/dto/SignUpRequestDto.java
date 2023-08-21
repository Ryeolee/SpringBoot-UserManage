package com.user.usermanage.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Builder
@AllArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    private String identifier;

    @NotBlank
    private String role;

    @NotBlank
    private String password;

    @Email
    private String email;

    @NotBlank
    private String nickname;


}
