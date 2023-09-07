package com.min.prodosing.entity;

import com.min.prodosing.dto.BoardDTO;
import com.min.prodosing.dto.BoardReDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "board_re")
public class BoardReEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "re_id")
    private Long reid;

    @Column(name = "board_id")
    private Long boardid;

    @Column(name="user_id")
    private String re_user_id;

    @Column(name="content")
    private String re_content;

    @ColumnDefault("N")
    private String delete_yn;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createwrite;

    public static BoardReEntity toBoardReEntity(BoardReDTO boardReDTO) {
        return BoardReEntity.builder()
                .reid(boardReDTO.getRe_id())
                .boardid(boardReDTO.getBoard_id())
                .re_user_id(boardReDTO.getUser_id())
                .re_content(boardReDTO.getContent())
                .delete_yn(boardReDTO.getDelete_yn())
                .createwrite(boardReDTO.getCreatewrite())
                .build();
    }
}
