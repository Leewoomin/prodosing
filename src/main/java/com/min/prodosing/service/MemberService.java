package com.min.prodosing.service;

import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //회원가입
    public String join(MemberDTO memberDTO) {
        try {
            memberRepository.save(MemberEntity.toMemberEntity(memberDTO));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //아이디 중복확인
    public String idCheck(String userid) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserid(userid);
        if(byUserId.isPresent()) {
            return "fail";
        }else {
            return "ok";
        }

    }

    //로그인
    public String login(MemberDTO memberDTO) {
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(memberDTO.getUserid());

        if(byUserid.isPresent()) {
            MemberEntity memberEntity = byUserid.get();
            if(memberDTO.getPassword().equals(memberEntity.getPassword())) {
                return memberEntity.getStatus();
            }else {
                return null;
            }
        }else {
            return null;
        }
    }
}
