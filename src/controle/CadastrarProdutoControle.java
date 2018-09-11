package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Produto;
import dao.ProdutoDAO;
import visao.produto.FormCadastrarProduto;

public class CadastrarProdutoControle {

    //Atributos
    private final ProdutoDAO daoProduto;
    private final FormCadastrarProduto visaoCadastrarProdutos;
    private ActionListener actionListener;

    //Construtor
    public CadastrarProdutoControle(ProdutoDAO daoProduto, FormCadastrarProduto visaoCadastrarProdutos) {
        this.daoProduto = daoProduto;
        this.visaoCadastrarProdutos = visaoCadastrarProdutos;
        //Métodos ouvindo eventos
        evtBotaoCancelar();
        evtBotaoCadastrar();
    }

    //Métodos
    private void evtBotaoCancelar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (todosCamposVazios()) {
                    visaoCadastrarProdutos.dispose();
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        visaoCadastrarProdutos.dispose();
                    }
                }

            }
        };
        visaoCadastrarProdutos.getBtnCancelar().addActionListener(actionListener);
    }

    private boolean todosCamposVazios() {
        return visaoCadastrarProdutos.getTxtDescricao().getText().equals("")
                && visaoCadastrarProdutos.getTxtValor().getText().equals("")
                && visaoCadastrarProdutos.getTxtCor().getText().equals("")
                && visaoCadastrarProdutos.getTxtTamanho().getText().equals("")
                && visaoCadastrarProdutos.getTxtMarca().getText().equals("")
                && visaoCadastrarProdutos.getTxtModelo().getText().equals("");
    }

    private void evtBotaoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Obtendo os dados inseridos na visão
                String descricao = visaoCadastrarProdutos.getTxtDescricao().getText();
                String valor = visaoCadastrarProdutos.getTxtValor().getText();
                String cor = visaoCadastrarProdutos.getTxtCor().getText();
                String tamanho = visaoCadastrarProdutos.getTxtTamanho().getText();
                String marca = visaoCadastrarProdutos.getTxtMarca().getText();
                String modleo = visaoCadastrarProdutos.getTxtModelo().getText();

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
//                ConexaoBanco.conectar();
                daoProduto.inserir(produto);
//                ConexaoBanco.desconectar();

                JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!", "Sucesso", 1);
                visaoCadastrarProdutos.dispose();
            }
        };
        visaoCadastrarProdutos.getBtnCadastrar().addActionListener(actionListener);
    }
}
