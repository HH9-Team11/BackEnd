package com.hh99team11.backend.controller;

import com.hh99team11.backend.dto.SignupRequestDto;
import com.hh99team11.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public ResponseEntity registerUser(SignupRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return new ResponseEntity(ResponseEntity.ok(), "회원가입 완료");
//    }
}
