package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.enumType.PetGenderType;
import com.hh99team11.backend.model.enumType.PetSizeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//이너클래스 패턴
public class MyPageDto {


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestDto {

        private String petName;
        private PetSizeType petSizeType;
        private Long petAge;
        private String address;
        private Double lat;
        private Double lng;
        private List<ImgUrlDto> currentImgUrl; //삭제해야할 기존 URL

    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDto{

        private String petName;
        private PetSizeType petSizeType;
        private Long petAge;
        private String address;
        private Double lat;
        private Double lng;
        private List<String> petImgUrlList;
        private PetGenderType petGenderType;
    }

}
