package com.usuario.api.usuarioapi.usuario.repositories;

import com.usuario.api.usuarioapi.usuario.model.UserModel;
import org.h2.engine.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByUsername(String username);

}
