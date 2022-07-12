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

    private String senderName;

    private String receiverName;

    private String content;

    public static MessageResponseDto create(Message message){
        return new MessageResponseDto(message.getId(), message.getSender().getUsername(),
                message.getReceiver().getUsername(), message.getContent());
    }
}
