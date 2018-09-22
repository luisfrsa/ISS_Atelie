package controle;

import dao.ItemSacolaDAO;
import dao.SacolaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Consultora;
import modelo.ItemSacola;
import modelo.Produto;
import modelo.Sacola;
import util.Datas;
import visao.sacola.FormAssociarProdutoSacola;
import visao.sacola.FormCriarSacola;
import visao.sacola.FormGerenciarSacolas;

public class SacolaControle {

    //Atributos
    private static final ProdutoControle controleProduto = new ProdutoControle();
    private static final ConsultoraControle controleConsultora = new ConsultoraControle();
    private static final SacolaDAO daoSacola = new SacolaDAO();
    private static final ItemSacolaDAO daoItemSacola = new ItemSacolaDAO();
    private static final FormGerenciarSacolas visaoGerenciarSacolas = new FormGerenciarSacolas();
    private static final FormCriarSacola visaoCriarSacola = new FormCriarSacola();
    private static final FormAssociarProdutoSacola visaoAssociarProduto = new FormAssociarProdutoSacola();
    private Sacola sacola = new Sacola();
    private ActionListener actionListener;

    private boolean ouvirEventosGerenciar = true;
    private boolean ouvirEventosCriar = true;
    private boolean ouvirEventosAssociarProduto = true;

    //Métodos
    public void renderizarVisaoGerenciarSacolas() {
        if (ouvirEventosGerenciar) {
            evtBotaoCriarNova();            
            ouvirEventosGerenciar = false;
        }
        preencheTabelaSacolas(daoSacola.buscarTodas(), visaoGerenciarSacolas.getTblSacolas());
        visaoGerenciarSacolas.setVisible(true);
    }

    public void renderizarVisaoCriarSacola() {
        if (ouvirEventosCriar) {
            evtBotaoAssociarConsultora();
            evtBotaoAdicionarProduto();            
            evtBotaoCriarSacola();
            ouvirEventosCriar = false;
        }        
        restauraFormCriarSacola();
        sacola = new Sacola();
        preencheTabelaItensSacola(sacola.getListaItens(), visaoCriarSacola.getTblItensDeSacola());
        visaoCriarSacola.setVisible(true);
    }

    public void renderizarVisaoAssociarProduto() {
        if (ouvirEventosAssociarProduto) {
            evtBotaoBuscar();
            evtBotaoConfirmar();
            ouvirEventosAssociarProduto = false;
        }
        restauraFormAssociarProduto();
        controleProduto.preencheTabelaProdutos(controleProduto.getDaoProduto().buscarTodos(), visaoAssociarProduto.getTblProdutos());
        visaoAssociarProduto.setVisible(true);
    }

    //----- TELA GERENCIAR SACOLAS -----
    public void preencheTabelaSacolas(List<Sacola> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (Sacola sacola : lista) {
            modelo.addRow(new Object[]{
                sacola.getId(),
                sacola.getConsultora().getNome(),
                Datas.formatoData.format(sacola.getDataCriacao()),
                Datas.formatoData.format(sacola.getDataAcerto())
            });
        }
    }
    
