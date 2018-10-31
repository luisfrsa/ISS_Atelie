package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.ItemSacola;
import com.ISS_Atelie.sistemaWeb.domain.Sacola;
import com.ISS_Atelie.sistemaWeb.repository.SacolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SacolaService {

    @Autowired
    private SacolaRepository sacolaRepository;

    @Autowired
    private ItemSacolaService itemSacolaService;


    public List<Sacola> findAll() {
        List<Sacola> sacolas = sacolaRepository.findAll();

        return sacolas
                .stream()
                .map(sacola -> {
                    List<ItemSacola> listaProdutos = itemSacolaService.findAll();
                    sacola.setListaProdutos(listaProdutos);
                    return sacola;
                })
                .collect(Collectors.toList());
    }

}
