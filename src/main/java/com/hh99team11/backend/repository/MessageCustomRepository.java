package com.hh99team11.backend.repository;

import com.hh99team11.backend.dto.FindAllMessageDto;

import java.util.List;

public interface MessageCustomRepository {

    List<FindAllMessageDto> findAllCommunicatorsByReceiverId(Long receiverId);
}
