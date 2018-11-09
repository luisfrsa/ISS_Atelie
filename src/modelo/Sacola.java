package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
    
    @Column(name = "status_finilizada")
    private boolean finalizada;
    
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "data_criacao")
    private Date dataCriacao;
    
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "data_acerto")
    private Date dataAcerto;
    
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "data_finalizada")
    private Date dataFinalizada;
    
    @OneToOne
    @JoinColumn(name = "consultora_id")
    private Consultora consultora;
    
    @OneToMany(cascade = CascadeType.ALL)   
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

    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

    public void setListaItens(List<ItemSacola> listaItens) {
        this.listaItens = listaItens;
    }

    public Date getDataFinalizada() {
        return dataFinalizada;
    }

    public void setDataFinalizada(Date dataFinalizada) {
        this.dataFinalizada = dataFinalizada;
    }
    

    @Override
    public String toString() {
        return "Sacola{" + "id=" + id + ", finalizada=" + finalizada + ", dataCriacao=" + dataCriacao + ", dataAcerto=" + dataAcerto + ", consultora=" + consultora + ", listaItens=" + listaItens + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + (this.finalizada ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.dataCriacao);
        hash = 97 * hash + Objects.hashCode(this.dataAcerto);
        hash = 97 * hash + Objects.hashCode(this.consultora);
        hash = 97 * hash + Objects.hashCode(this.listaItens);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sacola other = (Sacola) obj;
        if (this.finalizada != other.finalizada) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dataCriacao, other.dataCriacao)) {
            return false;
        }
        if (!Objects.equals(this.dataAcerto, other.dataAcerto)) {
            return false;
        }
        if (!Objects.equals(this.consultora, other.consultora)) {
            return false;
        }
        if (!Objects.equals(this.listaItens, other.listaItens)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
