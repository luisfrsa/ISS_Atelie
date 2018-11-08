package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

import modelo.Produto;
import dao.ProdutoDAO;
import dao.ItemEstoqueDAO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.ItemEstoque;
import modelo.builder.ProdutoBuilder;
import visao.produto.FormCadastrarProduto;
import visao.produto.FormEditarEstoqueProduto;
import visao.produto.FormEditarProduto;
import visao.produto.FormEstoqueProdutos;
import visao.produto.FormGerenciarProdutos;

public class ProdutoControle {

    //Atributos
    private static final ProdutoDAO daoProduto = new ProdutoDAO();
    private static final ItemEstoqueDAO daoItemEstoque = new ItemEstoqueDAO();
    private static final FormGerenciarProdutos visaoGerenciarProdutos = new FormGerenciarProdutos();
    private static final FormCadastrarProduto visaoCadastrarProduto = new FormCadastrarProduto();
    private static final FormEditarProduto visaoEditarProduto = new FormEditarProduto();
    private static final FormEstoqueProdutos visaoEstoqueProdutos = new FormEstoqueProdutos();
    private static final FormEditarEstoqueProduto visaoEditarEstoque = new FormEditarEstoqueProduto();
    private ActionListener actionListener;

    private boolean ouvirEventosGerenciar = true;
    private boolean ouvirEventosCadastrar = true;
    private boolean ouvirEventosEditar = true;
    private boolean ouvirEventosEstoque = true;
    private boolean ouvirEventosEditarEstoque = true;

    //Métodos
    public void renderizarVisaoGerenciarProdutos() {

        if (ouvirEventosGerenciar) {
            evtBotaoCadastrar();
            evtBotaoDetalhes();
            evtBotaoExcluir();
            evtBotaoBuscar();
            evtBotaoEstoque();
            ouvirEventosGerenciar = false;
        }
        preencheTabelaProdutos(daoProduto.buscarTodos(), visaoGerenciarProdutos.getTblProdutos());
        visaoGerenciarProdutos.getTxtDescricao().setText("");
        visaoGerenciarProdutos.setVisible(true);
    }

    public void renderizarVisaoCadastrarProduto() {

        if (ouvirEventosCadastrar) {
            evtBotaoCadastrarNovo();
            evtBotaoCancelar();
            ouvirEventosCadastrar = false;
        }
        limparCamposCadastro();
        restauraCorCamposCadastro();
        visaoCadastrarProduto.setVisible(true);
    }

    public void renderizarVisaoEditarProduto() {

        if (ouvirEventosEditar) {
            evtBotaoSalvar();
            evtBotaoCancelarEdicao();
            ouvirEventosEditar = false;
        }
        visaoEditarProduto.setVisible(true);
    }

    public void renderizaVisaoEstoque() {
        if (ouvirEventosEstoque) {
            evtBotaoFecharEstoque();
            evtBotaoEditarEstoque();
            ouvirEventosEstoque = false;
        }
        preencheTabelaEstoque(daoItemEstoque.buscarTodos(), visaoEstoqueProdutos.getTblEstoqueProdutos());
        visaoEstoqueProdutos.setVisible(true);
    }

    public void renderizaVisaoEditarEstoque() {
        if (ouvirEventosEditarEstoque) {
            evtBotaoCancelarEditarEstoque();
            evtBotaoSalvarEditarEstoque();
            ouvirEventosEditarEstoque = false;
        }
        visaoEditarEstoque.setVisible(true);
    }

