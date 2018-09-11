package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Produto;
import dao.ProdutoDAO;
import javax.swing.JOptionPane;
import visao.produto.FormCadastrarProduto;
import visao.produto.FormGerenciarProdutos;

public class ProdutoControle {

    //Atributos
    private static final ProdutoDAO daoProduto = new ProdutoDAO();
    private static final FormGerenciarProdutos visaoGerenciarProdutos = new FormGerenciarProdutos();
    private static final FormCadastrarProduto visaoCadastrarProduto = new FormCadastrarProduto();
    private ActionListener actionListener;

    //Métodos
    public void renderizarVisaoGerenciarProdutos() {
        preencheTabela();
        evtBotaoCadastrar();
        evtBotaoAtualizar();
        visaoGerenciarProdutos.setVisible(true);
    }
    
    public void renderizarVisaoCadastrarProduto() {
        evtBotaoCadastrarNovo();
        evtBotaoCancelar();
        visaoCadastrarProduto.setVisible(true);
    }
    
    private void preencheTabela() {
        DefaultTableModel modelo = (DefaultTableModel) visaoGerenciarProdutos.getTblProdutos().getModel();
        modelo.setNumRows(0);
        for (Produto produto : daoProduto.buscarTodos()) {
            modelo.addRow(new Object[]{
                    produto.getId(),
                    produto.getDescricao(),
                    produto.getValor()
            });
        }
    }

    private void evtBotaoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoCadastrarProduto();
            }
        };
        visaoGerenciarProdutos.getBtnCadastrar().addActionListener(actionListener);
    }

    private void evtBotaoAtualizar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                preencheTabela();
            }
        };
        visaoGerenciarProdutos.getBtnAtualizar().addActionListener(actionListener);
    }
    
    private void evtBotaoCancelar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (todosCamposVazios()) {
                    visaoCadastrarProduto.dispose();
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        visaoCadastrarProduto.dispose();
                    }
                }

            }
        };
        visaoCadastrarProduto.getBtnCancelar().addActionListener(actionListener);
    }

    private boolean todosCamposVazios() {
        return visaoCadastrarProduto.getTxtDescricao().getText().equals("")
                && visaoCadastrarProduto.getTxtValor().getText().equals("")
                && visaoCadastrarProduto.getTxtCor().getText().equals("")
                && visaoCadastrarProduto.getTxtTamanho().getText().equals("")
                && visaoCadastrarProduto.getTxtMarca().getText().equals("")
                && visaoCadastrarProduto.getTxtModelo().getText().equals("");
    }

    private void evtBotaoCadastrarNovo() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Obtendo os dados inseridos na visão
                String descricao = visaoCadastrarProduto.getTxtDescricao().getText();
                String valor = visaoCadastrarProduto.getTxtValor().getText();
                String cor = visaoCadastrarProduto.getTxtCor().getText();
                String tamanho = visaoCadastrarProduto.getTxtTamanho().getText();
                String marca = visaoCadastrarProduto.getTxtMarca().getText();
                String modleo = visaoCadastrarProduto.getTxtModelo().getText();

                //Criando objeto com as informações
                Produto produto = new Produto();
                produto.setDescricao(descricao);
                produto.setValor(Double.parseDouble(valor));
                produto.setCor(cor);
                produto.setTamanho(tamanho);
                produto.setMarca(marca);
                produto.setModelo(modleo);

                //Persistindo objeto
                //Implementar validação do cadastro
                daoProduto.inserir(produto);

                JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!", "Sucesso", 1);
                visaoCadastrarProduto.dispose();
            }
        };
        visaoCadastrarProduto.getBtnCadastrar().addActionListener(actionListener);
    }

}
