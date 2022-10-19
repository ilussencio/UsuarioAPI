package com.usuario.api.usuarioapi.usuario.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank
    private String nome;

    @Min(9)
    private String telefone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String usuario;

    @NotBlank
    private String senha;
}
