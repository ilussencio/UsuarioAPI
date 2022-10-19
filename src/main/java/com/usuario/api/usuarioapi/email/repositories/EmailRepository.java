package com.usuario.api.usuarioapi.email.repositories;

import com.usuario.api.usuarioapi.email.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
