package modelo;

import java.util.Date;
import java.util.List;
import modelo.builder.SacolaBuilder;

public class Sacola {

    //Atributos
    private Integer id;
    private Date dataCriacao;
    private Date dataAcerto;
    private Consultora consultora;    
    private List <Produto> listaProdutos;

    //Contrutores
    public Sacola() {
    }
    
    public Sacola(SacolaBuilder sacolabuilder) {
        this.id = sacolabuilder.id;
        this.dataCriacao = sacolabuilder.dataCriacao;
        this.dataAcerto = sacolabuilder.dataAcerto;
        this.consultora = sacolabuilder.consultora;
        this.listaProdutos = sacolabuilder.listaProdutos;
    }
    
    //Métodos
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {    
        this.id = id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAcerto() {
        return dataAcerto;
    }

    public void setDataAcerto(Date dataAcerto) {
        this.dataAcerto = dataAcerto;
    }

    public Consultora getConsultora() {
        return consultora;
    }

    public void setConsultora(Consultora consultora) {
        this.consultora = consultora;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
    
    
    
    
}