    //----- TELA GERENCIAR PRODUTOS -----
    public void preencheTabelaProdutos(List<Produto> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (Produto produto : lista) {
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
                        daoItemEstoque.remover(id); //Remove primeiro o item do estoque
                        daoProduto.remover(id);
                        JOptionPane.showMessageDialog(null, "Produto Excluido com Sucesso!", "Sucesso", 1);
                        preencheTabelaProdutos(daoProduto.buscarTodos(), visaoGerenciarProdutos.getTblProdutos()); //Atualiza tabela após remoção
                    }
                }
            }
        };
        visaoGerenciarProdutos.getBtnExcluir().addActionListener(actionListener);
    }

    private void evtBotaoBuscar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String busca = visaoGerenciarProdutos.getTxtDescricao().getText();
                if (busca.equals("")) {
                    preencheTabelaProdutos(daoProduto.buscarTodos(), visaoGerenciarProdutos.getTblProdutos());
                } else {
                    List<Produto> listaDeBusca = buscaPorDescricao(busca);
                    preencheTabelaProdutos(listaDeBusca, visaoGerenciarProdutos.getTblProdutos());
                }
            }
        };
        visaoGerenciarProdutos.getBtnBuscar().addActionListener(actionListener);

    }

    public List<Produto> buscaPorDescricao(String busca) {

        List<Produto> listaDeBusca = new ArrayList<>();
        int numLetras = busca.length();

        for (Produto produto : daoProduto.buscarTodos()) {
            if (numLetras <= produto.getDescricao().length()) {
                if (produto.getDescricao().substring(0, numLetras).equalsIgnoreCase(busca)) {
                    listaDeBusca.add(produto);
                }
            }
        }
        return listaDeBusca;
    }

    private void evtBotaoEstoque() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizaVisaoEstoque();
            }
        };
        visaoGerenciarProdutos.getBtnEstoque().addActionListener(actionListener);
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
                && visaoCadastrarProduto.getTxtModelo().getText().equals("")
                && visaoCadastrarProduto.getTxtQuantidade().getText().equals("");
    }

    private void evtBotaoCadastrarNovo() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Obtendo os dados inseridos na visão
                String descricao = visaoCadastrarProduto.getTxtDescricao().getText();

                Double valor;
                try {
                    valor = Double.parseDouble(visaoCadastrarProduto.getTxtValor().getText());
                } catch (NumberFormatException e) {
                    valor = null;
                }

                String cor = visaoCadastrarProduto.getTxtCor().getText();
                String tamanho = visaoCadastrarProduto.getTxtTamanho().getText();
                String marca = visaoCadastrarProduto.getTxtMarca().getText();
                String modleo = visaoCadastrarProduto.getTxtModelo().getText();

                Integer quantidade;
                try {
                    quantidade = Integer.parseInt(visaoCadastrarProduto.getTxtQuantidade().getText());
                } catch (NumberFormatException e) {
                    quantidade = null;
                }

                //Criando objeto com as informações
                Produto produto = new ProdutoBuilder(descricao)
                        .setValor(valor)
                        .setCor(cor)
                        .setTamanho(tamanho)
                        .setMarca(marca)
                        .setModelo(modleo)
                        .build();

                //Persistindo objeto
                if (validaCadastroProduto(produto) && validaQuantidadeEstoque(quantidade)) {
                    daoProduto.inserir(produto);

                    //Inserindo produto ao estoque
                    ItemEstoque itemEstoque = new ItemEstoque();
                    itemEstoque.setProduto(produto);
                    itemEstoque.setId(produto.getId());
                    itemEstoque.setQuantidade(quantidade);
                    daoItemEstoque.inserir(itemEstoque);

                    JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!", "Sucesso", 1);
                    visaoCadastrarProduto.dispose();
                    preencheTabelaProdutos(daoProduto.buscarTodos(), visaoGerenciarProdutos.getTblProdutos()); //Atualiza tabela após cadastro
                    limparCamposCadastro();
                }
            }
        };
        visaoCadastrarProduto.getBtnCadastrar().addActionListener(actionListener);
    }

    private boolean validaQuantidadeEstoque(Integer quantidade) {

        if (quantidade == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Quantidade' é obrigatório!"
                    + "\nPermitidos apenas números inteiros.", "Erro na Validação", 0);
            visaoCadastrarProduto.getTxtQuantidade().requestFocus();
            visaoCadastrarProduto.getTxtQuantidade().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (quantidade < 0) {
            JOptionPane.showMessageDialog(null, "O campo 'Quantidade' não aceita números negativos!", "Erro na Validação", 0);
            visaoCadastrarProduto.getTxtQuantidade().requestFocus();
            visaoCadastrarProduto.getTxtQuantidade().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        return true;
    }

    private boolean validaCadastroProduto(Produto produto) {

        restauraCorCamposCadastro();

        if (produto.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório!", "Erro na Validação", 0);
            visaoCadastrarProduto.getTxtDescricao().requestFocus();
            visaoCadastrarProduto.getTxtDescricao().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (produto.getValor() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor' é obrigatório!"
                    + "\nPermitidos apenas números inteiros ou reais.", "Erro na Validação", 0);
            visaoCadastrarProduto.getTxtValor().requestFocus();
            visaoCadastrarProduto.getTxtValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (produto.getValor() <= 0) {
            JOptionPane.showMessageDialog(null, "O Valor de um Produto deve ser maior que zero!", "Erro na Validação", 0);
            visaoCadastrarProduto.getTxtValor().requestFocus();
            visaoCadastrarProduto.getTxtValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        return true;
    }

    private void restauraCorCamposCadastro() {
        visaoCadastrarProduto.getTxtDescricao().setBackground(Color.white);
        visaoCadastrarProduto.getTxtValor().setBackground(Color.white);
        visaoCadastrarProduto.getTxtCor().setBackground(Color.white);
        visaoCadastrarProduto.getTxtTamanho().setBackground(Color.white);
        visaoCadastrarProduto.getTxtMarca().setBackground(Color.white);
        visaoCadastrarProduto.getTxtModelo().setBackground(Color.white);
        visaoCadastrarProduto.getTxtQuantidade().setBackground(Color.white);
    }

    private void limparCamposCadastro() {
        visaoCadastrarProduto.getTxtDescricao().setText("");
        visaoCadastrarProduto.getTxtValor().setText("");
        visaoCadastrarProduto.getTxtCor().setText("");
        visaoCadastrarProduto.getTxtTamanho().setText("");
        visaoCadastrarProduto.getTxtMarca().setText("");
        visaoCadastrarProduto.getTxtModelo().setText("");
        visaoCadastrarProduto.getTxtQuantidade().setText("");
    }

    //----- TELA EDITAR PRODUTOS -----
    private void evtBotaoSalvar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Obtendo os dados inseridos na visão
                Integer id = Integer.parseInt(visaoEditarProduto.getTxtId().getText());
                String descricao = visaoEditarProduto.getTxtDescricao().getText();

                Double valor;
                try {
                    valor = Double.parseDouble(visaoEditarProduto.getTxtValor().getText());
                } catch (Exception e) {
                    valor = null;
                }

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
                if (validaEdicaoProduto(novoProduto)) {
                    daoProduto.alterar(novoProduto);
                    JOptionPane.showMessageDialog(null, "Produto Editado com Sucesso!", "Sucesso", 1);
                    preencheTabelaProdutos(daoProduto.buscarTodos(), visaoGerenciarProdutos.getTblProdutos());
                    visaoEditarProduto.dispose();
                }
            }
        };
        visaoEditarProduto.getBtnSalvar().addActionListener(actionListener);
    }

    private boolean validaEdicaoProduto(Produto produto) {

        restauraCorCamposEdicao();

        if (produto.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório!", "Erro na Validação", 0);
            visaoEditarProduto.getTxtDescricao().requestFocus();
            visaoEditarProduto.getTxtDescricao().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (produto.getValor() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor' é obrigatório!"
                    + "\nPermitidos apenas números inteiros ou reais.", "Erro na Validação", 0);
            visaoEditarProduto.getTxtValor().requestFocus();
            visaoEditarProduto.getTxtValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (produto.getValor() <= 0) {
            JOptionPane.showMessageDialog(null, "O Valor de um Produto deve ser maior que zero!", "Erro na Validação", 0);
            visaoEditarProduto.getTxtValor().requestFocus();
            visaoEditarProduto.getTxtValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        return true;
    }

    private void restauraCorCamposEdicao() {
        visaoEditarProduto.getTxtDescricao().setBackground(Color.white);
        visaoEditarProduto.getTxtValor().setBackground(Color.white);
        visaoEditarProduto.getTxtCor().setBackground(Color.white);
        visaoEditarProduto.getTxtTamanho().setBackground(Color.white);
        visaoEditarProduto.getTxtMarca().setBackground(Color.white);
        visaoEditarProduto.getTxtModelo().setBackground(Color.white);
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

    //----- TELA ESTOQUE PRODUTOS -----
    
    public void atualizaTabelaEstoque(){
        preencheTabelaEstoque(daoItemEstoque.buscarTodos(), visaoEstoqueProdutos.getTblEstoqueProdutos());
    }    
    
    public void preencheTabelaEstoque(List<ItemEstoque> lista, JTable tabela) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (ItemEstoque itemEstoque : lista) {
            modelo.addRow(new Object[]{
                itemEstoque.getId(),
                itemEstoque.getProduto().getDescricao(),
                itemEstoque.getQuantidade()
            });
        }
    }

    private void evtBotaoFecharEstoque() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoEstoqueProdutos.dispose();
            }
        };
        visaoEstoqueProdutos.getBtnFechar().addActionListener(actionListener);
    }

    private void evtBotaoEditarEstoque() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoEstoqueProdutos.getTblEstoqueProdutos().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum Prodruto selecionado!", "Erro", 0);
                } else {
                    Integer id = (Integer) visaoEstoqueProdutos.getTblEstoqueProdutos().getValueAt(linha, 0);
                    ItemEstoque item = daoItemEstoque.buscarPorId(id);
                    visaoEditarEstoque.getTxtId().setText(id.toString());
                    visaoEditarEstoque.getTxtDescricao().setText(item.getProduto().getDescricao());
                    visaoEditarEstoque.getTxtQuantidade().setText(item.getQuantidade().toString());
                    renderizaVisaoEditarEstoque();
                }
            }
        };
        visaoEstoqueProdutos.getBtnEditar().addActionListener(actionListener);
    }

    //----- TELA EDITAR ESTOQUE PRODUTOS -----
    private void evtBotaoCancelarEditarEstoque() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoEditarEstoque.dispose();
            }
        };
        visaoEditarEstoque.getBtnCancelar().addActionListener(actionListener);
    }

    private void evtBotaoSalvarEditarEstoque() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Integer id = Integer.parseInt(visaoEditarEstoque.getTxtId().getText());
                Integer quantidade = Integer.parseInt(visaoEditarEstoque.getTxtQuantidade().getText());

                ItemEstoque item = daoItemEstoque.buscarPorId(id);
                item.setQuantidade(quantidade);
                daoItemEstoque.alterar(item);

                JOptionPane.showMessageDialog(null, "Produto Editado com Sucesso!", "Sucesso", 1);
                preencheTabelaEstoque(daoItemEstoque.buscarTodos(), visaoEstoqueProdutos.getTblEstoqueProdutos());
                visaoEditarEstoque.dispose();

            }
        };
        visaoEditarEstoque.getBtnSalvar().addActionListener(actionListener);
    }

    public ProdutoDAO getDaoProduto() {
        return daoProduto;
    }

    public ItemEstoqueDAO getDaoItemEstoque() {
        return daoItemEstoque;
    }

}
