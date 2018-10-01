/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ISS_Atelie.sistemaWeb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Ronnie
 */
@Entity
@Table(name = "tbl_contas_pagar")

public class ContasPagar implements Serializable {
//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "ENTRADA")
    private Date entrada;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "BAIXA")
    private Date baixa;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "VENCIMENTO")
    private Date vencimento;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "FORNECEDOR")
    private String fornecedor;

    @Column(name = "FORMA_PAGAMENTO")
    private String formaPagamento;
    
    
    //Construtores
    public ContasPagar(){
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
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

    
    public void setValor(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContasPagar)) {
            return false;
        }
        ContasPagar that = (ContasPagar) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getValor(), that.getValor())
                && Objects.equals(getDescricao(), that.getDescricao())
                && Objects.equals(getEntrada(), that.getEntrada())
                && Objects.equals(getBaixa(), that.getBaixa())
                && Objects.equals(getVencimento(), that.getVencimento())
                && Objects.equals(getFornecedor(), that.getFornecedor())
                && Objects.equals(getStatus(), that.getStatus())
                && Objects.equals(getFormaPagamento(), that.getFormaPagamento());
    }

     @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getValor(), getEntrada(), getBaixa(), getVencimento(), getFornecedor(), getFormaPagamento(), getStatus());
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", fornecedor='" + fornecedor + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", entrada='" + entrada + '\'' +
                ", baixa='" + baixa + '\'' +
                ", vencimento='" + vencimento + '\'' +
                ", status='" + status + '\'' +
                ", formadePagemento='" + formaPagamento + '\'' +
               '}';
    }


}
