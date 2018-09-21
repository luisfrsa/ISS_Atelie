package controle;

import dao.SacolaDAO;
import java.awt.event.ActionListener;
import visao.sacola.FormGerenciarSacolas;

public class SacolaControle {

    //Atributos
    private static final ProdutoControle controleProduto = new ProdutoControle();
    private static final ConsultoraControle controleConsultora = new ConsultoraControle();
    private static final SacolaDAO daoSacola = new SacolaDAO();
    private static final FormGerenciarSacolas visaoGerenciarSacolas = new FormGerenciarSacolas();
    private ActionListener actionListener;

    //Métodos
    public void renderizarVisaoGerenciarSacolas() {
        visaoGerenciarSacolas.setVisible(true);
    }
}
