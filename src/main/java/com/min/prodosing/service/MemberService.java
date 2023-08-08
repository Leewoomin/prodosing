package com.min.prodosing.service;

import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public String join(MemberDTO memberDTO) {
        try {
            memberRepository.save(MemberEntity.toMemberEntity(memberDTO));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    public String idCheck(String userid) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserid(userid);
        if(byUserId.isPresent()) {
            return "fail";
        }else {
            return "ok";
        }

    }
}
