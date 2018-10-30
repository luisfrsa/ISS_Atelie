package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.Notificacao;
import com.ISS_Atelie.sistemaWeb.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ISS_Atelie.sistemaWeb.util.Documentos.removePontuacao;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> findAll() {
        return notificacaoRepository
                .findAll()
                .stream()
                .filter(notificacao -> notificacao.isStatus())
                .collect(Collectors.toList());
    }

}
