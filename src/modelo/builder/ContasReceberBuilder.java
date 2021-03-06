/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.builder;

import java.util.Date;
import static java.util.Objects.isNull;
import modelo.ContasReceber;

/**
 *
 * @author Ronnie
 */
public class ContasReceberBuilder {

    public Integer id;
    public Double valor;
    public String descricao;
    public Date entrada;
    public Date baixa;
    public Date vencimento;
    public String status;
    public String cliente;
    public String formaPagamento;
    public String user_entrada;
    public String user_baixa;
    public String user_remocao;
    public Date atraso;
    
    public ContasReceberBuilder(String descricao) {
        if (isNull(descricao)) {
            throw new IllegalArgumentException("Não é possível criar uma conta sem descrição");
        }
        this.descricao = descricao;
    }

    public ContasReceber build() {
        return new ContasReceber(this);
    }

    public ContasReceberBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ContasReceberBuilder setValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public ContasReceberBuilder setEntrada(Date entrada) {
        this.entrada = entrada;
        return this;

    }

    public ContasReceberBuilder setBaixa(Date baixa) {
        this.baixa = baixa;
        return this;
    }

    public ContasReceberBuilder setVencimento(Date vencimento) {
        this.vencimento = vencimento;
        return this;
    }

    public ContasReceberBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ContasReceberBuilder setCliente(String cliente) {
        this.cliente = cliente;
        return this;
    }

    public ContasReceberBuilder setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        return this;
    }

    public ContasReceberBuilder setUser_entrada(String user_entrada) {
        this.user_entrada = user_entrada;
        return this;
    }

    public ContasReceberBuilder setUser_baixa(String user_baixa) {
        this.user_baixa = user_baixa;
        return this;
    }

    public ContasReceberBuilder setUser_remocao(String user_remocao) {
        this.user_remocao = user_remocao;
        return this;
    }
    
    public ContasReceberBuilder setAtraso(Date atraso) {
        this.atraso=atraso;
        return this;
    }
    
    
    
}
