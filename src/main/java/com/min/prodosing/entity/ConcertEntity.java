package com.min.prodosing.entity;

import com.min.prodosing.dto.ConcertDTO;
import com.min.prodosing.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "concert")
public class ConcertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long SCH_ID;
    private String TEAM_NAME;
    private String DATE;
    private String PLACE;
    private String START_TIME;
    private String END_TIME;
    private String STAGE_SIZE;
    @ColumnDefault("0")
    private int PRICE;
    private String NOTE;



    public static ConcertEntity toConcertEntity(ConcertDTO concertDTO) {
        return ConcertEntity.builder()
                .SCH_ID(concertDTO.getSCH_ID())
                .TEAM_NAME(concertDTO.getTEAM_NAME())
                .DATE(concertDTO.getDATE())
                .PLACE(concertDTO.getPLACE())
                .START_TIME(concertDTO.getSTART_TIME())
                .END_TIME(concertDTO.getEND_TIME())
                .STAGE_SIZE(concertDTO.getSTAGE_SIZE())
                .PRICE(concertDTO.getPRICE())
                .NOTE(concertDTO.getNOTE())
                .build();
    }



}
