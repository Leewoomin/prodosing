package com.min.prodosing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.min.prodosing.dto.BoardDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "board")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    @Column(name = "board_id")
    private Long boardid;
    private String user_id;
    private String title;
    private String content;
    private String orgfilename;
    private String filename;
    private String filepath;
    @ColumnDefault("0")
    private int view;
    private LocalDate writedate;



    public static BoardEntity toBoardEntity(BoardDTO boardDTO){
        return BoardEntity.builder()
                .boardid(boardDTO.getBoard_id())
                .user_id(boardDTO.getUser_id())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .orgfilename(boardDTO.getOrgfilename())
                .filename(boardDTO.getFilename())
                .filepath(boardDTO.getFilepath())
                .view(boardDTO.getView())
                .writedate(boardDTO.getWritedate())
                .build();
    }
}
