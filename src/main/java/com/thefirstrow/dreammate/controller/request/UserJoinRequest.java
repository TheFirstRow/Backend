package com.thefirstrow.dreammate.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {

    private String email;
    private String nickname;
    private String password;

}
