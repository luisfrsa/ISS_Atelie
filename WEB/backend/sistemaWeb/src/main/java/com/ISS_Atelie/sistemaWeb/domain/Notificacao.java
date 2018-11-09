package com.ISS_Atelie.sistemaWeb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_notificacao")
public class Notificacao implements Serializable {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA")
    private Date data;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "PRIORIDADE")
    private Integer prioridade;

    public Notificacao() {

    }

    public Integer getId() {
        return id;
    }

    public Notificacao setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Notificacao setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Notificacao setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Date getData() {
        return data;
    }

    public Notificacao setData(Date data) {
        this.data = data;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Notificacao setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public Notificacao setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
        return this;
    }
}
