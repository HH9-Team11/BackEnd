package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/*
    대화 상대 리스트 조회값 DTO
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindAllMessageDto {

    private Long userId;

    private User sender;
    private User receiver;

    private String content;
    private Long unReadCount; // 해당 대화 상대와 읽지 않은 메시지 갯수 ,
}
