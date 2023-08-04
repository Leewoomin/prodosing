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
    private String user_id;
    private String password;
    private String password_check;
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
    private String satus;


    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        return MemberDTO.builder()
                .member_id(memberEntity.getMember_id())
                .user_id(memberEntity.getUser_id())
                .password(memberEntity.getPassword())
                .password_check(memberEntity.getPassword_check())
                .name(memberEntity.getName())
                .team_name(memberEntity.getTeam_name())
                .gender(memberEntity.getGender())
                .phone(memberEntity.getPhone())
                .birth(memberEntity.getBirth())
                .email(memberEntity.getEmail())
                .career(memberEntity.getCareer())
                .favorite(memberEntity.getFavorite())
                .satus(memberEntity.getSatus())
                .build();
    }
}
