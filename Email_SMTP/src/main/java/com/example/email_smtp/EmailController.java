package com.example.email_smtp;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;


@RestController

@RequiredArgsConstructor
public class EmailController {


    private final JavaMailSender mailSender;


    @RequestMapping("/send-email")
    public String sendEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("your-email@gmail.com");
            message.setTo("sent-to-email@gmail.com");
            message.setSubject("Simple text email from me");
            message.setText("This is a sample");

            mailSender.send(message);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @RequestMapping("/send-email-with-attachment")
    public String sendEmailWithAttachment() {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

//            SimpleMailMessage message = new SimpleMailMessage();

            helper.setFrom("your-email@gmail.com");
            helper.setTo("sent-to@gmail.com");
            helper.setSubject("A java sample text email from me ");
            helper.setText("Please find the attached docs");

            helper.addAttachment("filename.type- exmaple- img.png", new File("file path"));
            helper.addAttachment("filename.type", new File("file path"));
            mailSender.send(message);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
