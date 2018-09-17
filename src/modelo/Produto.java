package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import modelo.builder.ProdutoBuilder;

@Entity
@Table(name = "tbl_produto")
public class Produto implements Serializable {

    //Atributos
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "valor")
    private Double valor;
    
    @Column(name = "cor")
    private String cor;
    
    @Column(name = "tamanho")
    private String tamanho;
    
    @Column(name = "marca")
    private String marca;
    
    @Column(name = "modelo")
    private String modelo;

    //Construtor
    public Produto() {
    }

    public Produto(ProdutoBuilder produtoBuilder) {
        this.id = produtoBuilder.id;
        this.descricao = produtoBuilder.descricao;
        this.valor = produtoBuilder.valor;
        this.cor = produtoBuilder.cor;
        this.tamanho = produtoBuilder.tamanho;
        this.marca = produtoBuilder.marca;
        this.modelo = produtoBuilder.modelo;
    }
    
    //Métodos

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setValor(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getId(), produto.getId()) &&
                Objects.equals(getDescricao(), produto.getDescricao()) &&
                Objects.equals(getValor(), produto.getValor()) &&
                Objects.equals(getCor(), produto.getCor()) &&
                Objects.equals(getTamanho(), produto.getTamanho()) &&
                Objects.equals(getMarca(), produto.getMarca()) &&
                Objects.equals(getModelo(), produto.getModelo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescricao(), getValor(), getCor(), getTamanho(), getMarca(), getModelo());
    }


}
