package com.ISS_Atelie.sistemaWeb.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tbl_consultora")
public class Consultora implements Serializable {

    //Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "SENHA")
    private String senha;

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATANASCIMENTO")
    private Date dataNascimento;

    @Column(name = "STATUSATIVIDADE")
    private boolean statusAtividade;

    public Consultora() {

    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public Consultora setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public boolean isStatusAtividade() {
        return statusAtividade;
    }

    public Consultora setStatusAtividade(boolean statusAtividade) {
        this.statusAtividade = statusAtividade;
        return this;
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
