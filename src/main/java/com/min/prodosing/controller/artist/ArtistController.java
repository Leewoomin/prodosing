package com.min.prodosing.controller.artist;

import com.min.prodosing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ArtistController {

    private final MemberService memberService;

    @GetMapping("artistList")
    public String artistList() {
        return "artist/artistList";
    }




    @PostMapping("artistSearch")
    public String artistSearch() {
        return "redirect:index";
    }








}
