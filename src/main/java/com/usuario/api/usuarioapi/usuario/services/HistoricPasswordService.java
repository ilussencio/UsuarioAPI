package com.usuario.api.usuarioapi.usuario.services;

import com.usuario.api.usuarioapi.usuario.model.HistoricPasswordModel;
import com.usuario.api.usuarioapi.usuario.model.UserModel;
import com.usuario.api.usuarioapi.usuario.repositories.HistoricPasswordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricPasswordService {
    final HistoricPasswordRepository historicPasswordRepository;

    public HistoricPasswordService(HistoricPasswordRepository historicPasswordRepository) {
        this.historicPasswordRepository = historicPasswordRepository;
    }

    public List<HistoricPasswordModel> find(UserModel user){
        return historicPasswordRepository.findByUser(user);
    }
    public HistoricPasswordModel save(HistoricPasswordModel historicPasswordModel){
        return historicPasswordRepository.save(historicPasswordModel);
    }
}