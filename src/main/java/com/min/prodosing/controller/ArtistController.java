package com.min.prodosing.controller;

import com.min.prodosing.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    //아티스트 목록
    @GetMapping("artistList")
    public String artistList() {
        return "artist/artistList";
    }



    //아티스트 검색
    @PostMapping("artistSearch")
    public String artistSearch() {
        return "redirect:index";
    }








}
