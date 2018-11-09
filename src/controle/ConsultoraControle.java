package controle;

import dao.ConsultoraDAO;
import java.util.List;
import java.util.Objects;
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
            Consultora consultora = consultoraDAO.buscarPorId(id);
            consultora.setStatusAtividade(false);
            consultoraDAO.alterar(consultora);
            fecharTela();
        }
    }

    public void excluirFisicamente(Integer id) {
        Consultora consultora = consultoraDAO.buscarPorId(id);
        consultoraDAO.remover(consultora);
    }

    public Consultora salvar(Consultora consultora) {
        String erro = validarConsultora(consultora);
        if (isNull(erro)) {
            fecharTela();
            if (isNull(consultora.getId())) {
                return consultoraDAO.inserir(consultora);
            } else {
                return consultoraDAO.alterar(consultora);
            }
        } else {
            JError.alert(erro, "Erro validação");
        }
        return null;
    }

    public void buscarPorNome(String stringBusca) {
        List<Consultora> consultoras = consultoraDAO.buscarTodos()
                .stream()
                .filter(consultora -> consultora.getNome().contains(stringBusca))
                .collect(Collectors.toList());
        preencheTabela(consultoras);
    }

    public Consultora buscaPorCpf(String cpf) {
        List<Consultora> consultoras = consultoraDAO.buscarTodos();
        for (Consultora consultora : consultoras) {
            if (consultora.getCpf().equals(cpf)) {
                return consultora;
            }
        }
        return null;
    }

    public String validarConsultora(Consultora consultora) {
        String erroMsg = null;
        if (isNull(consultora.getNome()) || consultora.getNome().length() < 3) {
            return "O Nome deve possuir no mínimo 3 caracteres";
        }
        if (isNull(consultora.getNome()) || consultora.getNome().length() > 50) {
            return "O Nome deve possuir no máximo 50 caracteres";
        }
        if (isNull(consultora.getCpf()) || !validaCPF(consultora.getCpf())) {
            return "CPF Inválido";
        }
        if (isNull(consultora.getDataNascimento())) {
            return "Não foi possível converter data de nascimento, favor informar dd/mm/yyyy";
        }
        return null;
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
                adicionaPontuacaoCPF(consultora.getCpf()),
                consultora.getStatusAtividade() ? "Ativo" : "Inativo"
            });
        });
    }

    private void fecharTela() {
        atualizaTabela();
        formGerenciarConsultora.setVisible(false);
    }

    public ConsultoraDAO getConsultoraDAO() {
        return consultoraDAO;
    }

}
