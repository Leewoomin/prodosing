package com.min.prodosing.dto;

import com.min.prodosing.entity.ConcertEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ConcertDTO {
    private Long SCH_ID;
    private String TEAM_NAME;
    private String DATE;
    private String PLACE;
    private String START_TIME;
    private String END_TIME;
    private String STAGE_SIZE;
    private int PRICE;
    private String NOTE;


    private static ConcertDTO concertDTO(ConcertEntity concertEntity) {
        return ConcertDTO.builder()
                .SCH_ID(concertEntity.getSCH_ID())
                .TEAM_NAME(concertEntity.getTEAM_NAME())
                .DATE(concertEntity.getDATE())
                .PLACE(concertEntity.getPLACE())
                .START_TIME(concertEntity.getSTART_TIME())
                .END_TIME(concertEntity.getEND_TIME())
                .STAGE_SIZE(concertEntity.getSTAGE_SIZE())
                .PRICE(concertEntity.getPRICE())
                .NOTE(concertEntity.getNOTE())
                .build();
    }
}
