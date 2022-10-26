package com.usuario.api.usuarioapi.usuario.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ResetDTO {
    @NotBlank
    @Email
    private String email;
    private String token_reset;
    private String password;
}