    private void evtBotaoCriarNova() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoCriarSacola();
            }
        };
        visaoGerenciarSacolas.getBtnNova().addActionListener(actionListener);
    }

    //----- TELA CRIAR SACOLA -----
    private void restauraFormCriarSacola() {
        visaoCriarSacola.getLblNomeConsultora().setText("Nenhuma Consultora associada.");
        visaoCriarSacola.getTxtCpf().setText("");
        visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(255, 255, 255));
        visaoCriarSacola.getTxtCpf().setEditable(true);
        visaoCriarSacola.getBtnAssociarConsultora().setEnabled(true);
    }

    public void preencheTabelaItensSacola(List<ItemSacola> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (ItemSacola item : lista) {
            modelo.addRow(new Object[]{
                item.getProduto().getDescricao(),
                item.getQuantidade()
            });
        }
    }

    private void evtBotaoAssociarConsultora() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String cpfConsultora = visaoCriarSacola.getTxtCpf().getText();
                Consultora consultora = controleConsultora.buscaPorCpf(cpfConsultora);
                if (validaAssocairConsultora(consultora)) {
                    sacola.setConsultora(consultora);
                    visaoCriarSacola.getLblNomeConsultora().setText(consultora.getNome());
                    visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(204, 255, 204));
                    visaoCriarSacola.getTxtCpf().setEditable(false);
                    visaoCriarSacola.getBtnAssociarConsultora().setEnabled(false);
                }
            }
        };
        visaoCriarSacola.getBtnAssociarConsultora().addActionListener(actionListener);
    }

    private boolean validaAssocairConsultora(Consultora consultora) {
        if (consultora == null) {
            JOptionPane.showMessageDialog(null, "Nenhuma consultora cadastrada com o CPF informado.", "Erro na Validação", 0);
            visaoCriarSacola.getTxtCpf().requestFocus();
            visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }
        return true;
    }

    private void evtBotaoAdicionarProduto() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoAssociarProduto();
            }
        };
        visaoCriarSacola.getBtnAdicionarProduto().addActionListener(actionListener);
    }
    
    private void evtBotaoCriarSacola(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //Implementar validação
                String dataString = visaoCriarSacola.getTxtDataAcerto().getText();
                Date dataAcerto = null;
                try {
                    dataAcerto = Datas.formatoData.parse(dataString);
                } catch (Exception e) {
                    System.out.println("Erro na conversão (String para Date) formato digitado é incorreto.");
                }
                
                Calendar calendario = Calendar.getInstance();
                Date dataCricao = calendario.getTime();
                
                sacola.setDataCriacao(dataCricao);
                sacola.setDataAcerto(dataAcerto);                
                daoSacola.inserir(sacola);
                preencheTabelaSacolas(daoSacola.buscarTodas(), visaoGerenciarSacolas.getTblSacolas());
                visaoCriarSacola.dispose();
            }
        };
        visaoCriarSacola.getBtnCriarSacola().addActionListener(actionListener);
    }

    //----- TELA ASSOCIAR PRODUTO -----
    private void restauraFormAssociarProduto() {
        visaoAssociarProduto.getTxtQuantidade().setText("");
        visaoAssociarProduto.getTxtQuantidade().setBackground(new java.awt.Color(255, 255, 255));
        visaoAssociarProduto.getTxtDescricao().setText("");
    }

    private void evtBotaoBuscar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String busca = visaoAssociarProduto.getTxtDescricao().getText();
                if (busca.equals("")) {
                    controleProduto.preencheTabelaProdutos(controleProduto.getDaoProduto().buscarTodos(), visaoAssociarProduto.getTblProdutos());
                } else {
                    List<Produto> listaDeBusca = controleProduto.buscaPorDescricao(busca);
                    controleProduto.preencheTabelaProdutos(listaDeBusca, visaoAssociarProduto.getTblProdutos());
                }
            }
        };
        visaoAssociarProduto.getBtnBuscar().addActionListener(actionListener);
    }

    private void evtBotaoConfirmar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoAssociarProduto.getTblProdutos().getSelectedRow();
                if (validaFormAssociarProduto(linha)) {
                    Integer id = (Integer) visaoAssociarProduto.getTblProdutos().getValueAt(linha, 0);
                    Produto produto = controleProduto.getDaoProduto().buscarPorId(id);
                    Integer quantidade = Integer.parseInt(visaoAssociarProduto.getTxtQuantidade().getText());
                    ItemSacola itemSacola = new ItemSacola();
                    itemSacola.setProduto(produto);
                    itemSacola.setQuantidade(quantidade);
                    sacola.getListaItens().add(itemSacola);
                    preencheTabelaItensSacola(sacola.getListaItens(), visaoCriarSacola.getTblItensDeSacola());
                    visaoAssociarProduto.dispose();
                }

            }
        };
        visaoAssociarProduto.getBtnConfirmar().addActionListener(actionListener);
    }

    private boolean validaFormAssociarProduto(int linha) {
        if (linha < 0){
            JOptionPane.showMessageDialog(null, "Nenhum produto selecionado na tabela.", "Erro", 0);
            return false;
        }
        //Implementar demais validações
        return true;
    }

}
