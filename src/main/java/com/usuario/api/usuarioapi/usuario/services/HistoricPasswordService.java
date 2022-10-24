package com.usuario.api.usuarioapi.usuario.services;

import com.usuario.api.usuarioapi.usuario.repositories.HistoricPasswordRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoricPasswordService {
    final HistoricPasswordRepository historicPasswordRepository;

    public HistoricPasswordService(HistoricPasswordRepository historicPasswordRepository) {
        this.historicPasswordRepository = historicPasswordRepository;
    }
}