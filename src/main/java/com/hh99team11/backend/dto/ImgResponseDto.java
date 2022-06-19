package com.hh99team11.backend.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImgResponseDto {
    private Long postId;
    private String imgUrl;

}