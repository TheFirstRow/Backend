package com.thefirstrow.dreammate.service;


import com.thefirstrow.dreammate.exception.DreamMateApplicationException;
import com.thefirstrow.dreammate.exception.ErrorCode;
import com.thefirstrow.dreammate.model.User;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.repository.UserEntityRepository;
import com.thefirstrow.dreammate.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userRepository;
    private final BCryptPasswordEncoder encoder;



    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;


    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND, String.format("email is %s", email)));
        return User.fromEntity(userEntity);
    }

    public String login(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new DreamMateApplicationException(ErrorCode.USER_NOT_FOUND, String.format("%s not founded", email)));

        if(!encoder.matches(password, userEntity.getPassword())) {
            throw new DreamMateApplicationException(ErrorCode.INVALID_PASSWORD);
        }

        String token = JwtTokenUtils.generateAccessToken(email, secretKey, expiredTimeMs);
        return token;
    }


    @Transactional
    public User join(String email, String nickname, String password, String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            throw new DreamMateApplicationException(ErrorCode.CONFIRM_PASSWORD_NOT_MATCH);
        }

        userRepository.findByEmail(email).ifPresent(it -> {
            throw new DreamMateApplicationException(ErrorCode.DUPLICATED_USER_EMAIL, String.format("email is %s", email));
        });

        userRepository.findByNickname(nickname).ifPresent(it -> {
            throw new DreamMateApplicationException(ErrorCode.DUPLICATED_USER_NICKNAME, String.format("nickname is %s", nickname));
        });

        UserEntity savedUser = userRepository.save(UserEntity.of(email, nickname, encoder.encode(password)));
        return User.fromEntity(savedUser);
    }


}
