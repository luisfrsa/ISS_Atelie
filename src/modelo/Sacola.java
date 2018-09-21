package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import modelo.builder.SacolaBuilder;

@Entity
@Table(name = "tbl_sacola")
public class Sacola {

    //Atributos
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "dataCriacao")
    private Date dataCriacao;
    
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "dataAcerto")
    private Date dataAcerto;
    
    @OneToOne
    @JoinColumn(name = "consultora_id")
    private Consultora consultora;
    
    @OneToMany    
    private List <ItemSacola> listaItens = new ArrayList<>();

    //Contrutores
    public Sacola() {
    }
    
    public Sacola(SacolaBuilder sacolabuilder) {
        this.id = sacolabuilder.id;
        this.dataCriacao = sacolabuilder.dataCriacao;
        this.dataAcerto = sacolabuilder.dataAcerto;
        this.consultora = sacolabuilder.consultora;
        this.listaItens = sacolabuilder.itensSacola;
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

    public List<ItemSacola> getListaItens() {
        return listaItens;
    }

    public void setListaProdutos(List<ItemSacola> listaItens) {
        this.listaItens = listaItens;
    }
    
    
    
    
}
