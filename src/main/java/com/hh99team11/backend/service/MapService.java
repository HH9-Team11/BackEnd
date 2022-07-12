package com.hh99team11.backend.service;

import com.hh99team11.backend.dto.NearByUserResponseDto;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.repository.UserCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {
    private final UserCustomRepository userRepository;
    private final EntityManager entityManager;

    public List<NearByUserResponseDto> getNearByUsers(User user) {
        Long userId = user.getId();
        Double userLat = user.getLat();
        Double userLng = user.getLng();
        Query query = entityManager.createNativeQuery("" +
                "SELECT *, ( 6371 * acos( cos( radians(:userLng) ) * cos( radians( lng ) ) * cos( radians( lat ) - radians(:userLat) ) + sin( radians(:userLng) ) * sin( radians( lng ) ) ) ) AS distance " +
                "FROM user AS u HAVING distance < 5 ORDER BY distance; " //LIMIT 0 , 5;"
                , User.class);
        query.setParameter("userLng", userLng);
        query.setParameter("userLat", userLat);
        List<User> result = query.getResultList();
        List<NearByUserResponseDto> responseDtoList = new ArrayList<>();
        for (User inUser : result) {
            responseDtoList.add(new NearByUserResponseDto(inUser));
        }
        return responseDtoList;
    }
}
