package com.min.prodosing.controller;

import com.min.prodosing.dto.MemberDTO;
import com.min.prodosing.service.MemberService;
import com.min.prodosing.service.RegisterMail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RegisterMail registerMail;

    @Value("${upload.path}") // application.properties에서 설정된 경로
    private String uploadPath;

    @GetMapping("/join")
    public String join() {
        return "member/join";
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
        if(null == memberDTO.getBirth_y() ) {
            memberDTO.setBirth_y("");
        }
        if(null==memberDTO.getBirth_m()) {
            memberDTO.setBirth_m("");
        }
        if(null==memberDTO.getBirth_d()) {
            memberDTO.setBirth_d("");
        }
        String birth = memberDTO.getBirth_y().concat( memberDTO.getBirth_m().concat(memberDTO.getBirth_d()));


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
        }else {
            memberDTO.setFavorite(null);
        }
        String result = memberService.join(memberDTO);

        if(result.equals("success")){
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            model.addAttribute("searchUrl", "/");
            return "/message/message";
        }else {
            model.addAttribute("message", "회원가입을 실패했습니다.");
            return "/message/message_fail";
        }
    }

    //아이디 중복체크 검색 새창
    @GetMapping("/join/idCheckSearch")
    public String idCheckSearch() {
        return "member/idCheckSearch";
    }

    //아이디 중복체크
    @PostMapping("/join/id-check")
    public @ResponseBody String idCheck(@RequestParam("userid") String userid){
        String checkResult;
        if(userid=="") {
            return checkResult = "";
        }else {
            checkResult = memberService.idCheck(userid);
            return checkResult;
        }
    }

    //로그인 페이지 이동
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    //로그인
    @PostMapping("login")
    public String login(MemberDTO memberDTO, HttpSession session, Model model) {
        System.out.println("status= "+memberDTO.getStatus());
        String loginResult = memberService.login(memberDTO);

        if(loginResult != null) {
            session.setAttribute("loginId",memberDTO.getUserid());
            session.setAttribute("status", loginResult);
            return "redirect:index";
        }else {
            model.addAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "message/message_fail";
        }
    }

    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session, Model model) {
        session.invalidate();
        model.addAttribute("message", "로그아웃이 완료되었습니다");
        model.addAttribute("searchUrl", "index");
        return "message/message";
    }

    //아티스트 등록
    @GetMapping("artistJoin")
    public String artistJoin() {
        return "member/artistJoin";
    }

    @PostMapping("/artistJoin")
    public String artistJoin(MemberDTO memberDTO, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        //팀프로필사진
        if(file == null || file.isEmpty()){

        }else {
            String orgFileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            //확장자 추출
            String extension = orgFileName.substring(orgFileName.lastIndexOf("."));
            String saveFileName = uuid + extension;
            String filePath = uploadPath + saveFileName;
            file.transferTo(new File(filePath));

            memberDTO.setOrgfilename(orgFileName);
            memberDTO.setFilename(saveFileName);
            memberDTO.setFilepath(filePath);
        }

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
        if(null == memberDTO.getBirth_y() ) {
            memberDTO.setBirth_y("");
        }
        if(null==memberDTO.getBirth_m()) {
            memberDTO.setBirth_m("");
        }
        if(null==memberDTO.getBirth_d()) {
            memberDTO.setBirth_d("");
        }
        String birth = memberDTO.getBirth_y().concat( memberDTO.getBirth_m().concat(memberDTO.getBirth_d()));

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

        //status "A"(artist)
        memberDTO.setStatus("A");

        String result = memberService.join(memberDTO);

        if(result.equals("success")){
            model.addAttribute("message", "회원가입이 완료되었습니다.");
            model.addAttribute("searchUrl", "/");
            return "/message/message";
        }else {
            model.addAttribute("message", "회원가입을 실패했습니다.");
            return "/message/message_fail";
        }
    }

    // 메일인증
    @ResponseBody
    @PostMapping("/emailAuth")
    public String emailAuth(MemberDTO memberDTO) throws MessagingException, UnsupportedEncodingException {
        String userEmail;
            if (memberDTO.getEmail_site().equals("직접입력")) {
                userEmail = memberDTO.getEmail();
            } else {
                userEmail = memberDTO.getEmail() + "@" + memberDTO.getEmail_site();
            }

        String code = registerMail.sendMessage(userEmail);
        return code;
    }




}
