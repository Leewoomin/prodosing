package com.min.prodosing.repository;

import com.min.prodosing.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<ConcertEntity, Long>{
}
