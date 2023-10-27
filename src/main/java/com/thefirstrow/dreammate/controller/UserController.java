package com.thefirstrow.dreammate.controller;

import com.thefirstrow.dreammate.controller.request.UserJoinRequest;
import com.thefirstrow.dreammate.controller.response.Response;
import com.thefirstrow.dreammate.controller.response.UserJoinResponse;
import com.thefirstrow.dreammate.model.entity.User;
import com.thefirstrow.dreammate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        User joinedDto = userService.join(request.getEmail(), request.getNickname(), request.getPassword());
        return Response.success(UserJoinResponse.fromUser(joinedDto));
    }
}