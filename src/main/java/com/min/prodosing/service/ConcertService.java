package com.min.prodosing.service;

import com.min.prodosing.dto.ConcertDTO;
import com.min.prodosing.entity.ConcertEntity;
import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.repository.ConcertRepository;
import com.min.prodosing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

        return memberEntity.getTeamname();

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

    //공연목록
    public List<ConcertDTO> concertList() {
        List<ConcertEntity> concertEntityList = concertRepository.findAll();
        List<ConcertDTO> concertDTOList = new ArrayList<>();

        for(ConcertEntity concertEntity: concertEntityList) {
            concertDTOList.add(ConcertDTO.toConcertDTO(concertEntity));
        }
        return concertDTOList;
    }

    //공연상세정보
    public ConcertDTO concertInfo(Long concertId) {
        Optional<ConcertEntity> concertEntity = concertRepository.findById(concertId);
        return ConcertDTO.toConcertDTO(concertEntity.get());
    }
}
