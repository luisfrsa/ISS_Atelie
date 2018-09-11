package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Produto;
import dao.ProdutoDAO;
import javax.swing.JOptionPane;
import modelo.builder.ProdutoBuilder;
import visao.produto.FormCadastrarProduto;
import visao.produto.FormEditarProduto;
import visao.produto.FormGerenciarProdutos;

public class ProdutoControle {

    //Atributos
    private static final ProdutoDAO daoProduto = new ProdutoDAO();
    private static final FormGerenciarProdutos visaoGerenciarProdutos = new FormGerenciarProdutos();
    private static final FormCadastrarProduto visaoCadastrarProduto = new FormCadastrarProduto();
    private static final FormEditarProduto visaoEditarProduto = new FormEditarProduto();
    private ActionListener actionListener;

    //Métodos
    public void renderizarVisaoGerenciarProdutos() {
        preencheTabela();
        evtBotaoCadastrar();
        evtBotaoDetalhes();
        evtBotaoExcluir();
        visaoGerenciarProdutos.setVisible(true);
    }

    public void renderizarVisaoCadastrarProduto() {
        evtBotaoCadastrarNovo();
        evtBotaoCancelar();
        visaoCadastrarProduto.setVisible(true);
    }

    public void renderizarVisaoEditarProduto() {
        evtBotaoSalvar();
        evtBotaoCancelarEdicao();
        visaoEditarProduto.setVisible(true);
    }

    //----- TELA GERENCIAR PRODUTOS -----
    
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

    private void evtBotaoDetalhes() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoGerenciarProdutos.getTblProdutos().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum Prodruto selecionado!", "Erro", 0);
                } else {
                    Integer id = (Integer) visaoGerenciarProdutos.getTblProdutos().getValueAt(linha, 0); //ID do item selecionado
                    Produto produtoSelecionado = daoProduto.buscarPorId(id);
                    //Preenche os campos do form editar
                    visaoEditarProduto.getTxtId().setText(produtoSelecionado.getId().toString());
                    visaoEditarProduto.getTxtDescricao().setText(produtoSelecionado.getDescricao());
                    visaoEditarProduto.getTxtValor().setText(produtoSelecionado.getValor().toString());
                    visaoEditarProduto.getTxtCor().setText(produtoSelecionado.getCor());
                    visaoEditarProduto.getTxtTamanho().setText(produtoSelecionado.getTamanho());
                    visaoEditarProduto.getTxtMarca().setText(produtoSelecionado.getMarca());
                    visaoEditarProduto.getTxtModelo().setText(produtoSelecionado.getModelo());
                    renderizarVisaoEditarProduto();
                }

            }
        };
        visaoGerenciarProdutos.getBtnDetalhes().addActionListener(actionListener);
    }

    private void evtBotaoExcluir() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoGerenciarProdutos.getTblProdutos().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum Prodruto selecionado!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do produto "
                            + visaoGerenciarProdutos.getTblProdutos().getValueAt(linha, 1)
                            + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoGerenciarProdutos.getTblProdutos().getValueAt(linha, 0); //ID do item selecionado
                        daoProduto.remover(id);
                        JOptionPane.showMessageDialog(null, "Produto Excluido com Sucesso!", "Sucesso", 1);
                        preencheTabela(); //Atualiza tabela após remoção
                    }
                }
            }
        };
        visaoGerenciarProdutos.getBtnExcluir().addActionListener(actionListener);
    }

    //----- TELA CADASTRAR PRODUTO -----
    
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
                Double valor = Double.parseDouble(visaoCadastrarProduto.getTxtValor().getText());
                String cor = visaoCadastrarProduto.getTxtCor().getText();
                String tamanho = visaoCadastrarProduto.getTxtTamanho().getText();
                String marca = visaoCadastrarProduto.getTxtMarca().getText();
                String modleo = visaoCadastrarProduto.getTxtModelo().getText();

                //Criando objeto com as informações
                Produto produto = new ProdutoBuilder(descricao)
                        .setValor(valor)
                        .setCor(cor)
                        .setTamanho(tamanho)
                        .setMarca(marca)
                        .setModelo(modleo)
                        .build();

                //Persistindo objeto
                //Implementar validação do cadastro
                daoProduto.inserir(produto);
                JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!", "Sucesso", 1);
                visaoCadastrarProduto.dispose();
                preencheTabela(); //Atualiza tabela após cadastro
                limparCamposCadastro();
            }
        };
        visaoCadastrarProduto.getBtnCadastrar().addActionListener(actionListener);
    }

    private void limparCamposCadastro() {
        visaoCadastrarProduto.getTxtDescricao().setText("");
        visaoCadastrarProduto.getTxtValor().setText("");
        visaoCadastrarProduto.getTxtCor().setText("");
        visaoCadastrarProduto.getTxtTamanho().setText("");
        visaoCadastrarProduto.getTxtMarca().setText("");
        visaoCadastrarProduto.getTxtModelo().setText("");
    }

    //----- TELA EDITAR PRODUTOS -----
    
    private void evtBotaoSalvar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Obtendo os dados inseridos na visão
                Integer id = Integer.parseInt(visaoEditarProduto.getTxtId().getText());
                String descricao = visaoEditarProduto.getTxtDescricao().getText();
                Double valor = Double.parseDouble(visaoEditarProduto.getTxtValor().getText());
                String cor = visaoEditarProduto.getTxtCor().getText();
                String tamanho = visaoEditarProduto.getTxtTamanho().getText();
                String marca = visaoEditarProduto.getTxtMarca().getText();
                String modleo = visaoEditarProduto.getTxtModelo().getText();

                //Criando objeto com as informações
                Produto novoProduto = daoProduto.buscarPorId(id);
                novoProduto.setDescricao(descricao);
                novoProduto.setValor(valor);
                novoProduto.setCor(cor);
                novoProduto.setTamanho(tamanho);
                novoProduto.setMarca(marca);
                novoProduto.setModelo(modleo);

                //Persistindo objeto alterado
                //Implementar validação da edição
                daoProduto.alterar(novoProduto);
                JOptionPane.showMessageDialog(null, "Produto Editado com Sucesso!", "Sucesso", 1);
                visaoEditarProduto.dispose();
                preencheTabela();

            }
        };
        visaoEditarProduto.getBtnSalvar().addActionListener(actionListener);
    }

    private void evtBotaoCancelarEdicao() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar a edição? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoEditarProduto.dispose();
                }
            }

        };
        visaoEditarProduto.getBtnCancelar().addActionListener(actionListener);
    }

}
