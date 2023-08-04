package com.min.prodosing.entity;

import com.min.prodosing.dto.MemberDTO;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long member_id;

    @Column(unique = true)
    private String userid;
    private String password;
    private String password_check;
    private String name;
    private String team_name;
    private String gender;
    private String phone;
    private String birth;
    private String email;
    private String career;
    private String favorite;
    private String satus;




    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        return MemberEntity.builder()
                .member_id(memberDTO.getMember_id())
                .userid(memberDTO.getUserid())
                .password(memberDTO.getPassword())
                .password_check(memberDTO.getPassword_check())
                .name(memberDTO.getName())
                .team_name(memberDTO.getTeam_name())
                .gender(memberDTO.getGender())
                .phone(memberDTO.getPhone())
                .birth(memberDTO.getBirth())
                .email(memberDTO.getEmail())
                .career(memberDTO.getCareer())
                .favorite(memberDTO.getFavorite())
                .satus(memberDTO.getSatus())
                .build();
    }

//    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
//        MemberEntity memberEntity = new MemberEntity();
//        memberEntity.setMember_id(memberDTO.getMember_id());
//        memberEntity.setPassword(memberDTO.getPassword());
//        return memberEntity;
//    }

}
