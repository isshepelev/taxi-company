package ru.taxicompany.taxicompany.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.taxicompany.taxicompany.domain.RegRabbit;
import ru.taxicompany.taxicompany.domain.RentAndReturnRabbit;

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
    public void sendAuthEmail(RegRabbit rabbit) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", rabbit.getName());
        String html = templateEngine.process("auth",context);
        sendEmail(rabbit.getEmail(),"Вход", html);
    }

    public void sendRentCarEmail(RentAndReturnRabbit rabbit) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", rabbit.getName());
        context.setVariable("manufacturer", rabbit.getCar().getManufacturer());
        context.setVariable("model", rabbit.getCar().getModel());
        context.setVariable("price", rabbit.getCar().getPrice());
        String html = templateEngine.process("rent", context);
        sendEmail(rabbit.getEmail(), "Аренда", html);
    }

    public void sendReturnEmail(RentAndReturnRabbit rabbit) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", rabbit.getName());
        context.setVariable("manufacturer", rabbit.getCar().getManufacturer());
        context.setVariable("model", rabbit.getCar().getModel());
        context.setVariable("price", rabbit.getCar().getPrice());
        String html = templateEngine.process("return", context);
        sendEmail(rabbit.getEmail(), "Возврат", html);
    }

    public void sendAdminEmail(RegRabbit rabbit) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", rabbit.getName());
        String html = templateEngine.process("admin",context);
        sendEmail(rabbit.getEmail(), "Админ", html);
    }
}
