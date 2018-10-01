package com.ISS_Atelie.sistemaWeb.resource;

import com.ISS_Atelie.sistemaWeb.domain.Produto;
import com.ISS_Atelie.sistemaWeb.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    public List<Produto> getAll() {
        return produtoService.findAll();
    }

}
