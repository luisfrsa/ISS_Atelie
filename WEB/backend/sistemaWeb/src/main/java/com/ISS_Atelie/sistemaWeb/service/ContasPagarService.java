package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.ContasPagar;
import com.ISS_Atelie.sistemaWeb.repository.ContasPagarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ISS_Atelie.sistemaWeb.util.Documentos.removePontuacao;

@Service
public class ContasPagarService {

    @Autowired
    private ContasPagarRepository contasPagarRepository;

    public List<ContasPagar> findAll() {
        return contasPagarRepository.findAll();
    }

}
