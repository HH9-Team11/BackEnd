package com.hh99team11.backend.dto;

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
    private String dogName;
    private String dogImg;
    private PetSizeType dogSize;
    private Double lat;
    private Double lng;
    private Double dist;
}
