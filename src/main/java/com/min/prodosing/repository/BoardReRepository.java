package com.min.prodosing.repository;

import com.min.prodosing.entity.BoardEntity;
import com.min.prodosing.entity.BoardReEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardReRepository extends JpaRepository<BoardReEntity, Long> {
    List<BoardReEntity> findByBoardidOrderByReidDesc(Long boardid);


}
