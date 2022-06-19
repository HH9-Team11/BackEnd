package com.hh99team11.backend.service;

import com.hh99team11.backend.dto.SignupRequestDto;
import com.hh99team11.backend.dto.UserInfo;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public UserInfo registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(requestDto, password);
        userRepository.save(user);

        return UserInfo.builder()
                .userId(user.getId())
                .username(username)
                .build();
    }
}
