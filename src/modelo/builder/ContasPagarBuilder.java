/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.builder;

import java.util.Date;
import static java.util.Objects.isNull;
import modelo.ContasPagar;

/**
 *
 * @author Ronnie
 */
public class ContasPagarBuilder {

    public Integer id;
    public Double valor;
    public String descricao;
    public Date entrada;
    public Date baixa;
    public Date vencimento;
    public String status;
    public String fornecedor;
    public String formaPagamento;
    public String user_entrada;
    public String user_baixa;
    public String user_remocao;
    public Date atraso;
    
    public ContasPagarBuilder(String descricao) {
        if (isNull(descricao)) {
            throw new IllegalArgumentException("Não é possível criar uma conta sem descrição");
        }
        this.descricao = descricao;
    }

    public ContasPagar build() {
        return new ContasPagar(this);
    }

    public ContasPagarBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ContasPagarBuilder setValor(Double valor) {
        this.valor = valor;
        return this;
    }

    public ContasPagarBuilder setEntrada(Date entrada) {
        this.entrada = entrada;
        return this;

    }

    public ContasPagarBuilder setBaixa(Date baixa) {
        this.baixa = baixa;
        return this;
    }

    public ContasPagarBuilder setVencimento(Date vencimento) {
        this.vencimento = vencimento;
        return this;
    }

    public ContasPagarBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public ContasPagarBuilder setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
        return this;
    }

    public ContasPagarBuilder setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
        return this;
    }

    public ContasPagarBuilder setUser_entrada(String user_entrada) {
        this.user_entrada = user_entrada;
        return this;
    }

    public ContasPagarBuilder setUser_baixa(String user_baixa) {
        this.user_baixa = user_baixa;
        return this;
    }

    public ContasPagarBuilder setUser_remocao(String user_remocao) {
        this.user_remocao = user_remocao;
        return this;
    }

    public ContasPagarBuilder setAtraso(Date atraso) {
        this.atraso = atraso;
        return this;
    }



}
