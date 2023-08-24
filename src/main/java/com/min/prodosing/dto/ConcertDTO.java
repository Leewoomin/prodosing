package com.min.prodosing.dto;

import com.min.prodosing.entity.ConcertEntity;
import com.min.prodosing.entity.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ConcertDTO {
    private Long concert_id;
    private String title;
    private String team_name;
    private Date date;
    private String place;
    private String start_time;
    private String stage_size;
    private String price;
    private String note;
    private String filename;
    private String filepath;

    public ConcertDTO(String team_name, Date date){
        this.team_name = team_name;
        this.date = date;
    }


    public static ConcertDTO toConcertDTO(ConcertEntity concertEntity) {
        return ConcertDTO.builder()
                .concert_id(concertEntity.getConcert_id())
                .title(concertEntity.getTitle())
                .team_name(concertEntity.getTeam_name())
                .date(concertEntity.getDate())
                .place(concertEntity.getPlace())
                .start_time(concertEntity.getStart_time())
                .stage_size(concertEntity.getStage_size())
                .price(concertEntity.getPrice())
                .note(concertEntity.getNote())
                .filename(concertEntity.getFilename())
                .filepath(concertEntity.getFilepath())
                .build();
    }


}
