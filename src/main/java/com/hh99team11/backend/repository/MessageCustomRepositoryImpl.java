package com.hh99team11.backend.repository;


import com.hh99team11.backend.dto.FindAllMessageDto;

import java.util.List;

public class MessageCustomRepositoryImpl implements MessageCustomRepository{

    @Override
    public List<FindAllMessageDto> findAllCommunicatorsByReceiverId(Long receiverId) {
        return null;
    }
}
