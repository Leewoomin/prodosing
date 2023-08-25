package com.min.prodosing.controller;

import com.min.prodosing.dto.ConcertDTO;
import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.entity.MemberEntity;
import com.min.prodosing.service.ArtistService;
import com.min.prodosing.service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;


    //아티스트 목록
    @GetMapping("artistList")
    public String artistList(Model model,
                             @PageableDefault(page=0, size=5, sort="memberid", direction = Sort.Direction.DESC) Pageable pageable,
                             String searchKeyword) {

        Page<MemberEntity> artistList = null;
        List<ConcertDTO> concertDate = artistService.concertDate();

        //검색어 있는지 여부
        if(searchKeyword == null) {
            artistList = artistService.artistList(pageable);
        }else {
            artistList = artistService.artistSearchList(searchKeyword, pageable);
        }

        int totalPageCount = artistList.getTotalPages();
        int startPage = 0;
        int nowPage = artistList.getPageable().getPageNumber();
        int pagingNumber = nowPage / 5;
            if(pagingNumber==0){
                startPage = 1;
            }else {
                startPage = (pagingNumber*5) + 1;
            }
        int endPage = startPage + 4;
            if(endPage > artistList.getTotalPages()) {
                endPage = artistList.getTotalPages();
            }


        model.addAttribute("artistList", artistList);
        model.addAttribute("dateList", concertDate);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("pagingNumber", pagingNumber);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("totalPageCount", totalPageCount);

        return "artist/artistList";
    }



    //아티스트 검색
    @PostMapping("artistSearch")
    public String artistSearch() {
        return "redirect:index";
    }








}
