package com.ISS_Atelie.sistemaWeb.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tbl_sacola")
public class Sacola {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATACRIACAO")
    private Date dataCriacao;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATAACERTO")
    private Date dataAcerto;

    @OneToOne
    @JoinColumn(name = "CONSULTORA_ID")
    private Consultora consultora;


    @Transient
    private List<ItemSacola> listaItens = new ArrayList<>();

    //Contrutores
    public Sacola() {
    }


    //Métodos
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAcerto() {
        return dataAcerto;
    }

    public void setDataAcerto(Date dataAcerto) {
        this.dataAcerto = dataAcerto;
    }

    public Consultora getConsultora() {
        return consultora;
    }

    public void setConsultora(Consultora consultora) {
        this.consultora = consultora;
    }

    public List<ItemSacola> getListaItens() {
        return listaItens;
    }

    public void setListaProdutos(List<ItemSacola> listaItens) {
        this.listaItens = listaItens;
    }


}
