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
@Table(name="HISTORIC_PASSWORD")
public class HistoricPasswordModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime date;

    //REFERENCIA DO USUARIO
    @ManyToOne(cascade = CascadeType.ALL)
    private UserModel user;
}
