package com.ISS_Atelie.sistemaWeb.resource;

import com.ISS_Atelie.sistemaWeb.domain.Consultora;
import com.ISS_Atelie.sistemaWeb.service.ConsultoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/consultora/")
public class ConsultoraResource {

    @Autowired
    private ConsultoraService consultoraService;

    @GetMapping("/")
    public List<Consultora> getAll() {
        return consultoraService.findAll();
    }
}
