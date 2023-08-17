package com.min.prodosing.service;

import com.min.prodosing.dto.ConcertDTO;
import com.min.prodosing.entity.ConcertEntity;
import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.repository.ConcertRepository;
import com.min.prodosing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConcertService {
    private final MemberRepository memberRepository;
    private final ConcertRepository concertRepository;

    //팀명 가져오기
    public String selectTeam(String userid) {
        Optional<MemberEntity> byUserid = memberRepository.findByUserid(userid);
        MemberEntity memberEntity = byUserid.get();

        return memberEntity.getTeam_name();

    }
    
    //공연등록
    public boolean concertReg(ConcertDTO concertDTO) {
        try {
            concertRepository.save(ConcertEntity.toConcertEntity(concertDTO));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
