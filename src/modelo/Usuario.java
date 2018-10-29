/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import modelo.builder.UsuarioBuilder;
@Entity
@Table(name = "tbl_Usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "senha")
    private String senha;

    @Column(name = "status")
    private String status;

    @Column(name = "cpf")
    private String cpf;

    public Usuario() {
    }

    public Usuario(UsuarioBuilder usuarioBuilder) {
        this.id = usuarioBuilder.id;
        this.nome = usuarioBuilder.nome;
        this.cargo = usuarioBuilder.cargo;
        this.usuario = usuarioBuilder.usuario;
        this.senha = usuarioBuilder.senha;
        this.status = usuarioBuilder.status;
        this.cpf = usuarioBuilder.cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario that = (Usuario) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getNome(), that.getNome())
                && Objects.equals(getCargo(), that.getCargo())
                && Objects.equals(getUsuario(), that.getUsuario())
                && Objects.equals(getSenha(), that.getSenha())
                && Objects.equals(getCpf(), that.getCpf())
                && Objects.equals(getStatus(), that.getStatus());
                
    }
    
     public int hashCode() {
        return Objects.hash(getId(), getNome(), getCargo(), getUsuario(), getSenha(), getCpf(), getStatus());
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", fornecedor='" + nome + '\'' +
                ", descricao='" + cargo + '\'' +
                ", valor=" + usuario +
                ", entrada='" + senha + '\'' +
                ", baixa='" + cpf + '\'' +
                ", status='" + status + '\'' +
               '}';
    }    
}
