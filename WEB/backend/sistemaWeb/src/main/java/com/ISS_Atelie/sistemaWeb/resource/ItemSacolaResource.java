package com.ISS_Atelie.sistemaWeb.resource;

import com.ISS_Atelie.sistemaWeb.domain.ItemSacola;
import com.ISS_Atelie.sistemaWeb.service.ItemSacolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/itemSacola")
public class ItemSacolaResource {

    @Autowired
    private ItemSacolaService itemSacolaService;

    @GetMapping("/")
    public List<ItemSacola> getAll() {
        return itemSacolaService.findAll();
    }

}
