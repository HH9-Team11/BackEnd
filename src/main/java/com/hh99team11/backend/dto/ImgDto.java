package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.Img;
import com.hh99team11.backend.model.Message;
import com.hh99team11.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgDto {
    private String imgName;
    private String imgUrl;

    public Img toEntity(User user) {
        return Img.builder()
                .imgName(imgName)
                .imgUrl(imgUrl)
                .user(user)
                .build();
    }
}