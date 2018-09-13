package controle;

import dao.ConsultoraDAO;
import java.util.List;
import static java.util.Objects.isNull;
import javax.swing.table.DefaultTableModel;
import modelo.Consultora;
import modelo.Produto;
import util.JError;
import visao.consultora.FormGerenciarConsultora;
import visao.consultora.FormListarConsultora;

public class ConsultoraControle {

    private static final ConsultoraDAO consultoraDAO = new ConsultoraDAO();
    private static final FormListarConsultora formListarConsultora = new FormListarConsultora();
    private static final FormGerenciarConsultora formGerenciarConsultora = new FormGerenciarConsultora();

    public void renderizarVisao() {
        List<Consultora> consultoras = consultoraDAO.buscarTodos();
        preencheTabela(consultoras);
        formListarConsultora.setVisible(true);
    }

    public void renderizarVisaoCadastro() {
        formGerenciarConsultora.acaoCadastrar();
        formGerenciarConsultora.setVisible(true);
    }

    public void renderizarVisaoAlterar(Integer id) {
        Consultora consultora = consultoraDAO.buscarPorId(id);
        formGerenciarConsultora.acaoAlterar(consultora);
        formGerenciarConsultora.setVisible(true);
    }

    public void salvar(Consultora consultora) {
        if (isNull(consultora.getDataNascimento())) {
            JError.alert("Não foi possível converter data de nascimento, favor informar dd/mm/yyyy", "Erro validação");
        } else {
            if (isNull(consultora.getId())) {
                consultoraDAO.inserir(consultora);
            } else {
                consultoraDAO.alterar(consultora);
            }
        }
    }

    private void preencheTabela(List<Consultora> lista) {
        DefaultTableModel modelo = (DefaultTableModel) formListarConsultora.getTblConsultora().getModel();
        modelo.setNumRows(0);
        lista.stream().forEach(consultora -> {
            modelo.addRow(new Object[]{
                consultora.getId(),
                consultora.getNome(),
                consultora.getCpf()
            });
        });
    }

}
