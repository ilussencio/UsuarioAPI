package com.usuario.api.usuarioapi;

import com.usuario.api.usuarioapi.model.UserModel;
import com.usuario.api.usuarioapi.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class UsuarioApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioApiApplication.class, args);
    }

}
