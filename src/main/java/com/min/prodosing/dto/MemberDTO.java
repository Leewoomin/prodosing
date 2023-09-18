package com.min.prodosing.dto;

import com.min.prodosing.entity.ConcertEntity;
import com.min.prodosing.entity.MemberEntity;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberDTO {
    private Long memberid;
    private String userid;
    private String password;
    private String name;
    private String team_name;
    private String gender;
    private String phone;
    private String birth;
    private String birth_y;
    private String birth_m;
    private String birth_d;
    private String email;
    private String email_site;
    private String career;
    private String favorite;
    private String orgfilename;
    private String filename;
    private String filepath;
    private String status;
    private String kakaoid;


    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        return MemberDTO.builder()
                .memberid(memberEntity.getMemberid())
                .userid(memberEntity.getUserid())
                .password(memberEntity.getPassword())
                .name(memberEntity.getName())
                .team_name(memberEntity.getTeamname())
                .gender(memberEntity.getGender())
                .phone(memberEntity.getPhone())
                .birth(memberEntity.getBirth())
                .email(memberEntity.getEmail())
                .career(memberEntity.getCareer())
                .favorite(memberEntity.getFavorite())
                .orgfilename(memberEntity.getOrgfilename())
                .filename(memberEntity.getFilename())
                .filepath(memberEntity.getFilepath())
                .status(memberEntity.getStatus())
                .kakaoid(memberEntity.getKakaoid())
                .build();
    }

}
