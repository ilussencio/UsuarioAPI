package com.usuario.api.usuarioapi.usuario.DTO;

import com.usuario.api.usuarioapi.usuario.model.UserModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class LoginDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private boolean status;
    @NotBlank
    private LocalDateTime createAt;
    @NotBlank
    private UserModel user;
}
