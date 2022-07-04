package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.Message;
import com.hh99team11.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageCreateDto {

    private Long senderId;

    private Long receiverId;

    private String content;

    public Message toEntity(User sender, User receiver) {
        return Message.builder()
                .content(content)
                .sender(sender)
                .receiver(receiver)
                .isRead(false)
                .build();
    }
}
