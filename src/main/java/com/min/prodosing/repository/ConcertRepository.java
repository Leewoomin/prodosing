package com.min.prodosing.repository;

import com.min.prodosing.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ConcertRepository extends JpaRepository<ConcertEntity, Long>{

    //공연목록
    List<ConcertEntity> findAllByDateContaining(String searchDate);

    //팀별 가장 가까운 공연 예정일자
    @Query("select c from ConcertEntity c group by c.teamname order by c.date")
    List<ConcertEntity> findGroupByTeamNameOrderByDate();

}
