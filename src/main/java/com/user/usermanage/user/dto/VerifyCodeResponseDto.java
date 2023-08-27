package com.user.usermanage.user.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class VerifyCodeResponseDto {

    private int code;
    private String message;
    private String verifyNumber;
}
