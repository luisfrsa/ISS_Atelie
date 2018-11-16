package controle;

import dao.NotificacaoDAO;
import modelo.Notificacao;
import util.JError;
import visao.notificacao.FormGerenciarNotificacao;
import visao.notificacao.FormListarNotificacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.stream.Collectors;
import modelo.enums.Prioridade;
import static util.Datas.dateToString;

public class NotificacaoControle {

    private static final NotificacaoDAO notificacaoDAO = new NotificacaoDAO();
    private static final FormListarNotificacao formListarNotificacao = new FormListarNotificacao();
    private static final FormGerenciarNotificacao formGerenciarNotificacao = new FormGerenciarNotificacao();

    public void renderizarVisao() {
        atualizaTabela();
        formListarNotificacao.setVisible(true);
    }

    public void renderizarVisaoCadastro() {
        formGerenciarNotificacao.acaoCadastrar();
        formGerenciarNotificacao.setVisible(true);
    }

    public void renderizarVisaoAlterar(Integer id) {
        Notificacao notificacao = notificacaoDAO.buscarPorId(id);
        formGerenciarNotificacao.acaoAlterar(notificacao);
        formGerenciarNotificacao.setVisible(true);
    }

    public void fechar() {
        fecharTela();
    }

    public void excluir(Integer id) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Você realmente deseja excluir este registro", "Atenção", dialogButton);
        if (dialogResult == 0) {
            Notificacao notificacao = notificacaoDAO.buscarPorId(id);
            notificacao.setStatus(false);
            notificacaoDAO.alterar(notificacao);
            fecharTela();
        }

    }

    public void excluirFisicamente(Integer id) {
        Notificacao notificacao = notificacaoDAO.buscarPorId(id);
        notificacaoDAO.remover(notificacao);
    }


    public Notificacao salvar(Notificacao notificacao) {
        Notificacao novaNotificacao = null;

        String erro = validarNotificacao(notificacao);
        if (isNull(erro)) {
            fecharTela();
            if (isNull(notificacao.getId())) {
                novaNotificacao = notificacaoDAO.inserir(notificacao);
            } else {
                novaNotificacao = notificacaoDAO.alterar(notificacao);
            }
            atualizaTabela();
            return novaNotificacao;
        } else {
            JError.alert(erro, "Erro validação");
        }
        return null;
    }

    public void buscarPorNome(String stringBusca) {
        List<Notificacao> notificacaos = notificacaoDAO.buscarTodos()
                .stream()
                .filter(notificacao -> notificacao.getNome().contains(stringBusca))
                .collect(Collectors.toList());
        preencheTabela(notificacaos);
    }

    public String validarNotificacao(Notificacao notificacao) {
        String erroMsg = null;
        if (isNull(notificacao.getNome()) || notificacao.getNome().length() < 3) {
            erroMsg = "O Nome deve possuir no mínimo 3 caracteres";
        }
        if (isNull(notificacao.getNome()) || notificacao.getNome().length() > 50) {
            erroMsg = "O Nome deve possuir no máximo 50 caracteres";
        }
        if (isNull(notificacao.getDescricao()) || notificacao.getDescricao().length() < 3) {
            erroMsg = "A descrição deve possuir no mínimo 3 caracteres";
        }
        if (isNull(notificacao.getDescricao()) || notificacao.getDescricao().length() > 300) {
            erroMsg = "A descrição deve possuir no máximo 300 caracteres";
        }
        return erroMsg;
    }

    private void atualizaTabela() {
        List<Notificacao> notificacaos = notificacaoDAO.buscarTodos();
        preencheTabela(notificacaos);
    }

    private void preencheTabela(List<Notificacao> lista) {
        DefaultTableModel modelo = (DefaultTableModel) formListarNotificacao.getTblNotificacao().getModel();
        modelo.setNumRows(0);
        lista.stream().forEach(notificacao -> {
            modelo.addRow(new Object[]{
                notificacao.getId(),
                notificacao.getNome(),
                dateToString(notificacao.getData()),
                getPrioridade(notificacao.getPrioridade()).getNome(),
                notificacao.getStatus() ? "Ativo" : "Inativo"
            });
        });
    }

    public Prioridade getPrioridade(Integer index) {
        switch (index) {
            case 0:
                return Prioridade.BAIXA;
            case 1:
                return Prioridade.MEDIA;
            case 2:
                return Prioridade.ALTA;
        }
        return null;
    }

    private void fecharTela() {
        atualizaTabela();
        formGerenciarNotificacao.setVisible(false);
    }

    public NotificacaoDAO getNotificacaoDAO() {
        return notificacaoDAO;
    }

}
