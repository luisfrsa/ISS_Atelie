/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import modelo.builder.ContasReceberBuilder;

/**
 *
 * @author Ronnie
 */
@Entity
@Table(name = "tbl_contas_receber")

public class ContasReceber implements Serializable {
//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "descricao")
    private String descricao;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "entrada")
    private Date entrada;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "baixa")
    private Date baixa;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "vencimento")
    private Date vencimento;

    @Column(name = "status")
    private String status;

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "forma_pagamento")
    private String formaPagamento;
    
    @Column(name = "user_entrada")
    private String user_entrada;
    
    @Column(name = "user_baixa")
    private String user_baixa;
    
    @Column(name = "user_remocao")
    private String user_remocao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "atraso")
    private Date atraso;
    //Construtores
    public ContasReceber(){
    }

    public ContasReceber(ContasReceberBuilder contasReceberBuilder) {
        this.id = contasReceberBuilder.id;
        this.baixa = contasReceberBuilder.baixa;
        this.descricao = contasReceberBuilder.descricao;
        this.valor = contasReceberBuilder.valor;
        this.vencimento = contasReceberBuilder.vencimento;
        this.entrada = contasReceberBuilder.entrada;
        this.status = contasReceberBuilder.status;
        this.cliente = contasReceberBuilder.cliente;
        this.formaPagamento = contasReceberBuilder.formaPagamento;
        this.user_entrada=contasReceberBuilder.user_entrada;
        this.user_baixa=contasReceberBuilder.user_baixa;
        this.user_remocao=contasReceberBuilder.user_remocao;
        this.atraso=contasReceberBuilder.atraso;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getBaixa() {
        return baixa;
    }

    public void setBaixa(Date baixa) {
        this.baixa = baixa;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getUser_entrada() {
        return user_entrada;
    }

    public void setUser_entrada(String user_entrada) {
        this.user_entrada = user_entrada;
    }

    public String getUser_baixa() {
        return user_baixa;
    }

    public void setUser_baixa(String user_baixa) {
        this.user_baixa = user_baixa;
    }

    public String getUser_remocao() {
        return user_remocao;
    }

    public void setUser_remocao(String user_remocao) {
        this.user_remocao = user_remocao;
    }

    public Date getAtraso() {
        return atraso;
    }

    public void setAtraso(Date atraso) {
        this.atraso = atraso;
    }

    
 
    
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContasReceber)) {
            return false;
        }
        ContasReceber that = (ContasReceber) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getValor(), that.getValor())
                && Objects.equals(getDescricao(), that.getDescricao())
                && Objects.equals(getEntrada(), that.getEntrada())
                && Objects.equals(getBaixa(), that.getBaixa())
                && Objects.equals(getVencimento(), that.getVencimento())
                && Objects.equals(getCliente(), that.getCliente())
                && Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getFormaPagamento(), that.getFormaPagamento())
                && Objects.equals(getUser_entrada(), that.getUser_entrada())
                && Objects.equals(getUser_baixa(), that.getUser_baixa())
                && Objects.equals(getUser_remocao(), that.getUser_remocao())
                && Objects.equals(getAtraso(), that.getAtraso());
    }

     @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getValor(), getEntrada(), getBaixa(), getVencimento(), getCliente(), getFormaPagamento(), getStatus(), getUser_entrada(), getUser_baixa(), getUser_remocao(), getAtraso());
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", fornecedor='" + cliente + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", entrada='" + entrada + '\'' +
                ", baixa='" + baixa + '\'' +
                ", vencimento='" + vencimento + '\'' +
                ", status='" + status + '\'' +
                ", formadePagamento='" + formaPagamento + '\'' +
                ", user_entrada='" + user_entrada + '\'' +
                ", user_baixa='" + user_baixa + '\'' +
                ", user_remocao='" + user_remocao + '\'' +
                ", atraso='" + atraso + '\'' +
                '}';
    }


}
