package com.hh99team11.backend.repository;

import com.hh99team11.backend.dto.NearByUserResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hh99team11.backend.model.QImg.img;
import static com.hh99team11.backend.model.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NearByUserResponseDto> findAllNearByUsers(Long userId) {
        List<NearByUserResponseDto> nearByUserList = jpaQueryFactory
                .select(Projections.constructor(NearByUserResponseDto.class,
                        user.id,
                        user.username,
                        user.petName,
                        user.username, //Todo imgUrl 로 바꿔야함
                        user.petSizeType,
                        user.lat,
                        user.lng))
                .from(user)
                .where(equalsWithUserId(userId))
                .fetch();
        return nearByUserList;
    }

    private BooleanExpression equalsWithUserId(Long userId) {
        return user.id.eq(userId);
    }
}
