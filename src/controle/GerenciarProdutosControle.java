package controle;

import conexao.ConexaoBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Produto;
import modelo.dao.ProdutoDAO;
import visao.FormCadastrarProduto;
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
        evtBotaoCadastrar();
        evtBotaoAtualizar();
    }

    //Métodos
    private void preencheTabela() {        
        DefaultTableModel modelo = (DefaultTableModel) visaoGerenciarProdutos.getTblProdutos().getModel();
        modelo.setNumRows(0);
        
        ConexaoBanco.conectar();
        for (Produto produto : daoProduto.buscarTodos()) {
            modelo.addRow(new Object[]{
                produto.getId(),
                produto.getDescricao(),
                produto.getValor()
            });
        }
        ConexaoBanco.desconectar();
    }
    
    private void evtBotaoCadastrar(){
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
    
    private void evtBotaoAtualizar(){
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                preencheTabela();
            }
        };
        visaoGerenciarProdutos.getBtnAtualizar().addActionListener(actionListener);
    }

}
