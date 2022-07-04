package com.hh99team11.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {

    @NotBlank(message = "전송자 PK를 입력해주세요") // 클라이언트단에서 VALUE값 받을 예정
    private Long senderId;

    @NotBlank(message = "수신자 PK를 입력해주세요")
    private Long receiverId;

    @NotBlank(message = "쪽지 내용을 입력해주세요")
    private String content;


    public MessageCreateDto toServiceDto(){
        return new MessageCreateDto(senderId,receiverId,content);
    }

}
