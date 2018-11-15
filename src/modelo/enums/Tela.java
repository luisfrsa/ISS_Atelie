package modelo.enums;

public enum Tela {

    PRODUTO(0, "Produto"),
    CONSULTORA(1, "Consultora"),
    SACOLA(2, "Sacola"),
    CONTASPAGAR(3, "ContasPagar"),
    CONTASRECEBER(4, "ContasReceber"),
    USUARIO(5, "Usuario"),
    NOTIFICACAO(6, "Notificacao"),
    FREQUENCIA(7, "Frequência");

    private Integer codigo;
    private String nome;

    Tela(Integer codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }
}
