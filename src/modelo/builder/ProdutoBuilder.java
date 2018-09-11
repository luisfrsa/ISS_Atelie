package modelo.builder;

import static java.util.Objects.isNull;
import modelo.Produto;

public class ProdutoBuilder {

    public Integer id;
    public String descricao;
    public Double valor;
    public String cor;
    public String tamanho;
    public String marca;
    public String modelo;

    public ProdutoBuilder(String descricao) {
        if(isNull (descricao)){
            throw new IllegalArgumentException("Não é possível criar um produto sem descrição");
        }
        this.descricao = descricao;
    }
    
    public Produto build(){
        return new Produto(this);
    }
    
    public ProdutoBuilder setId(Integer id) {
        this.id = id;
        return this;
    }
    
    public ProdutoBuilder setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
    
    public ProdutoBuilder setValor(Double valor) {
        this.valor = valor;
        return this;
    }
    
    public ProdutoBuilder setCor(String cor) {
        this.cor = cor;
        return this;
    }
    
    public ProdutoBuilder setTamanho(String tamanho) {
        this.tamanho = tamanho;
        return this;
    }
    
    public ProdutoBuilder setMarca(String marca) {
        this.marca = marca;
        return this;
    }
    
    public ProdutoBuilder setModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }
    
}
