package com.hh99team11.backend.repository;

import com.hh99team11.backend.dto.NearByUserResponseDto;

import java.util.List;

public interface UserCustomRepository {

    List<NearByUserResponseDto> findAllNearByUsers(Long userId);
}
