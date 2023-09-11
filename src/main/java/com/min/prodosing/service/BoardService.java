package com.min.prodosing.service;

import com.min.prodosing.dto.BoardDTO;
import com.min.prodosing.dto.BoardReDTO;
import com.min.prodosing.entity.BoardEntity;
import com.min.prodosing.entity.BoardReEntity;
import com.min.prodosing.repository.BoardReRepository;
import com.min.prodosing.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardReRepository boardReRepository;

    //글쓰기
    public boolean boardWrite(BoardDTO boardDTO) {
        try {
            boardRepository.save(BoardEntity.toBoardEntity(boardDTO));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    //게시판 목록
    public Page<BoardEntity> boardList(Pageable pageable) {
        Page<BoardEntity> boardEntityList = boardRepository.findAll(pageable);

        return boardEntityList;
    }

    //게시글 본문
    public BoardDTO boardContent(Long boardid) {
        Optional<BoardEntity> boardEntity = boardRepository.findById(boardid);
        return BoardDTO.toBoardDTO(boardEntity.get());
    }

    //조회수 증가
    @Transactional
    public void updateView(Long boardid) {
        boardRepository.updateView(boardid);
    }

    //댓글작성
    public void boardReWrite(BoardReDTO boardReDTO) {
        boardReRepository.save(BoardReEntity.toBoardReEntity(boardReDTO));
    }

    //게시물 댓글리스트 가져오기
    public List<BoardReDTO> boardReplyList(Long boardid) {
        List<BoardReEntity> boardReEntityList = boardReRepository.findByBoardidOrderByReidDesc(boardid);
        List<BoardReDTO> boardReDTOList = new ArrayList<>();

        for (BoardReEntity boardReEntity : boardReEntityList) {
            boardReDTOList.add(BoardReDTO.toBoardReDTO(boardReEntity));
        }
        return boardReDTOList;
    }

    @Transactional
    public boolean boardUpdate(BoardDTO boardDTO) {
        Long boardid = boardDTO.getBoard_id();
        String title = boardDTO.getTitle();
        String content = boardDTO.getContent();

        try {
            boardRepository.boardUpdate(boardid, title, content);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean boardDelete(Long boardid) {
        try{
            boardRepository.deleteById(boardid);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
