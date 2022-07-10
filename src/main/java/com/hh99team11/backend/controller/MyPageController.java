package com.hh99team11.backend.controller;


import com.hh99team11.backend.dto.MyPageDto;
import com.hh99team11.backend.security.UserDetailsImpl;
import com.hh99team11.backend.service.MyPageService;
import com.hh99team11.backend.util.exception.ErrorCode;
import com.hh99team11.backend.util.exception.ExceptionResponse;
import com.hh99team11.backend.util.exception.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyPageController {

    private final MyPageService myPageService;

    //동물 정보 조회
    @GetMapping("/user/info/{userId}")
    public ResponseEntity<Object> animalInfo(@PathVariable Long userId){

        MyPageDto.ResponseDto findAnimalInfo = myPageService.findAnimalInfo(userId);

        return new ResponseEntity<>(new StatusResponseDto("동물 정보 조회 성공", findAnimalInfo), HttpStatus.OK);
    }
    //동물 정보 수정
    @PatchMapping("/user/info/modify")
    public ResponseEntity<ExceptionResponse> userInfoModify(
                                                 @RequestPart MyPageDto.RequestDto requestDto,
                                                 @RequestPart(required = false) List<MultipartFile> imgs,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        myPageService.modifyAnimalInfo(requestDto,imgs,userDetails);
        return new ResponseEntity<>(new ExceptionResponse(ErrorCode.OK), HttpStatus.OK);
    }
}
