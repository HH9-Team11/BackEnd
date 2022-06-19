package com.hh99team11.backend.util.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(HttpStatus.OK,  "200", "true"),

    //문자열 체크
    NOT_VALIDCONTENT(HttpStatus.BAD_REQUEST,"400","유효하지 않는 내용입니다."),
    // 회원가입
    SIGNUP_MEMBERID_WRONG_INPUT(HttpStatus.BAD_REQUEST, "400", "아이디 형식을 맞춰주세요"),
    SIGNUP_PASSWORD_WRONG_INPUT(HttpStatus.BAD_REQUEST, "400", "비밀번호 형식을 맞춰주세요"),
    SIGNUP_PWCHECK_WRONG_INPUT(HttpStatus.BAD_REQUEST, "400", "비밀번호가 일치하지 않습니다"),
    SIGNUP_NICKNAME_WRONG_INPUT(HttpStatus.BAD_REQUEST, "400", "닉네임 형식을 맞춰주세요"),
    SIGNUP_MEMBERID_DUPLICATE_CHECK(HttpStatus.BAD_REQUEST, "400", "아이디 중복확인을 해주세요"),
    SIGNUP_NICKNAME_DUPLICATE_CHECK(HttpStatus.BAD_REQUEST, "400", "닉네임 중복확인을 해주세요"),
    SIGNUP_MAJOR_WRONG_INPUT(HttpStatus.BAD_REQUEST, "400", "분야를 선택해주세요"),
    SIGNUP_USERID_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "userId가 존재하지 않습니다"),

    SIGNUP_MEMBERID_DUPLICATE(HttpStatus.BAD_REQUEST, "400", "해당 아이디가 이미 존재합니다"),
    SIGNUP_MEMBERID_CORRECT(HttpStatus.OK, "200", "사용할 수 있는 아이디입니다"),
    SIGNUP_NICKNAME_DUPLICATE(HttpStatus.BAD_REQUEST, "400", "해당 닉네임이 이미 존재합니다"),
    SIGNUP_NICKNAME_CORRECT(HttpStatus.OK, "200", "사용할 수 있는 닉네임입니다"),

    // Token
    JWT_TOKEN_WRONG_SIGNATURE(HttpStatus.UNAUTHORIZED, "401", "잘못된 JWT 서명입니다"),
    JWT_TOKEN_NOT_SUPPORTED(HttpStatus.UNAUTHORIZED, "401", "지원되지 않는 JWT 토큰입니다."),
    JWT_TOKEN_WRONG_FORM(HttpStatus.UNAUTHORIZED, "401", "JWT 토큰이 잘못되었습니다."),

    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "401", "로그인이 만료되었습니다. 재로그인 하세요."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "404", "Refresh Token이 존재하지 않습니다. 로그인 해주세요"),
    REFRESH_TOKEN_NOT_MATCH(HttpStatus.UNAUTHORIZED, "401", "Refresh Token이 일치하지 않습니다"),
    REFRESH_TOKEN_REISSUE_WRONG_INPUT(HttpStatus.BAD_REQUEST, "400", "userId, accessToken, refreshToken을 입력해주세요"),

    // 기타
    NOT_FOUND_AUTHORIZATION_IN_SECURITY_CONTEXT(HttpStatus.INTERNAL_SERVER_ERROR, "998", "Security Context에 인증 정보가 없습니다."),
    NOT_FOUND_USER_INFO(HttpStatus.NOT_FOUND, "404", "해당 유저가 존재하지 않습니다"),

    // 이미지
    WRONG_INPUT_IMAGE(HttpStatus.BAD_REQUEST, "400", "이미지는 반드시 있어야 합니다"),
    IMAGE_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "400", "이미지 업로드에 실패했습니다"),
    WRONG_IMAGE_FORMAT(HttpStatus.BAD_REQUEST, "400", "지원하지 않는 파일 형식입니다");
    private final HttpStatus status;
    private final String errorCode;
    private final String errorMessage;
}