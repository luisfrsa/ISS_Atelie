package com.ISS_Atelie.sistemaWeb.service;

import com.ISS_Atelie.sistemaWeb.domain.Produto;
import com.ISS_Atelie.sistemaWeb.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

}
