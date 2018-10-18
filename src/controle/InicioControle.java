package controle;

import modelo.enums.Tela;

import static java.lang.String.format;
import static modelo.enums.Tela.PRODUTO;

public class InicioControle {

    private static final ConsultoraControle consultoraControle = new ConsultoraControle();
    private static final ProdutoControle ProdutoControle = new ProdutoControle();
    private static final SacolaControle SacolaControle = new SacolaControle();
    private static final ContasPagarControle contasPagarControle = new ContasPagarControle();
    private static final ContasReceberControle contasReceberControle = new ContasReceberControle();
            
   public void abreView(Tela tela) {
        switch (tela) {
            case PRODUTO:
                ProdutoControle.renderizarVisaoGerenciarProdutos();
                break;
            case CONSULTORA:
                consultoraControle.renderizarVisao();
                break;
            case SACOLA:
                SacolaControle.renderizarVisaoGerenciarSacolas();
                break;
            case CONTASPAGAR:
                contasPagarControle.renderizarVisaoGerenciarContasPagar();
                break;
            case CONTASRECEBER:
                contasReceberControle.renderizarVisaoGerenciarContasReceber();
                break;
                
            default:
                throw new RuntimeException(format("Tela %s ainda não implementada", tela.getNome()));
        }

    }
}
