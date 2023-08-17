package com.min.prodosing.controller;

import com.min.prodosing.dto.ConcertDTO;
import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.repository.MemberRepository;
import com.min.prodosing.service.ConcertService;
import com.min.prodosing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;
    private final MemberService memberService;


    //공연등록 Form
    @GetMapping("register")
    public String registerForm(Model model, String userid) {
        String teamname = concertService.selectTeam(userid);
        model.addAttribute("teamname", teamname);
        return "artist/register";
    }

    //공연등록
    @PostMapping("register")
    public String register(ConcertDTO concertDTO, Model model) {
        boolean result = concertService.concertReg(concertDTO);

        if(result == true) {
            model.addAttribute("message", "공연등록이 완료되었습니다.");
            model.addAttribute("searchUrl", "index");
            return "message/message";
        }else {
            model.addAttribute("message", "공연등록을 실패했습니다.");
            return "message/message_fail";
        }

    }



    //공연목록
    @GetMapping("schedule")
    public String schedule() {
        return "artist/schedule";
    }
}
