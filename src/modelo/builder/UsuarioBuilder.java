/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.builder;

import static java.util.Objects.isNull;
import modelo.Usuario;

/**
 *
 * @author Ronnie
 */
public class UsuarioBuilder {
    public Integer id;
    public String nome;
    public String cargo;
    public String usuario;
    public String senha;
    public String tipo;
    public String lembrete;
    public String resposta;
    public String status;
    public String cpf;
    
    public UsuarioBuilder(String cpf) {
        if (isNull(cpf)) {
            throw new IllegalArgumentException("Não é possível criar uma conta sem descrição");
        }
        this.cpf = cpf;
    }

    public Usuario build() {
        return new Usuario(this);
    }

    public UsuarioBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public UsuarioBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }
    
        public UsuarioBuilder setCargo(String cargo) {
        this.cargo = cargo;
        return this;

    }

    public UsuarioBuilder setUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public UsuarioBuilder setSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public UsuarioBuilder setLembrete(String lembrete) {
        this.lembrete = lembrete;
        return this;
    }

    public UsuarioBuilder setResposta(String resposta) {
        this.resposta = resposta;
        return this;
    }

    public UsuarioBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public UsuarioBuilder setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

}
