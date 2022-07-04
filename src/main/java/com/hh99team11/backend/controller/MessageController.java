package com.hh99team11.backend.controller;


import com.hh99team11.backend.dto.MessageResponseDto;
import com.hh99team11.backend.dto.MessageRequestDto;
import com.hh99team11.backend.service.MessageService;
import com.hh99team11.backend.util.exception.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // 쪽지 보내기
    @PostMapping("/api/message")
    public ResponseEntity<Object> sendMessage(@RequestBody MessageRequestDto messageRequestDto)  {
        MessageResponseDto messageResponseDto = messageService.createMessage(messageRequestDto.toServiceDto());
        return new ResponseEntity<>(new StatusResponseDto("성공적으로 쪽지를 보냈습니다.", messageResponseDto), HttpStatus.OK);
    }

    // 쪽지 가져오기
    @GetMapping("/api/message")
    public ResponseEntity<Object> receiveMessage()  {

        return new ResponseEntity<>(new StatusResponseDto("", ""), HttpStatus.OK);
    }
}


