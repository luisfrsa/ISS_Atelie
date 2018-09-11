package controle;

import dao.ConsultoraDAO;
import visao.consultora.FormGerenciarConsultora;

public class ConsultoraControle {

    private static final ConsultoraDAO ConsultoraDAO = new ConsultoraDAO();
    private static final FormGerenciarConsultora formGerenciarConsultora = new FormGerenciarConsultora();


    public void renderizarVisao() {
        formGerenciarConsultora.setVisible(true);
    }

}
