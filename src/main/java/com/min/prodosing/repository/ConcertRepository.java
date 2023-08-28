package com.min.prodosing.repository;

import com.min.prodosing.dto.ConcertDTO;
import com.min.prodosing.entity.ConcertEntity;
import com.min.prodosing.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface ConcertRepository extends JpaRepository<ConcertEntity, Long>{
//    @Query("select c.team_name as team_name, c.date as date from ConcertEntity c where c.team_name=:team_name order by c.date asc")
//    @Query("select new com.min.prodosing.dto.ConcertDTO(c.team_name, c.date) from ConcertEntity c where c.team_name=:team_name order by c.date asc")
//    List<ConcertDTO> findFirstByTeam_nameOrderByDate(@Param("team_name") String team_name);

    //공연목록
    List<ConcertEntity> findAllByDateContaining(String searchDate);

    @Query("select c from ConcertEntity c group by c.teamname order by c.date")
    List<ConcertEntity> findGroupByTeamNameOrderByDate();

}
