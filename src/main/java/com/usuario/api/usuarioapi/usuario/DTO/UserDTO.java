package com.usuario.api.usuarioapi.usuario.DTO;

import lombok.Data;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String fone;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
