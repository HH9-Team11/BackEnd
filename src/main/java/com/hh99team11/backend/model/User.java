package com.hh99team11.backend.model;


import com.hh99team11.backend.dto.MyPageDto;
import com.hh99team11.backend.model.enumType.PetSizeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private Long animalAge;

    @Column
    private String address;

    @Column
    private Long lat; //위도

    @Column
    private Long lng; //경도

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Img> userImgList;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void updateInfo(MyPageDto.RequestDto requestDto , List<Img>userImgList){
        this.petName = requestDto.getPetName();
        this.petSizeType = requestDto.getPetSizeType();
        this.animalAge = requestDto.getAnimalAge();
        this.address = requestDto.getAddress();
        this.lat = requestDto.getLat();
        this.lng = requestDto.getLng();
        this.userImgList = userImgList;
        for(Img userImg : userImgList){
            userImg.updateUser(this);
        }

    }


}
