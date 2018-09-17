package controle;

import dao.ConsultoraDAO;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Consultora;
import modelo.Produto;
import static util.Documentos.adicionaPontuacaoCPF;
import static util.Documentos.validaCPF;
import util.JError;
import visao.consultora.FormGerenciarConsultora;
import visao.consultora.FormListarConsultora;

public class ConsultoraControle {

    private static final ConsultoraDAO consultoraDAO = new ConsultoraDAO();
    private static final FormListarConsultora formListarConsultora = new FormListarConsultora();
    private static final FormGerenciarConsultora formGerenciarConsultora = new FormGerenciarConsultora();

    public void renderizarVisao() {
        atualizaTabela();
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

    public void fechar() {
        fecharTela();
    }

    public void excluir(Integer id) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir este registro", "Atenção", dialogButton);
        if (dialogResult == 0) {
            consultoraDAO.remover(id);
            fecharTela();
        }

    }

    public void salvar(Consultora consultora) {
        if (validarConsultora(consultora)) {
            if (isNull(consultora.getId())) {
                consultoraDAO.inserir(consultora);
            } else {
                consultoraDAO.alterar(consultora);
            }
            fecharTela();
        }
    }

    public void buscarPorNome(String stringBusca) {
        List<Consultora> consultoras = consultoraDAO.buscarTodos()
                .stream()
                .filter(consultora -> consultora.getNome().contains(stringBusca))
                .collect(Collectors.toList());
        preencheTabela(consultoras);
    }

    private Boolean validarConsultora(Consultora consultora) {
        Boolean erro = false;
        if (consultora.getNome().length() < 3) {
            erro = true;
            JError.alert("O Nome deve possuir no mínimo 3 caracteres", "Erro validação");
        }
        if (!validaCPF(consultora.getCpf())) {
            erro = true;
            JError.alert("CPF Inválido", "Erro validação");
        }
        if (isNull(consultora.getDataNascimento())) {
            erro = true;
            JError.alert("Não foi possível converter data de nascimento, favor informar dd/mm/yyyy", "Erro validação");
        }
        return !erro;
    }

    private void atualizaTabela() {
        List<Consultora> consultoras = consultoraDAO.buscarTodos();
        preencheTabela(consultoras);
    }

    private void preencheTabela(List<Consultora> lista) {
        DefaultTableModel modelo = (DefaultTableModel) formListarConsultora.getTblConsultora().getModel();
        modelo.setNumRows(0);
        lista.stream().forEach(consultora -> {
            modelo.addRow(new Object[]{
                consultora.getId(),
                consultora.getNome(),
                adicionaPontuacaoCPF(consultora.getCpf())
            });
        });
    }

    private void fecharTela() {
        atualizaTabela();
        formGerenciarConsultora.setVisible(false);
    }

}
