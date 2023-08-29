package com.user.usermanage.user.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class ReissueTokenResponseDto {

    private int code;
    private String message;
    private String accessToken;
    private String refreshToken;
}
