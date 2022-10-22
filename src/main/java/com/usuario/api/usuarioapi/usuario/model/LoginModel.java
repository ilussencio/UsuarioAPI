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
@Table(name="user_login")
public class LoginModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_login;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserModel user;
}
