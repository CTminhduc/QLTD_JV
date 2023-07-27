package com.java08.quanlituyendung.service.impl;

import com.java08.quanlituyendung.service.IMailService;
import com.java08.quanlituyendung.service.IThymeleafService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    IThymeleafService thymeleafService;
    @Value("${spring.mail.username}")
    private String email;
    @Override
    public boolean sendVerificationMail(String mail, String code) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Map<String,Object> variables= new HashMap<>();
            variables.put("code", code);

            helper.setFrom(email);
            helper.setTo(mail);
            helper.setSubject("Verification Mail");
            helper.setText(thymeleafService.createContent("welcome.html",variables),true);

            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean sendResetPasswordMail(String mail, String code) {
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            Map<String,Object> variables= new HashMap<>();
            variables.put("code", code);

            helper.setFrom(email);
            helper.setTo(mail);
            helper.setSubject("Reset Password Mail");
            helper.setText(thymeleafService.createContent("resetpassword.html",variables),true);

            javaMailSender.send(message);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
