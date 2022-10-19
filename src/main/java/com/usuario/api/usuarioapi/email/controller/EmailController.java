package com.usuario.api.usuarioapi.email.controller;

import com.usuario.api.usuarioapi.email.DTO.EmailDTO;
import com.usuario.api.usuarioapi.email.services.EmailService;
import com.usuario.api.usuarioapi.email.model.EmailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDTO emailDTO){
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO,emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
