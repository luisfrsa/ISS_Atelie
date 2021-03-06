package com.ISS_Atelie.sistemaWeb.resource;

import com.ISS_Atelie.sistemaWeb.domain.Sacola;
import com.ISS_Atelie.sistemaWeb.service.SacolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/sacola")
public class SacolaResource {

    @Autowired
    private SacolaService sacolaService;

    @GetMapping("/")
    public List<Sacola> getAll() {
        List<Sacola> list = sacolaService.findAll();
        list.addAll(sacolaService.findAll());
        return list;
    }

}
