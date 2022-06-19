package com.hh99team11.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfo {
    private Long userId;
    private String username;
}
