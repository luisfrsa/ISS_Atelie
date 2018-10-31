package modelo.builder;

import modelo.Notificacao;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static java.util.Objects.isNull;

public class NotificacaoBuilder {

    public Integer id;
    public String nome;
    public String descricao;
    public Date data;
    public Integer prioridade;
    public Boolean status;

    public NotificacaoBuilder(String nome) {
        if (isNull(nome)) {
            throw new IllegalArgumentException("Não é possível criar uma notificacao sem nome");
        }
        this.nome = nome;
    }

    public Notificacao build() {
        return new Notificacao(this);
    }

    public NotificacaoBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public NotificacaoBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public NotificacaoBuilder setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public NotificacaoBuilder setData(Date data) {
        this.data = data;
        return this;
    }

    public NotificacaoBuilder setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
        return this;
    }
    
     public NotificacaoBuilder setStatus(Boolean status) {
        this.status = status;
        return this;
    }
}
