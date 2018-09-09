package modelo;

import modelo.builder.ConsultoraBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_consultora")
public class Consultora implements Serializable {

    //Atributos

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "dataNascimento")
    private Date dataNascimento;

    @Column(name = "statusAtividade")
    private Boolean statusAtividade;

    public Consultora(){

    }

    public Consultora(ConsultoraBuilder consultoraBuilder) {
        this.id = consultoraBuilder.id;
        this.nome = consultoraBuilder.nome;
        this.cpf = consultoraBuilder.cpf;
        this.dataNascimento = consultoraBuilder.dataNascimento;
        this.statusAtividade = consultoraBuilder.statusAtividade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(Boolean statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consultora)) return false;
        Consultora that = (Consultora) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getNome(), that.getNome()) &&
                Objects.equals(getCpf(), that.getCpf()) &&
                Objects.equals(getDataNascimento(), that.getDataNascimento()) &&
                Objects.equals(getStatusAtividade(), that.getStatusAtividade());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCpf(), getDataNascimento(), getStatusAtividade());
    }

    @Override
    public String toString() {
        return "Consultora{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", statusAtividade=" + statusAtividade +
                '}';
    }
}
