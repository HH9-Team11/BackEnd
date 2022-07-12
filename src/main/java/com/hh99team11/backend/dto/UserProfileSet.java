package com.hh99team11.backend.dto;


import com.hh99team11.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileSet {

    private Long userId;
    private String username;
    private String petName;

    public static UserProfileSet create(User user){
        return new UserProfileSet(user.getId(),user.getUsername(), user.getPetName());
    }




}
