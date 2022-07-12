package com.hh99team11.backend.controller;


import com.hh99team11.backend.dto.CommunicatorResponseDto;
import com.hh99team11.backend.dto.MessageResponseDto;
import com.hh99team11.backend.dto.MessageRequestDto;
import com.hh99team11.backend.dto.RecentMessageDto;
import com.hh99team11.backend.security.UserDetailsImpl;
import com.hh99team11.backend.service.MessageService;
import com.hh99team11.backend.util.exception.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 쪽지 상대 전체 조회 :: 받은 메시지 , 보낸 메시지
    @GetMapping("/api/communicator")
    public ResponseEntity<Object> findAllCommunicators(@AuthenticationPrincipal UserDetailsImpl userDetails)  {

        List<CommunicatorResponseDto> messagesDto = messageService.findAllCommunicators(userDetails.getUser().getId());
        return new ResponseEntity<>(new StatusResponseDto("쪽지 상대 전체를 조회합니다.", messagesDto), HttpStatus.OK);
    }

    /*
        DESC : 대상과의 전체 채팅 내역을 보내는 API
     */

    @GetMapping("/api/message/{receiverId}")
    public ResponseEntity<Object> findAllMessages(@PathVariable Long receiverId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails){

        List<MessageResponseDto>messages = messageService.findAllMessages(receiverId,userDetails.getUser().getId());

        return new ResponseEntity<>(new StatusResponseDto("상대방과의 모든 쪽지 내용을 조회합니다.",messages),HttpStatus.OK);
    }

}


