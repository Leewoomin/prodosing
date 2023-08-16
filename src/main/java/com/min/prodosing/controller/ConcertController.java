package com.min.prodosing.controller;

import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.repository.MemberRepository;
import com.min.prodosing.service.ConcertService;
import com.min.prodosing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;
    private final MemberService memberService;


    //공연등록
    @GetMapping("register")
    public String register(HttpSession session, MemberDTO memberDTO) {
        String teamname = concertService.selectTeam(memberDTO.getUserid());
        session.setAttribute("teamname", teamname);
        return "artist/register";
    }

    //공연목록
    @GetMapping("schedule")
    public String schedule() {
        return "artist/schedule";
    }
}
