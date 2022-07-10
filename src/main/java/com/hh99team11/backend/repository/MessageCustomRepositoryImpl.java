package com.hh99team11.backend.repository;


import com.hh99team11.backend.dto.RecentMessageDto;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.util.NullSafeBuilderHelper;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hh99team11.backend.model.QMessage.message;

@RequiredArgsConstructor
@Repository
public class MessageCustomRepositoryImpl implements MessageCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<RecentMessageDto> findAllCommunicatorsByReceiverId(Long userId) {

        List<RecentMessageDto> receiveMessages = jpaQueryFactory
                // 프로젝션 생성자 이용
                .select(Projections.constructor(RecentMessageDto.class,
                        message.id, message.sender, message.receiver,
                        message.content,
                        // 불필요한 조회 결과값을 fix
                        Expressions.asNumber(0L).as("unReadCount")))
                .from(message)
                // 서브쿼리를 적용하여 조건추가
                .where(message.id.in(
                        JPAExpressions
                                .select(message.id.max())
                                .from(message)
                                .where(equalsWithReceiverId(userId))
                                .groupBy(message.sender, message.receiver)))
                .fetch();

        List<RecentMessageDto> sendMessages = jpaQueryFactory
                .select(Projections.constructor(RecentMessageDto.class,
                        message.id, message.sender , message.receiver,
                        message.content ,
                        Expressions.asNumber(0L).as("unReadCount")))
                .from(message)
                .where(message.id.in(
                        JPAExpressions
                                .select(message.id.max())
                                .from(message)
                                .where(equalsWithSenderId(userId))
                                .groupBy(message.sender,message.receiver)))
                .fetch();


        List<RecentMessageDto> countMessages = jpaQueryFactory
                .select(Projections.constructor(RecentMessageDto.class,
                        message.id, message.sender, message.receiver,
                        message.content, message.count()))
                .from(message)
                .where(equalsWithReceiverId(userId),
                        isUnReadMessage())
                .groupBy(message.sender, message.receiver)
                .fetch();

        return findRecentCommunicatorsByCompareSendAndReceiveMessage(receiveMessages, sendMessages, countMessages);

    }

    private List<RecentMessageDto> findRecentCommunicatorsByCompareSendAndReceiveMessage
            (List<RecentMessageDto> receiveMessages, List<RecentMessageDto> sendMessages, List<RecentMessageDto> countMessages) {
        /*
            결과값에 추가적인 필터링을 줄 수 있도록 하기 위해 결과값 전달 메서드를 추가 적용하기로 결정 ,
        */

        Map<User,RecentMessageDto> result = new HashMap<>();


        receiveMessages
                .forEach(receiveMessage -> result.put(receiveMessage.getSender(),receiveMessage));

        sendMessages
                .forEach(sendMessage -> result.put(sendMessage.getReceiver(),sendMessage));

        countMessages
                .forEach(countMessage ->{
                    RecentMessageDto messageCount = result.get(countMessage.getSender());
                    messageCount.updateUnReadCount(countMessage.getUnReadCount());
                });

        return result.values().stream()
                .sorted(Comparator.comparing(RecentMessageDto::getId, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }


    private BooleanExpression equalsWithReceiverId(Long userId){
        return message.receiver.id.eq(userId);
    }

    private BooleanExpression equalsWithSenderId(Long userId) {
        return message.sender.id.eq(userId);
    }

    // nullSafeBuilder를 사용해서 null값으로 인한 Exception 방지
    private BooleanBuilder isUnReadMessage(){
        return NullSafeBuilderHelper.nullSafeBuilder(() -> message.isRead.eq(false));
    }
}
