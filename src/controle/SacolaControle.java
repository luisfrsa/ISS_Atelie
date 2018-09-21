package controle;

import dao.SacolaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Consultora;
import visao.sacola.FormCriarSacola;
import visao.sacola.FormGerenciarSacolas;

public class SacolaControle {

    //Atributos
    private static final ProdutoControle controleProduto = new ProdutoControle();
    private static final ConsultoraControle controleConsultora = new ConsultoraControle();
    private static final SacolaDAO daoSacola = new SacolaDAO();
    private static final FormGerenciarSacolas visaoGerenciarSacolas = new FormGerenciarSacolas();
    private static final FormCriarSacola visaoCriarSacola = new FormCriarSacola();
    private ActionListener actionListener;

    //Métodos
    public void renderizarVisaoGerenciarSacolas() {
        evtBotaoCriar();
        visaoGerenciarSacolas.setVisible(true);
    }

    public void renderizarVisaoCriarSacola() {
        evtBotaoAssociarConsultora();
        restauraFormCriarSacola();
        visaoCriarSacola.setVisible(true);
    }

    private void restauraFormCriarSacola() {
        visaoCriarSacola.getLblNomeConsultora().setText("Nenhuma Consultora associada.");
        visaoCriarSacola.getTxtCpf().setText("");
        visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(255, 255, 255));
        visaoCriarSacola.getTxtCpf().setEditable(true);
    }

    //----- TELA GERENCIAR SACOLAS -----
    private void evtBotaoCriar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoCriarSacola();
            }
        };
        visaoGerenciarSacolas.getBtnNova().addActionListener(actionListener);
    }

    //----- TELA CRIAR SACOLA -----
    private void evtBotaoAssociarConsultora() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String cpfConsultora = visaoCriarSacola.getTxtCpf().getText();
                Consultora consultora = controleConsultora.buscaPorCpf(cpfConsultora);
                if (validaAssocairConsultora(consultora)) {
                    visaoCriarSacola.getLblNomeConsultora().setText(consultora.getNome());
                    visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(204, 255, 204));
                    visaoCriarSacola.getTxtCpf().setEditable(false);
                }
            }
        };
        visaoCriarSacola.getBtnAssociarConsultora().addActionListener(actionListener);
    }

    private boolean validaAssocairConsultora(Consultora consultora) {
        if(consultora == null){
            JOptionPane.showMessageDialog(null, "Nenhuma consultora cadastrada com o CPF informado.", "Erro na Validação", 0);
            visaoCriarSacola.getTxtCpf().requestFocus();
            visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }
        return true;
    }

}
