package com.min.prodosing.entity;

import com.min.prodosing.dto.ConcertDTO;
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
public class ConcertEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long concert_id;
    private String title;
    @Column(name = "team_name")
    private String teamname;
    private String date;
    private String place;
    private String start_time;
    private String stage_size;
    @ColumnDefault("0")
    private String price;
    private String note;
    private String orgfilename;
    private String filename;
    private String filepath;
    private String createDate;


    public static ConcertEntity toConcertEntity(ConcertDTO concertDTO) {

        return ConcertEntity.builder()
                .concert_id(concertDTO.getConcert_id())
                .title(concertDTO.getTitle())
                .teamname(concertDTO.getTeam_name())
                .date(concertDTO.getDate())
                .place(concertDTO.getPlace())
                .start_time(concertDTO.getStart_time())
                .stage_size(concertDTO.getStage_size())
                .price(concertDTO.getPrice())
                .note(concertDTO.getNote())
                .orgfilename(concertDTO.getOrgfilename())
                .filename(concertDTO.getFilename())
                .filepath(concertDTO.getFilepath())
                .build();
    }


}
