package com.min.prodosing.repository;

import com.min.prodosing.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findAll(Pageable pageable);

    @Modifying
    @Query("update BoardEntity b set b.view = b.view + 1 where b.boardid = :boardid")
    void updateView(@Param("boardid") Long boardid);
}
