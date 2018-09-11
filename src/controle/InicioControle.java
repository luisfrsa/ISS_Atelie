package controle;

import modelo.enums.Tela;

import static java.lang.String.format;
import static modelo.enums.Tela.PRODUTO;

public class InicioControle {

    private static final ConsultoraControle consultoraControle = new ConsultoraControle();
    private static final GerenciarProdutosControle gerenciarProdutosControle = new GerenciarProdutosControle();

    public void abreView(Tela tela) {
        switch (tela) {
            case PRODUTO:
                gerenciarProdutosControle.renderizarVisao();
                break;
            case CONSULTORA:
                consultoraControle.renderizarVisao();
                break;
            default:
                throw new RuntimeException(format("Tela %s ainda não implementada", tela.getNome()));
        }

    }
}
