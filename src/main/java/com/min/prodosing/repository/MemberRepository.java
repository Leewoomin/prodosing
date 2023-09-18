package com.min.prodosing.repository;

import com.min.prodosing.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //아이디로 회원정보 조회
    Optional<MemberEntity> findByUserid(String userid);

    //아티스트 목록
    Page<MemberEntity> findByStatus(String status, Pageable pageable);

    //아티스트 검색목록
    Page<MemberEntity> findByStatusAndTeamnameContaining(String status, String searchKeyword, Pageable pageable);

    //카카오아이디 로그인이력 유무 조회
    Optional<MemberEntity> findByKakaoid(String kakaoid);
}
