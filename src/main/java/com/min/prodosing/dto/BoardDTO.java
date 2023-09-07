package com.min.prodosing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.min.prodosing.entity.BoardEntity;
import com.min.prodosing.entity.ConcertEntity;
import lombok.*;
import org.springframework.ui.ModelMap;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardDTO {
    private Long board_id;
    private String member_id;
    private String user_id;
    private String title;
    private String content;
    private String orgfilename;
    private String filename;
    private String filepath;
    private int view;
    private LocalDate writedate;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        return BoardDTO.builder()
                .board_id(boardEntity.getBoardid())
                .user_id(boardEntity.getUser_id())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .orgfilename(boardEntity.getOrgfilename())
                .filename(boardEntity.getFilename())
                .filepath(boardEntity.getFilepath())
                .view(boardEntity.getView())
                .writedate(boardEntity.getWritedate())
                .build();
    }
}
