package com.min.prodosing.service;

import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final MemberRepository memberRepository;

    public String selectTeam(String userid) {
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        MemberEntity memberEntity = byUserid.get();

        return memberEntity.getTeam_name();

    }
}
