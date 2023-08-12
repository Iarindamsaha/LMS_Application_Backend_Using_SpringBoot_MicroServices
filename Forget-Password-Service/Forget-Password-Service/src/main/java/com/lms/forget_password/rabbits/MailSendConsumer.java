package com.lms.forget_password.rabbits;

import com.lms.forget_password.dto.EmailSendingDTO;
import com.lms.forget_password.utility.EmailSending;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailSendConsumer {

    @Autowired
    EmailSending emailSending;

    @RabbitListener(queues = "${rabbit.queue.name}" )
    public void sendEmails(EmailSendingDTO emailSendDTO){
        emailSending.sendMail(emailSendDTO.body(),emailSendDTO.subject(),emailSendDTO.email());
        log.info("Email Sent Successfully"+" email id : " + emailSendDTO.email());
    }
}
