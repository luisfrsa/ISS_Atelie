package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Produto;
import dao.ProdutoDAO;
import visao.produto.FormCadastrarProduto;
import visao.produto.FormGerenciarProdutos;

public class GerenciarProdutosControle {

    //Atributos
    private static final ProdutoDAO daoProduto = new ProdutoDAO();
    private static final FormGerenciarProdutos visaoGerenciarProdutos = new FormGerenciarProdutos();
    private ActionListener actionListener;

    //Construtor
    public GerenciarProdutosControle() {
    }

    public void renderizarVisao() {
        preencheTabela();
        evtBotaoCadastrar();
        evtBotaoAtualizar();
        visaoGerenciarProdutos.setVisible(true);

    }

    //Métodos
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
                FormCadastrarProduto visaoCadastrarProduto = new FormCadastrarProduto();
                CadastrarProdutoControle controleCadastrarProduto = new CadastrarProdutoControle(daoProduto, visaoCadastrarProduto);
                visaoCadastrarProduto.setVisible(true);
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

}
