package com.usuario.api.usuarioapi.usuario.services;

import com.usuario.api.usuarioapi.usuario.repositories.LoginRepository;

public class LoginService {
    final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


}
