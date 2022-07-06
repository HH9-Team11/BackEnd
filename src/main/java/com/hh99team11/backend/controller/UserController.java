package com.hh99team11.backend.controller;

import com.hh99team11.backend.dto.SignupRequestDto;
import com.hh99team11.backend.dto.UserInfo;
import com.hh99team11.backend.service.UserService;
import com.hh99team11.backend.util.exception.StatusResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public ResponseEntity<Object> registerUser(SignupRequestDto requestDto) throws IOException {
        UserInfo userInfo = userService.registerUser(requestDto);
        return new ResponseEntity<>(new StatusResponseDto("회원가입 1차 성공", userInfo), HttpStatus.OK);
    }

}
