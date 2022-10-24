package com.usuario.api.usuarioapi.usuario.repositories;

import com.usuario.api.usuarioapi.usuario.model.HistoricPasswordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricPasswordRepository extends JpaRepository<HistoricPasswordModel, Long> {
}
