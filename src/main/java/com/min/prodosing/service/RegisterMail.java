package com.min.prodosing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class RegisterMail {

    @Autowired
    JavaMailSender emailSender; // Bean 등록해둔 MailConfig

    private String authNum; //인증번호

    //메일 내용
    public MimeMessage createMessage(String userEmail) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, userEmail); //보내는 대상
        message.setSubject("ProDoSing 회원가입 이메일 인증"); //제목

        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1> 안녕하세요</h1>";
        msg += "<h1> ProDoSing 입니다.</h1>";
        msg += "<br>";
        msg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요.<p>";
        msg += "<br>";
        msg += "<p>항상 아티스트와 팬들의 소통을 위해 노력하는 ProDoSing이 되겠습니다. 감사합니다!<p>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "CODE : <strong>";
        msg += authNum + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msg += "</div>";
        message.setText(msg, "utf-8", "html");// 내용, charset 타입, subtype
        message.setFrom(new InternetAddress("dnals93@naver.com", "ProDoSing_Admin")); //보내는사람의 메일주소,이름

        return message;
    }

    //랜덤 인증 코드
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for(int i=0; i<8; i++) { //인증코드 8자리
            int num = rnd.nextInt(3); // 0~2 중에 랜덤생성 후 switch문

            switch (num) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97)); // 소문자 a~z
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65)); // 대문자 A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10))); // 0~9
                    break;
            }
        }
        return key.toString();
    }

    //메일 발송
    public String sendMessage(String userEmail) throws MessagingException, UnsupportedEncodingException {
        authNum = createKey(); //랜덤 인증번호
        MimeMessage message = createMessage(userEmail);

        System.out.println("메일인증번호= " + authNum);

        try {
            emailSender.send(message);
        }catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }

        return authNum;
    }





}
