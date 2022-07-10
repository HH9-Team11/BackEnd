package com.hh99team11.backend.service;

import com.hh99team11.backend.dto.NearByUserResponseDto;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MapService {
    private final UserRepository userRepository;

    public List<NearByUserResponseDto> getNearByUsers(User user) {
        Long userId = user.getId();
        return userRepository.findAllNearByUsers(userId);
    }
}
