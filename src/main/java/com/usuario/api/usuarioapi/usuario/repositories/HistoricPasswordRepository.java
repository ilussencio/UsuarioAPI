package com.usuario.api.usuarioapi.usuario.repositories;

import com.usuario.api.usuarioapi.usuario.model.HistoricPasswordModel;
import com.usuario.api.usuarioapi.usuario.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricPasswordRepository extends JpaRepository<HistoricPasswordModel, Long> {
    @Query(value = "")
    List<HistoricPasswordModel> findByUser(UserModel user);
}
