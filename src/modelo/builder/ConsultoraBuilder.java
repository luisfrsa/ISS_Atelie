package modelo.builder;

import modelo.Consultora;
import java.util.Date;
import static java.util.Objects.isNull;

public class ConsultoraBuilder {

    public Integer id;
    public String nome;
    public String cpf;
    public Date dataNascimento;
    public Boolean statusAtividade;


    public ConsultoraBuilder(String nome){
        if (isNull(nome)) {
            throw new IllegalArgumentException("Não é possível criar uma consultora sem nome");
        }
        this.nome = nome;
    }

    public Consultora build(){
        return new Consultora(this);
    }
    
    public ConsultoraBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ConsultoraBuilder setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ConsultoraBuilder setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public ConsultoraBuilder setStatusAtividade(Boolean statusAtividade) {
        this.statusAtividade = statusAtividade;
        return this;
    }
}
