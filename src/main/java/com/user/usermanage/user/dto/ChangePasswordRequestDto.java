package com.user.usermanage.user.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class ChangePasswordRequestDto {

    @NotBlank
    String password;
}
