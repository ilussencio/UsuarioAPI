package com.usuario.api.usuarioapi.usuario.repositories;

import com.usuario.api.usuarioapi.usuario.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmail(String email);
}
