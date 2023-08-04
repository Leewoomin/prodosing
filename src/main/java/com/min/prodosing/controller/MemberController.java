package com.min.prodosing.controller;

import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/join")
    public String join(MemberDTO memberDTO, Model model) {

        //성별
        String gender = memberDTO.getGender();
        if(gender.equals("남자")) {
            memberDTO.setGender("M");
        }else if(gender.equals("여자")){
            memberDTO.setGender("W");
        }else {
            memberDTO.setGender("S");
        }

        //생년월일 년+월+일
        if(null == memberDTO.getBirth_y() && null ==  memberDTO.getBirth_m() && null == memberDTO.getBirth_d()) {
            memberDTO.setBirth(null);
        }else {
            String birth = memberDTO.getBirth_y() +
                            memberDTO.getBirth_m() + memberDTO.getBirth_d();
                            memberDTO.setBirth(birth);
        }

        //email id+사이트
        String email;
        if(memberDTO.getEmail() == null) {
            if (memberDTO.getEmail_site().equals("직접입력")) {
                email = memberDTO.getEmail();
            } else {
                email = memberDTO.getEmail() + "@" + memberDTO.getEmail_site();
            }
            memberDTO.setEmail(email);
        }else {
            memberDTO.setEmail(null);
        }

        //favorite 문자열끝 ',' 제거
        String favorite = memberDTO.getFavorite();
        if(!"".equals(favorite) && favorite.substring(favorite.length()-1,favorite.length()).equals(",")) {
            favorite = favorite.substring(0,memberDTO.getFavorite().length()-1);
            memberDTO.setFavorite(favorite);
        }
        String result = memberService.join(memberDTO);

        if(result.equals("success")){
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            model.addAttribute("searchUrl", "/");
            return "/message/message";
        }else {
            model.addAttribute("message", "회원가입을 실패했습니다.");
            model.addAttribute("searchUrl", "/join");
            return "/message/message_fail";
        }

    }












}
