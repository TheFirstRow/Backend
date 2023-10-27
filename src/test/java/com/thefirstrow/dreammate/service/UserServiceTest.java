package com.thefirstrow.dreammate.service;

import com.thefirstrow.dreammate.exception.DreamMateApplicationException;
import com.thefirstrow.dreammate.fixture.UserEntityFixture;
import com.thefirstrow.dreammate.model.entity.UserEntity;
import com.thefirstrow.dreammate.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void 회원가입이_정상적으로_동작하는_경우() {

        String email = "email@emial.com";
        String nickname = "nickname";
        String password = "password";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(userRepository.findByNickname(nickname)).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(Optional.of(UserEntityFixture.get(email, nickname, password))); // save를 하면 저장된 엔티티 반환


        Assertions.assertDoesNotThrow(() -> userService.join(email, nickname, password));
    }

    @Test
    void 회원가입시_이메일과_닉네임이_중복되는_경우() {

        String email = "email@emial.com";
        String nickname = "nickname";
        String password = "password";

        UserEntity fixture = UserEntityFixture.get(email, nickname, password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(fixture));
        when(userRepository.findByNickname(nickname)).thenReturn(Optional.of(fixture));
        when(userRepository.save(any())).thenReturn(Optional.of(fixture));


        Assertions.assertThrows(DreamMateApplicationException.class, () -> userService.join(email, nickname, password));
    }


    @Test
    void 로그인시_이메일이_없는_경우() {

        String email = "email@emial.com";
        String password = "password";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Assertions.assertThrows(DreamMateApplicationException.class, () -> userService.login(email, password));
    }

    @Test
    void 로그인시_패스워드가_틀린_경우() {

        String email = "email@emial.com";
        String nickname = "nickname";
        String password = "password";
        String wrongPassword = "wrongPassword";

        UserEntity fixture = UserEntityFixture.get(email, nickname, password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(fixture));

        Assertions.assertThrows(DreamMateApplicationException.class, () -> userService.login(email, wrongPassword));
    }
}
