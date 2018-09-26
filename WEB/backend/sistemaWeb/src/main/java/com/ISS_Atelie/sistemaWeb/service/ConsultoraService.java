package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.Consultora;
import com.ISS_Atelie.sistemaWeb.repository.ConsultoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultoraService {

    @Autowired
    private ConsultoraRepository consultoraRepository;

    public List<Consultora> findAll() {
        return consultoraRepository.findAll();
    }
}
