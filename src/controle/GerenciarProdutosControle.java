package controle;

import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Produto;
import modelo.dao.ProdutoDAO;
import visao.FormGerenciarProdutos;

public class GerenciarProdutosControle {

    //Atributos
    private final ProdutoDAO daoProduto;
    private final FormGerenciarProdutos visaoGerenciarProdutos;
    private ActionListener actionListener;

    //Construtor
    public GerenciarProdutosControle(ProdutoDAO daoProduto, FormGerenciarProdutos visaoGerenciarProdutos) {
        this.daoProduto = daoProduto;
        this.visaoGerenciarProdutos = visaoGerenciarProdutos;
        //Métodos ouvindo eventos
        preencheTabela();
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

}
