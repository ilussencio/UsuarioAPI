package com.usuario.api.usuarioapi.usuario.repositories;

import com.usuario.api.usuarioapi.usuario.model.LoginModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Long> {
}
