package com.min.prodosing.controller;

import com.google.gson.JsonObject;
import com.min.prodosing.dto.BoardDTO;
import com.min.prodosing.dto.BoardReDTO;
import com.min.prodosing.entity.BoardEntity;
import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Value("${upload.summer}")
    private String uploadSummer;

    //게시글 목록
    @GetMapping("/boardList")
    public String boardList(Model model, HttpSession session,
                            @PageableDefault(page=0, size=10, sort="boardid", direction = Sort.Direction.DESC) Pageable pageable) {

        //페이징
        Page<BoardEntity> boardList = boardService.boardList(pageable);

        int totalPageCount = boardList.getTotalPages();
        int startPage = 0;
        int nowPage = boardList.getPageable().getPageNumber();
        int pagingNumber = nowPage / 5;
        int nextBtn = Math.min((pagingNumber+1)*5, totalPageCount-1);
        int prevBtn = Math.max((pagingNumber*5)-1 , 0);
        if(pagingNumber==0){
            startPage = 1;
        }else {
            startPage = (pagingNumber*5) + 1;
        }
        int endPage = startPage + 4;
        if(endPage > boardList.getTotalPages()) {
            endPage = boardList.getTotalPages();
        }
        session.getAttribute("loginId");
        model.addAttribute("boardList", boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pagingNumber", pagingNumber);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("nextBtn", nextBtn);
        model.addAttribute("prevBtn", prevBtn);
        return "board/boardList";
    }

    //글쓰기 form
    @GetMapping("/boardWrite")
    public String boardWriteForm() {

        return "board/boardWrite";
    }

    //글쓰기
    @PostMapping("/boardWrite")
    public String boardWrite(BoardDTO boardDTO, Model model, HttpSession session) {
        boardDTO.setUser_id((String) session.getAttribute("loginId"));
        boardDTO.setWritedate(LocalDate.now());
        boolean saveResult = boardService.boardWrite(boardDTO);
        Map<String, String> result = new HashMap<>();

        if(saveResult) {
            model.addAttribute("message", "게시글 작성이 완료되었습니다.");
            model.addAttribute("searchUrl", "boardList");
            return "message/message";
        }else {
            model.addAttribute("message", "게시글 등록을 실패했습니다.");
            return "message/message_fail";
        }
    }

    //summer note 파일 업로드
    @ResponseBody
    @PostMapping("/summernoteUpload")
    public Map<String,String> upload(BoardDTO boardDTO, @RequestParam("file") MultipartFile file,
                         HttpServletRequest request) throws ServletException, IOException {
        String orgFileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        //확장자 추출
        String extension = orgFileName.substring(orgFileName.lastIndexOf("."));
        String saveFileName = uuid + extension;
        String filePath = uploadSummer + saveFileName;
        Map<String,String> fileMap = new HashMap<>();

        fileMap.put("savename", saveFileName);
        fileMap.put("orgname", orgFileName);
        fileMap.put("filepath", "/summernoteImg/" + saveFileName);


        Part part = request.getPart("file");
        file.transferTo(new File(filePath));

        System.out.println(filePath);


        return fileMap;
    }


    //게시물 본문
    @GetMapping("/content/{boardid}")
    public String boardContent(@PathVariable("boardid") Long boardid, Model model,
                               HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        //조회수 증가 중복 막기
        if(cookies != null) {
            for(Cookie cook: cookies) {
                System.out.println("cookieValue00000= "+cook.getValue());
                System.out.println("cookieName00000= "+cook.getName());
                cookie = cook;
                if(cookie.getName().equals("visited")) {


                    if(!cookie.getValue().contains("[" + String.valueOf(boardid) + "]")) {
                        System.out.println("cookieValue1111= "+cookie.getValue());
                        System.out.println("cookieName1111= "+cookie.getName());
                        cookie.setValue(cookie.getValue() + "[" + String.valueOf(boardid) + "]");
                        response.addCookie(cookie);
                        System.out.println("cookieValue2222= "+cookie.getValue());
                        System.out.println("cookieName2222= "+cookie.getName());
                        boardService.updateView(boardid);
                    }
                }
            }
        }else {
            System.out.println("bbbbbbbbbbbbbbbbb");
            Cookie newCookie = new Cookie("visited", "[" + String.valueOf(boardid) + "]");
            response.addCookie(newCookie);
            boardService.updateView(boardid);
        }

        //자정이 지나면 쿠키 초기화
        long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
        long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        if(cookie != null) {
            cookie.setMaxAge((int) (todayEndSecond - currentSecond));
            response.addCookie(cookie);
        }

        //게시물 가져오기
        BoardDTO boardDTO = boardService.boardContent(boardid);

        model.addAttribute("content", boardDTO);

        return "board/boardContent";
    }

    //댓글목록
    @ResponseBody
    @PostMapping("/boardReList")
    public List<BoardReDTO> boardReList(BoardReDTO boardReDTO) {
        if(boardReDTO.getContent() != "") {
            boardService.boardReWrite(boardReDTO);
            List<BoardReDTO> reDTOList = boardService.boardReplyList(boardReDTO.getBoard_id());
        }

        List<BoardReDTO> reDTOList = boardService.boardReplyList(boardReDTO.getBoard_id());
        return reDTOList;
    }




}
