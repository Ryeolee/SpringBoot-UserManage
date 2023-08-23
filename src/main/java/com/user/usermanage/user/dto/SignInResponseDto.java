package com.user.usermanage.user.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class SignInResponseDto {

    private int code;
    private String message;
    private Data data;


    @lombok.Data
    @lombok.Builder
    public static class Data {
        private String accessToken;
        private String refreshToken;
    }
}

