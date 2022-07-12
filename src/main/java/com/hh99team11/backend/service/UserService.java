package com.hh99team11.backend.service;

import com.hh99team11.backend.dto.ImgDto;
import com.hh99team11.backend.dto.SignupRequestDto;
import com.hh99team11.backend.dto.UserInfo;
import com.hh99team11.backend.model.Img;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.repository.ImgRepository;
import com.hh99team11.backend.repository.UserRepository;
import com.hh99team11.backend.util.exception.CustomException;
import com.hh99team11.backend.util.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;
    private final ImgRepository imgRepository;

    @Transactional
    public UserInfo registerUser(SignupRequestDto requestDto, MultipartFile img) throws IOException {
        log.info("UserService registerUser");
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new CustomException(ErrorCode.SIGNUP_MEMBERID_DUPLICATE);
        }
        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(requestDto, password);
        userRepository.save(user);

        ImgDto imgDto = fileUploadService.uploadImage(img);

        Img savedImg = imgDto.toEntity(user);
        imgRepository.save(savedImg);

        return UserInfo.builder()
                .userId(user.getId())
                .username(username)
                .build();
    }
}
