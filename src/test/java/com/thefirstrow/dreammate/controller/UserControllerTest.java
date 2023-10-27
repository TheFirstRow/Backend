package com.thefirstrow.dreammate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thefirstrow.dreammate.controller.request.UserJoinRequest;
import com.thefirstrow.dreammate.controller.request.UserLoginRequest;
import com.thefirstrow.dreammate.exception.DreamMateApplicationException;
import com.thefirstrow.dreammate.model.entity.User;
import com.thefirstrow.dreammate.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void 회원가입() throws Exception{
        String email = "email@emial.com";
        String nickname = "nickname";
        String password = "password";

        when(userService.join(email, nickname, password)).thenReturn(mock(User.class));

        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(email, nickname, password)))
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 회원가입시_이미_회원가입된_email로_회원가입을_하는_경우_에러반환() throws Exception{
        String email = "email@emial.com";
        String nickname = "nickname";
        String password = "password";

        when(userService.join(email, nickname, password)).thenThrow(new DreamMateApplicationException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(email, nickname, password)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    public void 회원가입시_이미_회원가입된_닉네임으로_회원가입을_하는_경우_에러반환() throws Exception{
        String email = "email@emial.com";
        String nickname = "nickname";
        String password = "password";

        when(userService.join(email, nickname, password)).thenThrow(new DreamMateApplicationException());

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(email, nickname, password)))
                ).andDo(print())
                .andExpect(status().isConflict());
    }


    @Test
    public void 로그인() throws Exception{
        String email = "email@emial.com";
        String password = "password";

        when(userService.login(email, password)).thenReturn("test_token");

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(email, password)))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void 로그인시_회원가입이_안된_email을_입력할경우_에러반환() throws Exception{
        String email = "email@emial.com";
        String password = "password";

        when(userService.login(email, password)).thenThrow(new DreamMateApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(email, password)))
                ).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void 로그인시_틀린_password를_입력할경우_에러반환() throws Exception{
        String email = "email@emial.com";
        String password = "password";

        when(userService.login(email, password)).thenThrow(new DreamMateApplicationException());

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(email, password)))
                ).andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
