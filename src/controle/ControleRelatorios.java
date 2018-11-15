package controle;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Consultora;
import modelo.Sacola;
import util.Datas;
import visao.relatorios.FormExibirRelFrequencia;
import visao.relatorios.FormRelFrequencia;

/**
 *
 * @author William Rodrigues
 */
public class ControleRelatorios {

    private static final SacolaControle controleSacola = new SacolaControle();
    private static final ConsultoraControle controleConsultora = new ConsultoraControle();
    private static final FormRelFrequencia visaoRelatorioFrequencia = new FormRelFrequencia();
    private static final FormExibirRelFrequencia visaoExibirRelFrequencia = new FormExibirRelFrequencia();

    public void renderizaVisaoRelatorioFrequencia() {
        visaoRelatorioFrequencia.getTxtCpf().setText("");
        visaoRelatorioFrequencia.setVisible(true);
    }

    //---------- TELA RELATORIO DE FREQUENCIA ----------
    public boolean verificaCpfDigitado(String cpf) {

        for (Consultora consultora : controleConsultora.getConsultoraDAO().buscarTodos()) {
            if (consultora.getCpf().equals(cpf)) {
                return true;
            }
        }
        JOptionPane.showMessageDialog(null, "Nenhuma Consultora cadastrada com o CPF informado!", "Erro", 0);
        visaoRelatorioFrequencia.getTxtCpf().requestFocus();
        return false;
    }

    //---------- TELA EXIBIR RELATORIO DE FREQUENCIA ----------
    private void preencheTabelaAtividades(Consultora consultora) {

        DefaultTableModel modelo = (DefaultTableModel) visaoExibirRelFrequencia.getTblAtividades().getModel();
        modelo.setNumRows(0);

        for (Sacola sacola : controleSacola.getDaoSacola().buscarTodas()) {
            if (sacola.getConsultora().equals(consultora)) {

                modelo.addRow(new Object[]{
                    Datas.formatoData.format(sacola.getDataCriacao()),
                    "Criação de uma Sacola",});

                if (sacola.isFinalizada()) {
                    modelo.addRow(new Object[]{
                        Datas.formatoData.format(sacola.getDataFinalizada()),
                        "Acerto de uma Sacola",});
                }

            }
        }
    }

    public void gerarRelatorioFrequencia(String cpfConsultora) {
        Consultora consultora = controleConsultora.buscaPorCpf(cpfConsultora);
        preencheTabelaAtividades(consultora);
        visaoExibirRelFrequencia.getLblNome().setText(consultora.getNome());
        visaoExibirRelFrequencia.getLblCpf().setText(consultora.getCpf());
        visaoExibirRelFrequencia.setVisible(true);
    }

}
