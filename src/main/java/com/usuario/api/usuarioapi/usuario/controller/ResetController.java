package com.usuario.api.usuarioapi.usuario.controller;

import com.usuario.api.usuarioapi.email.model.EmailModel;
import com.usuario.api.usuarioapi.email.services.EmailService;
import com.usuario.api.usuarioapi.usuario.DTO.ResetDTO;
import com.usuario.api.usuarioapi.usuario.error.ErrorMessage;
import com.usuario.api.usuarioapi.usuario.model.HistoricPasswordModel;
import com.usuario.api.usuarioapi.usuario.model.UserModel;
import com.usuario.api.usuarioapi.usuario.repositories.HistoricPasswordRepository;
import com.usuario.api.usuarioapi.usuario.services.HistoricPasswordService;
import com.usuario.api.usuarioapi.usuario.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/reset")
public class ResetController {

    final UserService userService;
    final EmailService emailService;

    final HistoricPasswordService historicPasswordService;

    public ResetController(UserService userService, EmailService emailService, HistoricPasswordService historicPasswordService) {
        this.userService = userService;
        this.emailService = emailService;
        this.historicPasswordService = historicPasswordService;
    }

    @PostMapping("/forgot_token")
    public ResponseEntity<Object> generateToken(@RequestBody @Valid ResetDTO resetDTO){
        Optional<UserModel> userModelOptional = userService.findByEmail(resetDTO.getEmail());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        //VERIFICANDO SE O EMAIL EXISTE
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("E-mail não encontrado!"));
        }

        //GERANDO TOKEN
        Random gerador = new Random();
        StringBuilder token = new StringBuilder();
        for(int i = 0; i < 6; i ++){
            token.append(gerador.nextInt(10));
        }

        //ATUALIZANDO TABELA
        UserModel userModel = userModelOptional.get();
        userModel.setToken_reset(String.valueOf(token));
        userModel.setToken_expire(now.plusHours(2));

        //ATUALIZANDO HISTORICO DE SENHA
        HistoricPasswordModel historic = new HistoricPasswordModel();
        historic.setUser(userModel);
        historic.setPassword(userModel.getPassword());
        historic.setDate(now);
        historicPasswordService.save(historic);
        userModel.setPassword("false");

        //ENVIANDO EMAIL
        EmailModel emailModel = new EmailModel();
        emailModel.setEmailTo(userModelOptional.get().getEmail());
        emailModel.setEmailFrom("ilussencio@gmail.com");
        emailModel.setOwnerRef(userModelOptional.get().getUsername());
        emailModel.setSubject("SUPORTE: Redefinição de senha");
        emailModel.setText("<html><center><b>Olá "+userModelOptional.get().getName()+"</b> <p> Vimos que você solicitou uma alteração de senha. Utilize este PIN para redefinir sua senha. </p> <p>PIN temporário:</p> <h1>"+userModel.getToken_reset()+"</h1> <p> Caso tenha alguma dúvida entre em contato com nosso suporte ficaremos felizes em ajudar.</center></html>");
        System.out.println(emailService.sendEmail(emailModel));

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

    @PostMapping("/valid_token")
    public ResponseEntity<Object> validToken(@RequestBody @Valid ResetDTO resetDTO){
        Optional<UserModel> userModelOptional = userService.findByEmail(resetDTO.getEmail());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));


        //VERIFICANDO SE O EMAIL EXISTE
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("E-mail não encontrado!"));
        }

        //VERIFICANDO DATA DE EXPIRACAO
        if( userModelOptional.get().getToken_expire().isBefore(now) ){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("Token expirado, por favor gere um novo token!"));
        }

        //VERIFICANDO TOKEN
        if( !userModelOptional.get().getToken_reset().equalsIgnoreCase(resetDTO.getToken_reset()) ){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("Token invalido!"));
        }

        //VERIFICANDO TOKEN VALIDADO
        if(userModelOptional.get().getToken_reset().equalsIgnoreCase("false")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("Token ja utilizado!"));
        }
        List<HistoricPasswordModel> list = historicPasswordService.find(userModelOptional.get());
        for(int i = 0; i < list.length; i ++){

        }
        if(historicPasswordService.find(userModelOptional.get()).contains(resetDTO.getPassword()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage("Senha ja utilizada anteriormente"));

        //ATUALIZANDO TABELA
        UserModel userModel = userModelOptional.get();
        userModel.setToken_reset(String.valueOf(false));
        userModel.setPassword(resetDTO.getPassword());
        userModel.setUpdateAt(now);

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }
}
