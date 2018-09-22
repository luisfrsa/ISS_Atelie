package modelo.builder;

import java.util.Date;
import java.util.List;
import modelo.Consultora;
import modelo.ItemSacola;
import modelo.Produto;
import modelo.Sacola;

public class SacolaBuilder {

    public Integer id;
    public Date dataCriacao;
    public Date dataAcerto;
    public Consultora consultora;    
    public List <ItemSacola> itensSacola;
    
    public Sacola build(){
        return new Sacola(this);
    }

    public SacolaBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public SacolaBuilder setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public SacolaBuilder setDataAcerto(Date dataAcerto) {
        this.dataAcerto = dataAcerto;
        return this;
    }

    public SacolaBuilder setConsultora(Consultora consultora) {
        this.consultora = consultora;
        return this;
    }

    public SacolaBuilder setListaProdutos(List<ItemSacola> listaItens) {
        this.itensSacola = listaItens;
        return this;
    }
    
    
}
