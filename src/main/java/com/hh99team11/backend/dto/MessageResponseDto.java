package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {

    private Long messageId;

    private String senderName;

    private String receiverName;

    private String content;

    private String createAt;


    public static MessageResponseDto create(Message message){
        return new MessageResponseDto(message.getId(), message.getSender().getUsername(),
                message.getReceiver().getUsername(), message.getContent(), timeConversion(message.getCreatedAt()));
    }

    private static String timeConversion(LocalDateTime createAt){
        return "오후 " + createAt.getHour() + ":" + String.format("%02d", createAt.getMinute());
    }
}
