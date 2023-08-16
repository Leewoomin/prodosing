package com.min.prodosing.dto;

import com.min.prodosing.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MemberDTO {
    private Long member_id;
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
    private String filename;
    private String filepath;
    private String status;


    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        return MemberDTO.builder()
                .member_id(memberEntity.getMember_id())
                .userid(memberEntity.getUserid())
                .password(memberEntity.getPassword())
                .name(memberEntity.getName())
                .team_name(memberEntity.getTeam_name())
                .gender(memberEntity.getGender())
                .phone(memberEntity.getPhone())
                .birth(memberEntity.getBirth())
                .email(memberEntity.getEmail())
                .career(memberEntity.getCareer())
                .favorite(memberEntity.getFavorite())
                .filename(memberEntity.getFilename())
                .filepath(memberEntity.getFilepath())
                .status(memberEntity.getStatus())
                .build();
    }
}
