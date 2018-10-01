package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.Consultora;
import com.ISS_Atelie.sistemaWeb.repository.ConsultoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ISS_Atelie.sistemaWeb.util.Documentos.removePontuacao;

@Service
public class ConsultoraService {

    @Autowired
    private ConsultoraRepository consultoraRepository;

    public List<Consultora> findAll() {
        return consultoraRepository.findAll();
    }

    public Consultora findByCpf(String cpf, String password) {
        cpf = removePontuacao(cpf);
        Optional<Consultora> consultora = consultoraRepository.findByCpfAndSenha(cpf,password);
        return consultora.orElseThrow(()->new RuntimeException("Login/Senha incorretos"));
    }
}
