package com.ISS_Atelie.sistemaWeb.domain;

import javax.persistence.*;

@Entity
@Table(name = "tbl_item_sacola")
public class ItemSacola {

    //Atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;
    
    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    public ItemSacola() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
