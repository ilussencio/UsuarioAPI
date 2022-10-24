package com.usuario.api.usuarioapi.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="COD_RESET")
public class CodResetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long codigo;

    @Column(nullable = false)
    private boolean validacao;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @Column(nullable = false)
    private LocalDateTime expiration;

    //REFERENCIA DO USUARIO
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private UserModel user;
}
