package com.min.prodosing.entity;

import com.min.prodosing.dto.MemberDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "member")
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long memberid;

    @Column(unique = true)
    private String userid;
    private String password;
    private String name;
    @Column(name = "team_name")
    private String teamname;
    private String gender;
    private String phone;
    private String birth;
    private String email;
    @ColumnDefault("F")
    private String career;
    private String favorite;
    private String orgfilename;
    private String filename;
    private String filepath;
    private String status;
    private String kakaoid;


    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        return MemberEntity.builder()
                .memberid(memberDTO.getMemberid())
                .userid(memberDTO.getUserid())
                .password(memberDTO.getPassword())
                .name(memberDTO.getName())
                .teamname(memberDTO.getTeam_name())
                .gender(memberDTO.getGender())
                .phone(memberDTO.getPhone())
                .birth(memberDTO.getBirth())
                .email(memberDTO.getEmail())
                .career(memberDTO.getCareer())
                .favorite(memberDTO.getFavorite())
                .orgfilename(memberDTO.getOrgfilename())
                .filename(memberDTO.getFilename())
                .filepath(memberDTO.getFilepath())
                .status(memberDTO.getStatus())
                .kakaoid(memberDTO.getKakaoid())
                .build();
    }

}
