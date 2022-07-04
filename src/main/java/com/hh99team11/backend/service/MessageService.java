package com.hh99team11.backend.service;


import com.hh99team11.backend.dto.MessageCreateDto;
import com.hh99team11.backend.dto.MessageResponseDto;
import com.hh99team11.backend.model.Message;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.repository.MessageRepository;
import com.hh99team11.backend.repository.UserRepository;
import com.hh99team11.backend.util.exception.CustomException;
import com.hh99team11.backend.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageResponseDto createMessage(MessageCreateDto messageCreateDto) {

        User sender = userRepository.findById(messageCreateDto.getSenderId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_INFO));

        User receiver = userRepository.findById(messageCreateDto.getSenderId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER_INFO));
        Message message = messageRepository.save(messageCreateDto.toEntity(sender,receiver));
        //메시지 저장 후 SSE .send 적용 해야함.

        return MessageResponseDto.create(message);

    }
}
