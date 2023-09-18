package com.min.prodosing.service;

import com.min.prodosing.dto.ConcertDTO;
import com.min.prodosing.entity.ConcertEntity;
import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.repository.ConcertRepository;
import com.min.prodosing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final MemberRepository memberRepository;
    private final ConcertRepository concertRepository;

    //아티스트 목록
    public Page<MemberEntity> artistList(Pageable pageable) {
        String status = "A";
        Page<MemberEntity> memberEntityList = memberRepository.findByStatus(status, pageable);

        return memberEntityList;
    }

    //아티스트 검색목록
    public Page<MemberEntity> artistSearchList(String searchKeyword, Pageable pageable) {
        String status = "A";
        return memberRepository.findByStatusAndTeamnameContaining(status, searchKeyword, pageable);
    }

    //아티스트 목록(공연예정일)
    public List<ConcertDTO> concertDate() {
        List<ConcertEntity> entityList = concertRepository.findGroupByTeamNameOrderByDate();
        List<ConcertDTO> dateList = new ArrayList<>();

        for(ConcertEntity concertEntity: entityList) {
            dateList.add(ConcertDTO.toConcertDTO(concertEntity));
        }

        return dateList;
    }







}
