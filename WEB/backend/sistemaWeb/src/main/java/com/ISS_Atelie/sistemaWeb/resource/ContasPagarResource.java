package com.ISS_Atelie.sistemaWeb.resource;

import com.ISS_Atelie.sistemaWeb.domain.ContasPagar;
import com.ISS_Atelie.sistemaWeb.service.ContasPagarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/contasPagar")
public class ContasPagarResource {

    @Autowired
    private ContasPagarService contasPagarService;

    @GetMapping("/")
    public List<ContasPagar> getAll() {
        return contasPagarService.findAll();
    }

}
