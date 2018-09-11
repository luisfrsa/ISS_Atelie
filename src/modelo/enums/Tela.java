package modelo.enums;

public enum Tela {

    PRODUTO(0, "Produto"),
    CONSULTORA(1, "Consultora");

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