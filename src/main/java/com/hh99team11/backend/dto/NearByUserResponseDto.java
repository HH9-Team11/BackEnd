package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.User;
import com.hh99team11.backend.model.enumType.PetSizeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NearByUserResponseDto {
    private Long userId;
    private String username;
    private String petName;
    private String petImg;
    private PetSizeType petSize;
    private Double lat;
    private Double lng;
//    private Double dist;

    public NearByUserResponseDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.petName = user.getPetName();
//        this.petImg = user.getUserImgList().get(0).getImgUrl();
        this.petSize = user.getPetSizeType();
        this.lat = user.getLat();
        this.lng = user.getLng();
    }
}
