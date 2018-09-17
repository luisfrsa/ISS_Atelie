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
    public Integer atraso;

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

    public ContasPagarBuilder setAtraso(Integer atraso) {
        this.atraso = atraso;
        return this;
    }
}
