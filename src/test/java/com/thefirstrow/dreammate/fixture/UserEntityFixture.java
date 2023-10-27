package com.thefirstrow.dreammate.fixture;

import com.thefirstrow.dreammate.model.entity.UserEntity;

public class UserEntityFixture {

    public static UserEntity get(String email, String nickname, String password) {
        UserEntity result = new UserEntity();
        result.setId(1L);
        result.setEmail(email);
        result.setNickname(nickname);
        result.setPassword(password);
        return result;
    }
}
