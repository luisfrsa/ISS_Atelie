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
import javax.persistence.Transient;
import modelo.builder.ContasPagarBuilder;

/**
 *
 * @author Ronnie
 */
@Entity
@Table(name = "tbl_contas_pagar")

public class ContasPagar implements Serializable {

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

    @Transient
    private Integer atraso;

    

    public ContasPagar(){
    }

    public ContasPagar(ContasPagarBuilder contasPagarBuilder) {
        this.id = contasPagarBuilder.id;
        this.baixa = contasPagarBuilder.baixa;
        this.descricao = contasPagarBuilder.descricao;
        this.valor = contasPagarBuilder.valor;
        this.vencimento = contasPagarBuilder.vencimento;
        this.entrada = contasPagarBuilder.entrada;
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

    public Integer getAtraso() {
        return atraso;
    }

    public void setAtraso(Integer atraso) {
        this.atraso = atraso;
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
                && Objects.equals(getAtraso(), that.getAtraso());
    }

     @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getValor(), getEntrada(), getBaixa(), getVencimento(), getAtraso());
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", cor='" + entrada + '\'' +
                ", tamanho='" + baixa + '\'' +
                ", marca='" + vencimento + '\'' +
                ", modelo='" + atraso + '\'' +
                '}';
    }


}
