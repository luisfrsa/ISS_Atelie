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
import modelo.ItemEstoque;
import modelo.ItemSacola;
import modelo.Produto;
import modelo.Sacola;
import util.Datas;
import visao.sacola.FormAssociarProdutoSacola;
import visao.sacola.FormCriarSacola;
import visao.sacola.FormDetalhesSacola;
import visao.sacola.FormDevolverProduto;
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
    private static final FormDetalhesSacola visaoDetalhesSacola = new FormDetalhesSacola();
    private static final FormDevolverProduto visaoDevolverProduto = new FormDevolverProduto();
    private Sacola sacola = new Sacola();
    private ActionListener actionListener;

    private boolean ouvirEventosGerenciar = true;
    private boolean ouvirEventosCriar = true;
    private boolean ouvirEventosAssociarProduto = true;
    private boolean ouvirEventosDetalhesSacola = true;
    private boolean ouvirEventosDevolver = true;

    //Métodos
    public void renderizarVisaoGerenciarSacolas() {
        if (ouvirEventosGerenciar) {
            evtBotaoCriarNova();
            evtBotaoExcluir();
            evtBotaoDetalhes();
            ouvirEventosGerenciar = false;
        }
        preencheTabelaSacolas(daoSacola.buscarTodas(), visaoGerenciarSacolas.getTblSacolas());
        visaoGerenciarSacolas.getTxtConsultora().setText("");
        visaoGerenciarSacolas.setVisible(true);
    }

    public void renderizarVisaoCriarSacola() {
        if (ouvirEventosCriar) {
            evtBotaoAssociarConsultora();
            evtBotaoAdicionarProduto();
            evtBotaoCriarSacola();
            evtBotaoCancelarCriar();
            ouvirEventosCriar = false;
        }
        restauraFormCriarSacola();
        sacola = new Sacola();
        preencheTabelaItensSacolaSemId(sacola.getListaItens(), visaoCriarSacola.getTblItensDeSacola());
        visaoCriarSacola.setVisible(true);
    }

    public void renderizarVisaoAssociarProduto() {
        if (ouvirEventosAssociarProduto) {
            evtBotaoBuscar();
            evtBotaoConfirmar();
            evtBotaoCancelarAssociar();
            ouvirEventosAssociarProduto = false;
        }
        restauraFormAssociarProduto();
        controleProduto.preencheTabelaProdutos(controleProduto.getDaoProduto().buscarTodos(), visaoAssociarProduto.getTblProdutos());
        visaoAssociarProduto.getTxtQuantidade().setText("");
        visaoAssociarProduto.getTxtDescricao().setText("");
        visaoAssociarProduto.setVisible(true);
    }

    public void renderizaVisaoDetalhesSacola(Sacola sacola) {
        if (ouvirEventosDetalhesSacola) {
            evtBotaoFecharDetalhes();
            evtBotaoDevolverProdutos();
            ouvirEventosDetalhesSacola = false;
        }
        String nomeConsultora = sacola.getConsultora().getNome();
        String dataCriacao = Datas.dateToString(sacola.getDataCriacao());
        String dataAcerto = Datas.dateToString(sacola.getDataAcerto());

        visaoDetalhesSacola.getLblCodigo().setText(sacola.getId().toString());
        visaoDetalhesSacola.getLblNomeConsultora().setText(nomeConsultora);
        visaoDetalhesSacola.getLblDataCriacao().setText(dataCriacao);
        visaoDetalhesSacola.getLblDataAcerto().setText(dataAcerto);
        preencheTabelaItensSacola(sacola.getListaItens(), visaoDetalhesSacola.getTblItensDeSacola());
        visaoDetalhesSacola.setVisible(true);
    }

    public void renderizarVisaoDevolverProduto() {
        if (ouvirEventosDevolver) {
            evtBotaoCancelarDevolver();
            evtBotaoDevolver();
            ouvirEventosDevolver = false;
        }
        visaoDevolverProduto.getTxtQuantidade().setText("");
        visaoDevolverProduto.setVisible(true);
    }

    //----- TELA GERENCIAR SACOLAS -----
    public void preencheTabelaSacolas(List<Sacola> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (Sacola sacola : lista) {
            if (!sacola.isFinalizada()) {
                modelo.addRow(new Object[]{
                    sacola.getId(),
                    sacola.getConsultora().getNome(),
                    Datas.formatoData.format(sacola.getDataCriacao()),
                    Datas.formatoData.format(sacola.getDataAcerto()),
                    sacola.isFinalizada() ? "Finalizada" : "Não finalizada"
                });
            }
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

    private void evtBotaoExcluir() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoGerenciarSacolas.getTblSacolas().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Sacola selecionada!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirma a exclusão da Sacola de "
                            + visaoGerenciarSacolas.getTblSacolas().getValueAt(linha, 1)
                            + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoGerenciarSacolas.getTblSacolas().getValueAt(linha, 0); //ID do item selecionado
                        //remover itens de sacola associados a sacola
                        Sacola sacola = daoSacola.buscarPorId(id);
                        excluiItensDaSacola(sacola);
                        daoSacola.remover(sacola);                        
                        preencheTabelaSacolas(daoSacola.buscarTodas(), visaoGerenciarSacolas.getTblSacolas());
                        controleProduto.atualizaTabelaEstoque();
                        JOptionPane.showMessageDialog(null, "Sacola excluida com Sucesso!", "Sucesso", 1);
                    }
                }
            }
        };
        visaoGerenciarSacolas.getBtnExcluir().addActionListener(actionListener);
    }

    private void excluiItensDaSacola(Sacola sacola) {

        Integer quantidade;
        Produto produto;
        ItemEstoque itemEstoque;

        for (ItemSacola item : sacola.getListaItens()) {

            produto = item.getProduto();
            quantidade = item.getQuantidade();
            itemEstoque = controleProduto.getDaoItemEstoque().buscarPorId(produto.getId()); //busca o item no estoque
            itemEstoque.setQuantidade(itemEstoque.getQuantidade() + quantidade); //devolve a quantidade ao estoque
            controleProduto.getDaoItemEstoque().alterar(itemEstoque);

            daoItemSacola.remover(item);
        }
    }

    private void evtBotaoDetalhes() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoGerenciarSacolas.getTblSacolas().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Sacola selecionada!", "Erro", 0);
                } else {
                    Integer id = (Integer) visaoGerenciarSacolas.getTblSacolas().getValueAt(linha, 0); //ID do item selecionado
                    Sacola sacola = daoSacola.buscarPorId(id);
                    renderizaVisaoDetalhesSacola(sacola);
                }
            }
        };
        visaoGerenciarSacolas.getBtnDetalhes().addActionListener(actionListener);
    }

    //----- TELA CRIAR SACOLA -----
    private void restauraFormCriarSacola() {
        visaoCriarSacola.getLblNomeConsultora().setText("Nenhuma Consultora associada.");
        visaoCriarSacola.getTxtCpf().setText("");
        visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(255, 255, 255));
        visaoCriarSacola.getTxtCpf().setEditable(true);
        visaoCriarSacola.getTxtDataAcerto().setText("");
        visaoCriarSacola.getTxtDataAcerto().setBackground(new java.awt.Color(255, 255, 255));
        visaoCriarSacola.getBtnAssociarConsultora().setEnabled(true);
    }

    public void preencheTabelaItensSacola(List<ItemSacola> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (ItemSacola item : lista) {
            modelo.addRow(new Object[]{
                item.getId(),
                item.getProduto().getDescricao(),
                item.getQuantidade()
            });
        }
    }

    public void preencheTabelaItensSacolaSemId(List<ItemSacola> lista, JTable tabela) {
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

        for (Sacola s : daoSacola.buscarTodas()) {
            if ((!s.isFinalizada()) && (s.getConsultora().equals(consultora))) {
                JOptionPane.showMessageDialog(null, "Já existe uma Sacola ativa associada a esta Consultora.", "Erro na Validação", 0);
                visaoCriarSacola.getTxtCpf().requestFocus();
                visaoCriarSacola.getTxtCpf().setBackground(new java.awt.Color(255, 204, 204));
                return false;
            }
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

    private void evtBotaoCriarSacola() {
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
                sacola.setFinalizada(false);

                if (validaSacola(sacola)) {
                    atualizaEstoqueCriarSacola(sacola);
                    daoSacola.inserir(sacola);
                    preencheTabelaSacolas(daoSacola.buscarTodas(), visaoGerenciarSacolas.getTblSacolas());
                    controleProduto.atualizaTabelaEstoque();
                    JOptionPane.showMessageDialog(null, "Sacola cadastrada com Sucesso!", "Sucesso", 1);
                    visaoCriarSacola.dispose();
                }
            }
        };
        visaoCriarSacola.getBtnCriarSacola().addActionListener(actionListener);
    }

    private void atualizaEstoqueCriarSacola(Sacola sacola) {
        Integer idProduto;
        ItemEstoque itemEstoque;
        for (ItemSacola itemSacola : sacola.getListaItens()) {
            idProduto = itemSacola.getProduto().getId();
            itemEstoque = controleProduto.getDaoItemEstoque().buscarPorId(idProduto);
            itemEstoque.setQuantidade(itemEstoque.getQuantidade() - itemSacola.getQuantidade());
            controleProduto.getDaoItemEstoque().alterar(itemEstoque);
        }
    }

    private boolean validaSacola(Sacola sacola) {
        visaoCriarSacola.getTxtDataAcerto().setBackground(new java.awt.Color(255, 255, 255));

        if (sacola.getConsultora() == null) {
            JOptionPane.showMessageDialog(null, "Preencha o campo 'CPF da culsoltora' e associe uma Consultora à Sacola!", "Erro na Validação", 0);
            visaoCriarSacola.getBtnAssociarConsultora().requestFocus();
            return false;
        }

        if (sacola.getDataAcerto() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data de Acerto' é obrigatório!"
                    + "\nFormato de data: dd/mm/aaaa", "Erro na Validação", 0);
            visaoCriarSacola.getTxtDataAcerto().requestFocus();
            visaoCriarSacola.getTxtDataAcerto().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (sacola.getListaItens().size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum Produto foi adicionado à Sacola", "Erro na Validação", 0);
            visaoCriarSacola.getBtnAdicionarProduto().requestFocus();
            return false;
        }

        return true;
    }

    private void evtBotaoCancelarCriar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar a criação da Sacola? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoCriarSacola.dispose();
                }
            }
        };
        visaoCriarSacola.getBtnCancelar().addActionListener(actionListener);

    }

    //----- TELA ASSOCIAR PRODUTO -----
    private void restauraFormAssociarProduto() {

        visaoAssociarProduto.getTxtQuantidade().setBackground(new java.awt.Color(255, 255, 255));
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
                    preencheTabelaItensSacolaSemId(sacola.getListaItens(), visaoCriarSacola.getTblItensDeSacola());
                    visaoAssociarProduto.dispose();
                }

            }
        };
        visaoAssociarProduto.getBtnConfirmar().addActionListener(actionListener);
    }

    private boolean validaFormAssociarProduto(int linha) {
        restauraFormAssociarProduto();

        if (linha < 0) {
            JOptionPane.showMessageDialog(null, "Nenhum produto selecionado na tabela.", "Erro", 0);
            return false;
        }

        if (visaoAssociarProduto.getTxtQuantidade().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Quantidade' é obrigatório!", "Erro na Validação", 0);
            visaoAssociarProduto.getTxtQuantidade().requestFocus();
            visaoAssociarProduto.getTxtQuantidade().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (produtoRepetidoSacola((Integer) visaoAssociarProduto.getTblProdutos().getValueAt(linha, 0))) {
            JOptionPane.showMessageDialog(null, "O Produto selecionado já foi adicionado à Sacola!", "Erro na Validação", 0);
            return false;
        }

        try {
            Integer quantidadeDigitada = Integer.parseInt(visaoAssociarProduto.getTxtQuantidade().getText());
            Integer idProduto = (Integer) visaoAssociarProduto.getTblProdutos().getValueAt(linha, 0);
            ItemEstoque itemEstoque = controleProduto.getDaoItemEstoque().buscarPorId(idProduto);
            Integer quantidadeEstoque = itemEstoque.getQuantidade();

            if (quantidadeDigitada > quantidadeEstoque) {
                JOptionPane.showMessageDialog(null, "A Quantidade digitada ultrapassa a Quantidade em Estoque do Produto!", "Erro na Validação", 0);
                visaoAssociarProduto.getTxtQuantidade().requestFocus();
                visaoAssociarProduto.getTxtQuantidade().setBackground(new java.awt.Color(255, 204, 204));
                return false;
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O campo 'Quantidade' aceita apenas número inteiros!", "Erro na Validação", 0);
            visaoAssociarProduto.getTxtQuantidade().requestFocus();
            visaoAssociarProduto.getTxtQuantidade().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        return true;
    }

    private boolean produtoRepetidoSacola(Integer id) {
        for (ItemSacola item : sacola.getListaItens()) {
            if (item.getProduto().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private void evtBotaoCancelarAssociar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoAssociarProduto.dispose();
            }
        };
        visaoAssociarProduto.getBtnCancelar().addActionListener(actionListener);

    }

    //----- TELA DETALHES DA SACOLA -----
    private void evtBotaoFecharDetalhes() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoDetalhesSacola.dispose();
            }
        };
        visaoDetalhesSacola.getBtnFechar().addActionListener(actionListener);
    }

    public void finalizar(Integer id) {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Você realmente deseja finalizar esta sacola?", "Atenção", dialogButton);
        if (dialogResult == 0) {
            Sacola sac = daoSacola.buscarPorId(id);
            sac.setFinalizada(true);
            Calendar calendario = Calendar.getInstance();
            Date dataFinalizada = calendario.getTime();
            sac.setDataFinalizada(dataFinalizada);
            daoSacola.alterar(sac);
            JOptionPane.showMessageDialog(null, "Sacola finalizada com Sucesso!", "Sucesso", 1);
            preencheTabelaSacolas(daoSacola.buscarTodas(), visaoGerenciarSacolas.getTblSacolas());
            visaoDetalhesSacola.dispose();

        }

    }

    public void evtBotaoDevolverProdutos() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoDetalhesSacola.getTblItensDeSacola().getSelectedRow();
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum produto selecionado na tabela.", "Erro", 0);
                } else {
                    Integer idItem = (Integer) visaoDetalhesSacola.getTblItensDeSacola().getValueAt(linha, 0);
                    ItemSacola item = daoItemSacola.buscarPorId(idItem);
                    Produto produto = item.getProduto();

                    visaoDevolverProduto.getLblDescricao().setText(produto.getDescricao());
                    visaoDevolverProduto.getLblIdItem().setText(item.getId().toString());
                    renderizarVisaoDevolverProduto();
                }
            }
        };
        visaoDetalhesSacola.getBtnDevolver().addActionListener(actionListener);
    }

    //----- TELA DEVOLVER PRODUTO -----
    private void evtBotaoCancelarDevolver() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                visaoDevolverProduto.dispose();
            }
        };
        visaoDevolverProduto.getBtnCancelar().addActionListener(actionListener);
    }

    private void evtBotaoDevolver() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Integer idSacola = Integer.parseInt(visaoDetalhesSacola.getLblCodigo().getText());
                Sacola sac = daoSacola.buscarPorId(idSacola);
                Integer idItem = Integer.parseInt(visaoDevolverProduto.getLblIdItem().getText());
                ItemSacola item = daoItemSacola.buscarPorId(idItem);
                
                if (validaQuantidadeDevolve(item)) {
                    //Removendo produto da sacola
                    Integer quantidadeDigitada = Integer.parseInt(visaoDevolverProduto.getTxtQuantidade().getText());
                    item.setQuantidade(item.getQuantidade() - quantidadeDigitada);
                    daoItemSacola.alterar(item);
                    //Devolvendo produto ao estoque
                    Produto produto = item.getProduto();
                    ItemEstoque itemEstoque = controleProduto.getDaoItemEstoque().buscarPorId(produto.getId());
                    itemEstoque.setQuantidade(itemEstoque.getQuantidade() + quantidadeDigitada);
                    controleProduto.getDaoItemEstoque().alterar(itemEstoque);  
                    
                    //Remove o item da sacola caso tenha sido devolvido pro completo
                    if(item.getQuantidade() == 0){
                        sac.getListaItens().remove(item);
                        daoItemSacola.remover(item);
                    }
                    
                    controleProduto.atualizaTabelaEstoque();
                    preencheTabelaItensSacola(sac.getListaItens(), visaoDetalhesSacola.getTblItensDeSacola());
                    JOptionPane.showMessageDialog(null, "Produto devolvido ao Estoque!", "Sucesso", 1);
                    visaoDevolverProduto.dispose();
                }
            }
        };
        visaoDevolverProduto.getBtnDevolver().addActionListener(actionListener);
    }

    private boolean validaQuantidadeDevolve(ItemSacola item) {
        Integer quantidadeDigitada = null;
        
        try {
            quantidadeDigitada = Integer.parseInt(visaoDevolverProduto.getTxtQuantidade().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O campo 'Quantidade' é obrigatório."
                    + "\nAceita apenas número inteiros!", "Erro na Validação", 0);
            visaoDevolverProduto.getTxtQuantidade().requestFocus();
            return false;
        }
        
        if(quantidadeDigitada <=0){
            JOptionPane.showMessageDialog(null, "A quantidade a ser devolvida deve ser maior que zero!", "Erro na Validação", 0);
            visaoDevolverProduto.getTxtQuantidade().requestFocus();
            return false;
        }
        
        if(quantidadeDigitada > item.getQuantidade()){
            JOptionPane.showMessageDialog(null, "A quantidade devolvida é maior do que a quantidade do Produto na Sacola!", "Erro na Validação", 0);
            visaoDevolverProduto.getTxtQuantidade().requestFocus();
            return false;
        }        
        
        return true;
    }

    //----- CALCULO DE LUCRO -----
    public Double calculoLucroSacolas(List<Sacola> sacolas) {
        return sacolas.stream()
                .map(sacola -> calculoLucroSacolas(sacola))
                .reduce(0.0, (a, b) -> a + b);
    }

    public Double calculoLucroSacolas(Sacola sacola) {
        return sacola.getListaItens().stream()
                .map(itens -> itens.getProduto().getValor() * itens.getQuantidade())
                .reduce(0.0, (a, b) -> a + b);

    }

}
