package com.hh99team11.backend.repository;

import com.hh99team11.backend.dto.RecentMessageDto;

import java.util.List;

public interface MessageCustomRepository {

    List<RecentMessageDto> findAllCommunicatorsByReceiverId(Long receiverId);
}
