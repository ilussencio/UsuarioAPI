package com.usuario.api.usuarioapi.email.services;

import com.usuario.api.usuarioapi.email.repositories.EmailRepository;
import com.usuario.api.usuarioapi.email.enums.StatusEmail;
import com.usuario.api.usuarioapi.email.model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    private JavaMailSenderImpl emailSenderImpl;

    public EmailModel sendEmail(EmailModel emailModel){
        emailModel.setSendDateEmail(LocalDateTime.now());
        try{
            MimeMessage message = emailSenderImpl.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(emailModel.getEmailFrom());
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText(), true);
            emailSenderImpl.send(message);
            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }

    }
}
