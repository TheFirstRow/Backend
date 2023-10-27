package com.thefirstrow.dreammate.service;

import com.thefirstrow.dreammate.exception.DreamMateApplicationException;
import com.thefirstrow.dreammate.exception.ErrorCode;
import com.thefirstrow.dreammate.model.entity.User;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User join(String email, String nickname, String password) {

        // 회원가입하려는 이메일로 회원가입된 유저가 있는지
        userRepository.findByEmail(email).ifPresent(it -> {
            throw new DreamMateApplicationException(ErrorCode.DUPLICATED_USER_EMAIL, String.format("userEmail is %s", email));
        });

        // 회원가입하려는 닉네임으로 회원가입된 유저가 있는지
        userRepository.findByNickname(nickname).ifPresent(it -> {
            throw new DreamMateApplicationException();
        });


        // 회원가입 진행 = 유저 등록
        UserEntity savedUser = userRepository.save(UserEntity.of(email, nickname, password));

        return User.fromEntity(savedUser);
    }

    public String login(String email, String password) {

        // 회원가입 여부 체크
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DreamMateApplicationException());

        // 비밀번호 체크
        if(!userEntity.getPassword().equals(password)) {
            throw new DreamMateApplicationException();
        }

        // 토큰 생성

        return "";
    }
}
