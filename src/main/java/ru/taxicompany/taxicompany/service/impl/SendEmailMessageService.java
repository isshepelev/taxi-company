package ru.taxicompany.taxicompany.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class SendEmailMessageService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String email, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(text,true);

        mailSender.send(message);
    }

    public void sendRegistrationEmail(String name,String email) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        String html = templateEngine.process("registration", context);
        sendEmail(email, "Спасибо что выбираете нас", html);
    }
}
