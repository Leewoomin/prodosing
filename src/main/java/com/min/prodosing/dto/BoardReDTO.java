package com.min.prodosing.dto;

import com.min.prodosing.entity.BoardEntity;
import com.min.prodosing.entity.BoardReEntity;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BoardReDTO {
    private Long re_id;
    private Long board_id;
    private String user_id;
    private String content;
    private String delete_yn;
    private LocalDate createwrite;


    public static BoardReDTO toBoardReDTO(BoardReEntity boardReEntity) {
        return BoardReDTO.builder()
                .re_id(boardReEntity.getReid())
                .board_id(boardReEntity.getBoardid())
                .user_id(boardReEntity.getRe_user_id())
                .content(boardReEntity.getRe_content())
                .delete_yn(boardReEntity.getDelete_yn())
                .createwrite(boardReEntity.getCreatewrite())
                .build();
    }
}
