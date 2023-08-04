package com.min.prodosing.repository;

import com.min.prodosing.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    //아이디로 회원정보 조회
    Optional<MemberEntity> findByUserid(String userid);
}
