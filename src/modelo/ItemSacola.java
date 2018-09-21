package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_item_sacola")
public class ItemSacola {

    //Atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    
    @Column(name = "quantidade")
    private Integer quantidade;

    public ItemSacola() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
