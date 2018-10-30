package controle;

import dao.NotificacaoDAO;
import modelo.Notificacao;
import util.JError;
import visao.notificacao.FormGerenciarNotificacao;
import visao.notificacao.FormListarNotificacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import modelo.enums.Prioridade;
import static util.Datas.dateToString;
import static util.Documentos.adicionaPontuacaoCPF;
import static util.Documentos.validaCPF;

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

    public void salvar(Notificacao notificacao) {
        if (validarNotificacao(notificacao)) {
            if (isNull(notificacao.getId())) {
                notificacaoDAO.inserir(notificacao);
            } else {
                notificacaoDAO.alterar(notificacao);
            }
            fecharTela();
        }
    }

    public void buscarPorNome(String stringBusca) {
        List<Notificacao> notificacaos = notificacaoDAO.buscarTodos()
                .stream()
                .filter(notificacao -> notificacao.getNome().contains(stringBusca))
                .collect(Collectors.toList());
        preencheTabela(notificacaos);
    }

    private Boolean validarNotificacao(Notificacao notificacao) {
        Boolean erro = false;
        if (notificacao.getNome().length() < 3) {
            erro = true;
            JError.alert("O Nome deve possuir no mínimo 3 caracteres", "Erro validação");
        }
        if (notificacao.getDescricao().length() < 3) {
            erro = true;
            JError.alert("A descrição deve possuir no mínimo 3 caracteres", "Erro validação");
        }
        return !erro;
    }

    private void atualizaTabela() {
        List<Notificacao> notificacaos = notificacaoDAO.buscarTodos();
        preencheTabela(notificacaos);
    }

    private void preencheTabela(List<Notificacao> lista) {
        System.out.println(lista);
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
