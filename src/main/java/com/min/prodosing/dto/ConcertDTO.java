package com.min.prodosing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.min.prodosing.entity.BaseEntity;
import com.min.prodosing.entity.ConcertEntity;
import com.min.prodosing.entity.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

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
    private String date;
    private String place;
    private String start_time;
    private String stage_size;
    private String price;
    private String note;
    private String orgfilename;
    private String filename;
    private String filepath;



    public static ConcertDTO toConcertDTO(ConcertEntity concertEntity) {
        return ConcertDTO.builder()
                .concert_id(concertEntity.getConcert_id())
                .title(concertEntity.getTitle())
                .team_name(concertEntity.getTeamname())
                .date(concertEntity.getDate())
                .place(concertEntity.getPlace())
                .start_time(concertEntity.getStart_time())
                .stage_size(concertEntity.getStage_size())
                .price(concertEntity.getPrice())
                .note(concertEntity.getNote())
                .orgfilename(concertEntity.getOrgfilename())
                .filename(concertEntity.getFilename())
                .filepath(concertEntity.getFilepath())
                .build();
    }


}
