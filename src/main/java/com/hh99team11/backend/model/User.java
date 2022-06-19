package com.hh99team11.backend.model;


import com.hh99team11.backend.dto.SignupRequestDto;
import com.hh99team11.backend.model.enumType.PetSizeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "\"USER\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String petName;

    @Enumerated(EnumType.STRING)
    @Column
    private PetSizeType petSizeType; //펫 크기

    @Column
    private Long dogAge;

    @Column
    private String address;

    @Column
    private Double lat; //위도

    @Column
    private Double lng; //경도

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(SignupRequestDto user, String password){
        this.username = user.getUsername();
        this.password = password;
        this.petName = user.getPetName();
        this.petSizeType = user.getPetSizeType();
        this.dogAge = user.getDogAge();
        this.address = user.getAddress();
        this.lat = user.getLat();
        this.lng = user.getLng();
    }


}
