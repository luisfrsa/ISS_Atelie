package controle;

import dao.ConsultoraDAO;
import dao.ProdutoDAO;
import dao.SacolaDAO;
import java.awt.event.ActionListener;
import visao.sacola.FormGerenciarSacolas;

public class SacolaControle {

    //Atributos
    private static final ProdutoDAO daoProduto = new ProdutoDAO();
    private static final ConsultoraDAO daoConsultora = new ConsultoraDAO();
    private static final SacolaDAO daoSacola = new SacolaDAO();
    private static final FormGerenciarSacolas visaoGerenciarSacolas = new FormGerenciarSacolas();
    private ActionListener actionListener;

    //Métodos
    public void renderizarVisaoGerenciarSacolas() {
        visaoGerenciarSacolas.setVisible(true);
    }
}
