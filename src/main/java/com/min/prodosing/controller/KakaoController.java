package com.min.prodosing.controller;

import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.service.KakaoService;
import com.min.prodosing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;
    private final MemberService memberService;

    @GetMapping("/kakao/callback")
    public String kakaoCallback(@RequestParam String code, HttpSession session) {
        System.out.println(code);
        String access_Token = kakaoService.getKakaoAccessToken(code);
        Map<String, String> kakaoUser = kakaoService.kakaoUserInfo(access_Token);

        String kakaoId = kakaoUser.get("kakaoId");
        String kakaoEmail = kakaoUser.get("kakaoEmail");
        MemberDTO result = kakaoService.kakaoLogin(kakaoId, kakaoEmail);

        if(null == result) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserid("kakao_" + kakaoId);
            memberDTO.setKakaoid(kakaoId);
                if("" != kakaoEmail) {
                    memberDTO.setEmail(kakaoEmail);
                }
            memberService.join(memberDTO);
            session.setAttribute("kakaoId",memberDTO.getUserid());
            session.setAttribute("status","F");
        }

        session.setAttribute("kakaoId",result.getUserid());
        session.setAttribute("status","F");
        session.setAttribute("access_token", access_Token);


        return "redirect:/index";
    }

    @RequestMapping(value="/kakao/logout")
    public String logout(HttpSession session) {
        System.out.println("token= " + session.getAttribute("access_token"));
        kakaoService.logout((String)session.getAttribute("access_token"));
        session.invalidate();


        return "redirect:/index";
    }



}
