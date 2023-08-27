package com.user.usermanage.user.config.email;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
@Setter
public class MailSenderRunner  {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    public String to;

//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        MimeMessage m = mailSender.createMimeMessage();
//        MimeMessageHelper h = new MimeMessageHelper(m,"UTF-8");
//        h.setFrom(from);
//        h.setTo(userEmail);
//        h.setSubject("테스트메일");
//        h.setText("메일테스트");
//        mailSender.send(m);
//    }

    public void sendEmail(String subject, String text) throws Exception {
        MimeMessage m = mailSender.createMimeMessage();
        MimeMessageHelper h = new MimeMessageHelper(m, "UTF-8");
        h.setFrom(from);
        h.setTo(to);
        h.setSubject(subject);
        h.setText(text);
        mailSender.send(m);
    }
}