package controle;

import dao.ConsultoraDAO;
import static java.util.Objects.isNull;
import modelo.Consultora;
import visao.consultora.FormGerenciarConsultora;
import visao.consultora.FormListarConsultora;

public class ConsultoraControle {

    private static final ConsultoraDAO consultoraDAO = new ConsultoraDAO();
    private static final FormListarConsultora formListarConsultora = new FormListarConsultora();
    private static final FormGerenciarConsultora formGerenciarConsultora = new FormGerenciarConsultora();

    public void renderizarVisao() {
        formListarConsultora.setVisible(true);
    }

    public void renderizarVisaoCadastro() {
        formGerenciarConsultora.acaoCadastrar();
        formGerenciarConsultora.setVisible(true);
    }

    public void salvar(Consultora consultora) {
        if (isNull(consultora.getDataNascimento())) {
            throw new RuntimeException("Não foi possível converter data de nascimento, favor informar dd/mm/yyyy");
        }
        if (isNull(consultora.getId())) {
            consultoraDAO.inserir(consultora);
        } else {
            consultoraDAO.alterar(consultora);
        }
    }

}
