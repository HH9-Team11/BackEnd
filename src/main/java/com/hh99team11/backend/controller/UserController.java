package com.hh99team11.backend.controller;

import com.hh99team11.backend.dto.SignupRequestDto;
import com.hh99team11.backend.dto.UserInfo;
import com.hh99team11.backend.service.UserService;
import com.hh99team11.backend.util.exception.StatusResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입 요청 처리
    @PostMapping(value = "/user/signup", headers = {"content-type=multipart/*"})
    public ResponseEntity<Object> registerUser(
            @RequestPart("data") SignupRequestDto data,
            @RequestPart("img") MultipartFile img) throws IOException {
        log.info("POST /user/signup");
        UserInfo userInfo = userService.registerUser(data, img);
        return new ResponseEntity<>(new StatusResponseDto("회원가입 1차 성공", userInfo), HttpStatus.OK);
    }

}
