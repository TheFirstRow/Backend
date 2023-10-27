package com.thefirstrow.dreammate.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "Duplicated user email"),
    DUPLICATED_USER_NICKNAME(HttpStatus.CONFLICT, "Duplicated user nickname");

    private final HttpStatus status;
    private final String message;
}
