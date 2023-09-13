package com.min.prodosing.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${adminMail.id}")
    private String id;

    @Value("${adminMail.pw}")
    private String password;


    @Bean
    public JavaMailSender MailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        System.out.println("id= " + id);
        System.out.println("pw= " + password);

        javaMailSender.setHost("smtp.naver.com");
        javaMailSender.setUsername(id); //관리자 메일 아이디
        javaMailSender.setPassword(password); //관리자 메일 비밀번호
        javaMailSender.setPort(465); //메일 인증서버 포트
        javaMailSender.setJavaMailProperties(getMailProperties()); //메일 인증서버 정보 가져오기

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp"); //프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true"); //smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); //smtp starttls 사용(데이터 암호화)
        properties.setProperty("mail.debug", "true"); //디버그 사용
        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com"); // ssl 인증 서버는 smtp.naver.com
        properties.setProperty("mail.smtp.ssl.enable","true"); // ssl 사용

        return properties;
    }





}
