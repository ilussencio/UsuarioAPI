package com.usuario.api.usuarioapi.usuario.services;
import com.usuario.api.usuarioapi.usuario.model.CodResetModel;
import com.usuario.api.usuarioapi.usuario.model.UserModel;
import com.usuario.api.usuarioapi.usuario.repositories.CodResetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class CodResetService {
    final CodResetRepository codResetRepository;
    public CodResetService(CodResetRepository codResetRepository) {
        this.codResetRepository = codResetRepository;
    }
    @Transactional
    public CodResetModel createCode(CodResetModel cod){
        return codResetRepository.save(cod);
    }

}
