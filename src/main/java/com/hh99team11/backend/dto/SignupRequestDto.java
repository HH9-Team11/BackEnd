package com.hh99team11.backend.dto;

import com.hh99team11.backend.model.enumType.PetGenderType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class SignupRequestDto {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$", message = "숫자와 문자를 포함한 6-12자리 입력해주세요.")
    private String password;

    @NotBlank(message = "강아지 이름은 필수 입력 값입니다.")
    private String petName;

//    @NotBlank(message = "강아지 크기는 필수 입력 값입니다.")
//    private PetSizeType petSizeType;

    private String petSize;

    private PetGenderType petGender;

//    private MultipartFile dogImg;

    @NotBlank(message = "강아지 나이는 필수 입력 값입니다.")
    private Long petAge;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private Double lat;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private Double lng;
}