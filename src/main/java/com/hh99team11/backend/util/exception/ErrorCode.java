package com.hh99team11.backend.util.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(HttpStatus.OK,  "200", "true"),
    //문자열 체크
    NOT_VALIDCONTENT(HttpStatus.BAD_REQUEST,"400","유효하지 않는 내용입니다.");

    private final HttpStatus status;
    private final String errorCode;
    private final String errorMessage;
}