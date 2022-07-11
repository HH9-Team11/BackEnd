package com.hh99team11.backend.service;


import com.hh99team11.backend.dto.CommunicatorResponseDto;
import com.hh99team11.backend.dto.MessageCreateDto;
import com.hh99team11.backend.dto.MessageResponseDto;
import com.hh99team11.backend.dto.RecentMessageDto;
import com.hh99team11.backend.model.Message;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.repository.MessageCustomRepository;
import com.hh99team11.backend.repository.MessageRepository;
import com.hh99team11.backend.repository.UserRepository;
import com.hh99team11.backend.util.exception.CustomException;
import com.hh99team11.backend.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageCustomRepository messageCustomRepository;
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

    @Transactional // 받는 이와 보내는 이가 현재 로그인 대상의 userPK 에 근거하여 나누어서 결과값을 전달한다.
    public List<CommunicatorResponseDto> findAllCommunicators(Long userId) {
        List<RecentMessageDto> messages= messageCustomRepository.findAllCommunicatorsByReceiverId(userId);

        return messages.stream()
                .map(CommunicatorResponseDto::create)
                .collect(Collectors.toList());

    }
}
