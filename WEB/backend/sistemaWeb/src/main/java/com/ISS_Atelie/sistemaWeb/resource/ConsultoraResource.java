package com.ISS_Atelie.sistemaWeb.resource;

import com.ISS_Atelie.sistemaWeb.domain.Consultora;
import com.ISS_Atelie.sistemaWeb.service.ConsultoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/consultora")
public class ConsultoraResource {

    @Autowired
    private ConsultoraService consultoraService;

    @GetMapping("/")
    public List<Consultora> getAll() {
        return consultoraService.findAll();
    }

    @GetMapping("/findByCpf/{cpf}/{password}")
    public Consultora findByCpf(@PathVariable String cpf,@PathVariable String password) {
        return consultoraService.findByCpf(cpf,password);
    }
}
