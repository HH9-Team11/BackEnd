package com.hh99team11.backend.model;



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
public class User {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String memberId;

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
    private Long lat; //위도

    @Column
    private Long lng; //경도



}
