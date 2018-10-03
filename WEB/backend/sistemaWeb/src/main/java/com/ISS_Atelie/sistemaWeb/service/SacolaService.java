package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.Sacola;
import com.ISS_Atelie.sistemaWeb.repository.SacolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SacolaService {

    @Autowired
    private SacolaRepository sacolaRepository;

    public List<Sacola> findAll() {
        return sacolaRepository.findAll();
    }

}
