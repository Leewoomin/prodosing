package com.min.prodosing.controller;

import com.min.prodosing.dto.ConcertDTO;

import com.min.prodosing.service.ConcertService;
import com.min.prodosing.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ConcertController {

    private final ConcertService concertService;
    private final MemberService memberService;

    @Value("${upload.concert}")
    private String uploadConcert;


    //공연등록 Form
    @GetMapping("register")
    public String registerForm(Model model, String userid) {
        String teamname = concertService.selectTeam(userid);
        model.addAttribute("teamname", teamname);
        return "artist/register";
    }

    //공연등록
    @PostMapping("register")
    public String register(ConcertDTO concertDTO, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        //공연날짜 String으로 변환
        concertDTO.setDate(concertDTO.getDate().toString());

        //공연제목 유무
        if("" == concertDTO.getTitle()) {
            concertDTO.setTitle(concertDTO.getTeam_name()+"님의 공연");
        }

        //공연포스터 유무
        if(file == null || file.isEmpty()){

        }else {
            String orgFileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            //확장자 추출
            String extension = orgFileName.substring(orgFileName.lastIndexOf("."));
            String saveFileName = uuid + extension;
            String filePath = uploadConcert + saveFileName;
            file.transferTo(new File(filePath));

            concertDTO.setOrgfilename(orgFileName);
            concertDTO.setFilename(saveFileName);
            concertDTO.setFilepath(filePath);
        }

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
    @GetMapping("concertList")
    public String concertList(Model model, String searchDate) {
        List<ConcertDTO> concertList = null;

        //검색어 유무 확인
        if(searchDate == null) {
            concertList = concertService.concertList();
        } else {
            concertList = concertService.concertList(searchDate);
        }

        String nlString = System.getProperty("line.separator").toString();
        model.addAttribute("nlString", nlString);
        model.addAttribute("concertList", concertList);
        model.addAttribute("searchDate", searchDate);

        return "artist/concertList";
    }

    //공연상세정보
    @GetMapping("concertInfo")
    public String concertInfo(Long concert_id, Model model) {
        ConcertDTO concertDTO = concertService.concertInfo(concert_id);

        String nlString = System.getProperty("line.separator").toString();
        model.addAttribute("nlString", nlString);

        model.addAttribute("concert", concertDTO);
        return "artist/concertInfo";
    }
}
