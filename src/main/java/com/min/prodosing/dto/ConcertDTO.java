package com.min.prodosing.dto;

import com.min.prodosing.entity.ConcertEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ConcertDTO {
    private Long sch_id;
    private String team_name;
    private Date date;
    private String place;
    private String start_time;
    private String stage_size;
    private String price;
    private String note;


    private static ConcertDTO toConcertDTO(ConcertEntity concertEntity) {
        return ConcertDTO.builder()
                .sch_id(concertEntity.getSch_id())
                .team_name(concertEntity.getTeam_name())
                .date(concertEntity.getDate())
                .place(concertEntity.getPlace())
                .start_time(concertEntity.getStart_time())
                .stage_size(concertEntity.getStage_size())
                .price(concertEntity.getPrice())
                .note(concertEntity.getNote())
                .build();
    }
}
