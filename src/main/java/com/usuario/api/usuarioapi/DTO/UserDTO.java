package com.usuario.api.usuarioapi.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
public class UserDTO {
    @NotBlank
    private String nome;

    @NotBlank
    @Min(9)
    private String telefone;

    @NotBlank
    @Email
    private String email;
}
