package com.ISS_Atelie.sistemaWeb.resource;

import com.ISS_Atelie.sistemaWeb.domain.Notificacao;
import com.ISS_Atelie.sistemaWeb.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/notificacoes")
public class NotificacaoResource {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/")
    public List<Notificacao> getAll() {
        return notificacaoService.findAll();
    }

}
