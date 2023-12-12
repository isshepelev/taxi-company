package ru.taxicompany.taxicompany.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.taxicompany.taxicompany.domain.RegRabbit;
import ru.taxicompany.taxicompany.domain.RentAndReturnRabbit;
import ru.taxicompany.taxicompany.service.impl.SendEmailMessageService;

import javax.mail.MessagingException;

@Component
@EnableRabbit
@RequiredArgsConstructor
@Slf4j
public class EmailDistributor {
    private final SendEmailMessageService sendEmailMessageService;

    @RabbitListener(queues = "registrationQueue")
    public void registrationQueue(RegRabbit rabbit) throws MessagingException {
        sendEmailMessageService.sendRegistrationEmail(rabbit.getName(), rabbit.getEmail());
        log.info("отправлено письмо регистрации пользователю " + rabbit.getName() + " на email " + rabbit.getEmail());
    }
    @RabbitListener(queues = "authQueue")
    public void authQueue(RegRabbit rabbit) throws MessagingException {
        sendEmailMessageService.sendAuthEmail(rabbit);
        log.info("письмо о входе " + rabbit.getName() + " на email " + rabbit.getEmail());
    }
    @RabbitListener(queues = "rentQueue")
    public void rentQueue(RentAndReturnRabbit rabbit) throws MessagingException {
        sendEmailMessageService.sendRentCarEmail(rabbit);
        log.info("отправлено письмо взятия в аренду автомобиля, пользователю " + rabbit.getName() + " на email " + rabbit.getEmail());
    }
    @RabbitListener(queues = "returnQueue")
    public void returnQueue(RentAndReturnRabbit rabbit) throws MessagingException {
        sendEmailMessageService.sendReturnEmail(rabbit);
        log.info("отправлено письмо возврата автомобиля, пользователю " + rabbit.getName() + " на email " + rabbit.getEmail());
    }
    @RabbitListener(queues = "adminQueue")
    public void adminQueue(RegRabbit rabbit) throws MessagingException {
        sendEmailMessageService.sendAdminEmail(rabbit);
        log.info("письмо назначен администратором " + rabbit.getName() + " на email " + rabbit.getEmail());
    }
}