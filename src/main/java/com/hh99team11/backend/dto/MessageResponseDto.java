package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {

    private Long messageId;

    private Long senderId;

    private Long receiverId;

    private String content;

    public static MessageResponseDto create(Message message){
        return new MessageResponseDto(message.getId(), message.getSender().getId(),
                message.getReceiver().getId(), message.getContent());
    }
}
