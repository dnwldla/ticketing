package com.example.ticketing.api.member.dto.req;

import lombok.Getter;

@Getter
public class LoginReqDto {
    private String email;
    private String password;
}
