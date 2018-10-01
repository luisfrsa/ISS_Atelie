package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.ItemSacola;
import com.ISS_Atelie.sistemaWeb.repository.ItemSacolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemSacolaService {

    @Autowired
    private ItemSacolaRepository itemSacolaRepository;

    public List<ItemSacola> findAll() {
        return itemSacolaRepository.findAll();
    }

}
