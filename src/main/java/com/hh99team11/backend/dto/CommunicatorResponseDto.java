package com.hh99team11.backend.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommunicatorResponseDto {

    private UserProfileSet sender;
    private UserProfileSet receiver;

    private String content;
    private Long unReadCount;

    public static CommunicatorResponseDto create(RecentMessageDto recentMessageDto){
        return new CommunicatorResponseDto(UserProfileSet.create(recentMessageDto.getSender()),
                UserProfileSet.create(recentMessageDto.getReceiver()),recentMessageDto.getContent(),
                recentMessageDto.getUnReadCount());
    }

}
