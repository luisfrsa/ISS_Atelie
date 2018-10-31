/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ContasPagarDAO;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ContasPagar;
import modelo.builder.ContasPagarBuilder;
import util.Datas;
import static util.Datas.formatoData;
import visao.contaspagar.FormBaixarContasPagar;
import visao.contaspagar.FormCadastrarContasPagar;
import visao.contaspagar.FormEditarContasPagar;
import visao.contaspagar.FormGerenciarContasPagar;


/**
 *
 * @author Ronnie
 */
public class ContasPagarControle {

    private static final ContasPagarDAO contasPagarDao = new ContasPagarDAO();
    private static final FormGerenciarContasPagar visaoGerenciarContasPagar = new FormGerenciarContasPagar();
    private static final FormCadastrarContasPagar visaoCadastrarContasPagar = new FormCadastrarContasPagar();
    private static final FormEditarContasPagar visaoEditarContasPagar = new FormEditarContasPagar();
    private static final FormBaixarContasPagar visaoBaixarContasPagar = new FormBaixarContasPagar();

    private boolean ouvirGerenciarContasPagar = true;
    private boolean ouvirCadastrarContasPagar = true;
    private boolean ouvirEditarContasPagar = true;
    private boolean ouvirBaixarContasPagar = true;

    private ActionListener actionListener;

    public void renderizarVisaoGerenciarContasPagar() {
        if (ouvirGerenciarContasPagar) {
            evtBotaoCadastrar();
            evtBotaoMaisDetalhes();
            evtBotaoBaixarConta();
            evtBotaoBuscar();
            evtBotaoExcluir();
            ouvirGerenciarContasPagar = false;
        }
        preencheTabela(contasPagarDao.buscarTodos(), visaoGerenciarContasPagar.getTblContasPagar());
        visaoGerenciarContasPagar.setVisible(true);
    }

    public void renderizarVisaoCadastrarContasPagar() {
        if (ouvirCadastrarContasPagar) {
            evtBotaoNovoCadastrar();
            evtBotaoCancelarCadastro();
            ouvirCadastrarContasPagar = false;
        }
        visaoCadastrarContasPagar.setVisible(true);
    }

    public void renderizarVisaoEditarContasPagar() {
        if (ouvirEditarContasPagar) {
            evtBotaoSalvar();
            evtBotaoCancelarEdicao();
            ouvirEditarContasPagar = false;
        }
        visaoEditarContasPagar.setVisible(true);
    }

    public void renderizarVisaoBaixarContasPagar() {
        if (ouvirBaixarContasPagar) {
            evtBotaoCancelarBaixar();
            evtBotaoBaixarSalvar();
            ouvirBaixarContasPagar = false;
        }
       visaoBaixarContasPagar.setVisible(true);
    }
    //------------------------Tela GerenciarContasPagar-----------
    private void preencheTabela(List<ContasPagar> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (ContasPagar contaspagar : lista) {

            modelo.addRow(new Object[]{
                contaspagar.getId(),
                contaspagar.getFornecedor(),
                contaspagar.getValor(),
                contaspagar.getStatus()
            });

        }
    }

    private void evtBotaoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoCadastrarContasPagar();
            }
        };
        visaoGerenciarContasPagar.getBtnCadastrar().addActionListener(actionListener);
    }

    private void evtBotaoMaisDetalhes() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoGerenciarContasPagar.getTblContasPagar().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Conta Selecionada!", "Erro", 0);
                } else {
                    Integer id = (Integer) visaoGerenciarContasPagar.getTblContasPagar().getValueAt(linha, 0); //ID do item selecionado
                    ContasPagar contasPagarSelecionada = contasPagarDao.buscarPorId(id);
                    //Preenche os campos do form editar
                    visaoEditarContasPagar.getjTextFieldStatus().setText(contasPagarSelecionada.getStatus());
                    visaoEditarContasPagar.getJTextFieldID().setText(contasPagarSelecionada.getId().toString());
                    visaoEditarContasPagar.getjTextFieldFornecedor().setText(contasPagarSelecionada.getFornecedor());
                    visaoEditarContasPagar.getjTextFieldDescricao().setText(contasPagarSelecionada.getDescricao());
                    visaoEditarContasPagar.getjFormattedTextFieldDataVencimento().setText(stringData(contasPagarSelecionada.getVencimento()));
                    visaoEditarContasPagar.getjFormattedTextFieldDatadaBaixa().setText(stringData(contasPagarSelecionada.getBaixa()));
                    visaoEditarContasPagar.getjTextFieldValor().setText(Double.toString(contasPagarSelecionada.getValor()));
                    visaoEditarContasPagar.getjTextFieldStatus().setEditable(false);
                    visaoEditarContasPagar.getJTextFieldID().setEditable(false);
                    renderizarVisaoEditarContasPagar();
                }

            }
        };
        visaoGerenciarContasPagar.getBtnDetalhes().addActionListener(actionListener);
    }

    private void evtBotaoExcluir() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoGerenciarContasPagar.getTblContasPagar().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Conta selecionada!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão da Conta "
                            + visaoGerenciarContasPagar.getTblContasPagar().getValueAt(linha, 1)
                            + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoGerenciarContasPagar.getTblContasPagar().getValueAt(linha, 0); //ID do item selecionado
                        ContasPagar contaRemovida = contasPagarDao.buscarPorId(id);
                        contaRemovida.setStatus("REMOVIDA");
                        contasPagarDao.alterar(contaRemovida);
                        JOptionPane.showMessageDialog(null, "Conta Excluida com Sucesso!", "Sucesso", 1);
                        preencheTabela(contasPagarDao.buscarTodos(), visaoGerenciarContasPagar.getTblContasPagar()); //Atualiza tabela após remoção
                    }
                }
            }
        };
        visaoGerenciarContasPagar.getBtnExcluir().addActionListener(actionListener);
    }

    private void evtBotaoBuscar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String busca = visaoGerenciarContasPagar.getTxtFornecedor().getText();
                if (busca.equals("")) {
                    preencheTabela(contasPagarDao.buscarTodos(), visaoGerenciarContasPagar.getTblContasPagar());
                } else {
                    List<ContasPagar> listaDeBusca = buscaPorFornecedor(busca);
                    preencheTabela(listaDeBusca, visaoGerenciarContasPagar.getTblContasPagar());
                }
            }
        };
        visaoGerenciarContasPagar.getBtnBuscar().addActionListener(actionListener);

    }
    
    public List<ContasPagar> buscaPorFornecedor(String busca) {

        List<ContasPagar> listaDeBusca = new ArrayList<>();
        int tamanho = contasPagarDao.buscarTodos().size();
        List <ContasPagar>listaAux = contasPagarDao.buscarTodos();
        for (int k = 0; k < tamanho; k++) {
            if (busca.toLowerCase().contains(listaAux.get(k).getFornecedor().toLowerCase())){
                listaDeBusca.add(listaAux.get(k));
            }
        }
        return listaDeBusca;
    }

    private void evtBotaoBaixarConta() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoBaixarContasPagar();
            }
        };
        visaoGerenciarContasPagar.getBtnBaixar().addActionListener(actionListener);
    }



//-------------------Tela CadastrarContasPagar-----------------------
    private void evtBotaoNovoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                //Obtendo os dados inseridos na visão
                String descricao = visaoCadastrarContasPagar.getjTextFieldDescricao().getText();

                Double valor;
                try {
                    valor = Double.parseDouble(visaoCadastrarContasPagar.getjTextFieldValor().getText());
                } catch (NumberFormatException e) {
                    valor = null;
                }

                int parcelas;
                try {
                    parcelas = Integer.parseInt(visaoCadastrarContasPagar.getjTextFieldNumeroParcelas().getText());
                } catch (NumberFormatException e) {
                    parcelas = 0;
                }

                Double valorparcela;
                 try {
                    valorparcela = Double.parseDouble(visaoCadastrarContasPagar.getjTextFieldValordaParcela().getText());
                } catch (NumberFormatException e) {
                    valorparcela= null;
                }
                
                
                String fornecedor = visaoCadastrarContasPagar.getjTextFieldFornecedor().getText();
                String formaDePagamento = (String) visaoCadastrarContasPagar.getjComboBoxFormaPagamento().getSelectedItem();
                Date entrada = Datas.stringToData(visaoCadastrarContasPagar.getjFormattedTextFieldDataEntrada().getText());

                /*
                ContasPagar contaspagar = new ContasPagarBuilder(descricao)
                        .setEntrada(entrada)
                        .setFormaPagamento(formaDePagamento)
                        .setFornecedor(fornecedor)
                        .setValor(valor)
                        .build();
                
                contasPagarDao.inserir(contaspagar);
                 */
                ContasPagar contaspagar = new ContasPagar();
                contaspagar.setEntrada(entrada);
                contaspagar.setFormaPagamento(formaDePagamento);
                contaspagar.setFornecedor(fornecedor);
                contaspagar.setValor(valor);
                contaspagar.setDescricao(descricao);
                contaspagar.setStatus("PENDENTE");

                if (validaCadastroContasPagar(contaspagar)) {
                    Calendar vencimentoparcelas = Calendar.getInstance();//variáveL que calcula vencimento das parcelas
                    vencimentoparcelas.setTime(entrada);
                    if (parcelas > 0 && contaspagar.getFormaPagamento() == "A Prazo") {
                        for (int i = 0; i < parcelas; i++) {
                            
                            contaspagar.setEntrada(entrada);
                            contaspagar.setFormaPagamento(formaDePagamento);
                            contaspagar.setFornecedor(fornecedor);
                            contaspagar.setValor(valorparcela);
                            contaspagar.setDescricao(descricao);
                            contaspagar.setStatus("PENDENTE");
                            
                           // -----Calculo do Vencimento (parcela)
                            vencimentoparcelas.add(Calendar.DAY_OF_MONTH, +30);
                            contaspagar.setVencimento(vencimentoparcelas.getTime());
                            contasPagarDao.inserir(contaspagar);
                            contaspagar = new ContasPagar();
                        }
                    } else {
                        
                        contaspagar.setVencimento(entrada);
                        contaspagar.setBaixa(entrada);
                        contaspagar.setStatus("LIQUIDADA");
                        contasPagarDao.inserir(contaspagar);
                    }
                    JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!", "Sucesso", 1);
                    preencheTabela(contasPagarDao.buscarTodos(), visaoGerenciarContasPagar.getTblContasPagar()); //Atualiza tabela após cadastro
                    
                    limparCamposCadastro();
                    visaoCadastrarContasPagar.dispose();
                }

            }

        };

        visaoCadastrarContasPagar.getBtnCadastrar().addActionListener(actionListener);
    }

    private boolean validaCadastroContasPagar(ContasPagar contaspagar) {
        restauraCorCamposCadastro();
        if (contaspagar.getFormaPagamento()== "A Prazo" && visaoCadastrarContasPagar.getjTextFieldNumeroParcelas().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Número de parcelas' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarContasPagar.getjTextFieldNumeroParcelas().requestFocus();
            visaoCadastrarContasPagar.getjTextFieldNumeroParcelas().setBackground(Color.yellow);
            return false;
        }
        if (visaoCadastrarContasPagar.getjComboBoxFormaPagamento().getSelectedItem().toString().equals("A Prazo") && visaoCadastrarContasPagar.getjTextFieldValordaParcela().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor da Parcela ' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarContasPagar.getjTextFieldValordaParcela().requestFocus();
            visaoCadastrarContasPagar.getjTextFieldValordaParcela().setBackground(Color.yellow);
            return false;
        }
        
        if (contaspagar.getEntrada() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data da entrada' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarContasPagar.getjFormattedTextFieldDataEntrada().requestFocus();
            visaoCadastrarContasPagar.getjFormattedTextFieldDataEntrada().setBackground(Color.yellow);
            return false;
        }
        if (contaspagar.getFornecedor().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Fornecedor' é obrigatório!", "Erro na Validação", 0);
            visaoCadastrarContasPagar.getjTextFieldFornecedor().requestFocus();
            visaoCadastrarContasPagar.getjTextFieldFornecedor().setBackground(Color.yellow);
            return false;
        }

        if (contaspagar.getValor() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor' é obrigatório!"
                    + "\nPermitidos apenas números inteiros ou reais.", "Erro na Validação", 0);
            visaoCadastrarContasPagar.getjTextFieldValor().requestFocus();
            visaoCadastrarContasPagar.getjTextFieldValor().setBackground(Color.yellow);
            return false;
        }

        if (contaspagar.getValor() <= 0) {
            JOptionPane.showMessageDialog(null, "O Valor de uma Conta deve ser maior que zero!", "Erro na Validação", 0);
            visaoCadastrarContasPagar.getjTextFieldValor().requestFocus();
            visaoCadastrarContasPagar.getjTextFieldValor().setBackground(Color.yellow);
            return false;
        }

        return true;
    }

    private void restauraCorCamposCadastro() {
        visaoCadastrarContasPagar.getjTextFieldFornecedor().setBackground(Color.white);
        visaoCadastrarContasPagar.getjTextFieldDescricao().setBackground(Color.white);
        visaoCadastrarContasPagar.getjTextFieldValor().setBackground(Color.white);
        visaoCadastrarContasPagar.getjFormattedTextFieldDataEntrada().setBackground(Color.white);
        visaoCadastrarContasPagar.getjTextFieldNumeroParcelas().setBackground(Color.white);
        visaoCadastrarContasPagar.getjTextFieldValordaParcela().setBackground(Color.white);
    }

    private void limparCamposCadastro() {
        visaoCadastrarContasPagar.getjTextFieldFornecedor().setText("");
        visaoCadastrarContasPagar.getjTextFieldDescricao().setText("");
        visaoCadastrarContasPagar.getjTextFieldValor().setText("");
        visaoCadastrarContasPagar.getjFormattedTextFieldDataEntrada().setText("");
        visaoCadastrarContasPagar.getjTextFieldNumeroParcelas().setText("");
        visaoCadastrarContasPagar.getjTextFieldValordaParcela().setText("");
    }
    private void evtBotaoCancelarCadastro() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro de contas? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoCadastrarContasPagar.dispose();
                }
            }

        };
        visaoCadastrarContasPagar.getBtnCancelar().addActionListener(actionListener);
    }
//--------------------Tela EditarContas a Pagar------------------------------------
    private void evtBotaoSalvar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //coletando dados da  visaoeditar
                Integer id = Integer.parseInt(visaoEditarContasPagar.getJTextFieldID().getText());
                String Fornecedor = visaoEditarContasPagar.getjTextFieldFornecedor().getText();
                String descricao = visaoEditarContasPagar.getjTextFieldDescricao().getText();

                 Double valor;
                try {
                    valor = Double.parseDouble(visaoEditarContasPagar.getjTextFieldValor().getText());
                } catch (Exception e) {
                    valor = null;
                }
           
               Date datavencimento = Datas.stringToData(visaoEditarContasPagar.getjFormattedTextFieldDataVencimento().getText());
               Date databaixa =  Datas.stringToData(visaoEditarContasPagar.getjFormattedTextFieldDatadaBaixa().getText());

               ContasPagar contanova = contasPagarDao.buscarPorId(id);
               
               contanova.setFornecedor(Fornecedor);
               contanova.setDescricao(descricao);
               contanova.setValor(valor);
               contanova.setVencimento(datavencimento);
               contanova.setBaixa(databaixa);
                if(contanova.getBaixa()!=null){
                    contanova.setStatus("LIQUIDADA");
                }
                if (validarContasEdicao(contanova)) {
                    contasPagarDao.alterar(contanova);
                    JOptionPane.showMessageDialog(null, "Conta Editada com Sucesso!", "Sucesso", 1);
                    visaoEditarContasPagar.dispose();
                    preencheTabela(contasPagarDao.buscarTodos() , visaoGerenciarContasPagar.getTblContasPagar());
                }
            }
        };
        visaoEditarContasPagar.getBtnSalvar().addActionListener(actionListener);
    }
               
               
               
               
               
    private boolean validarContasEdicao(ContasPagar contas) {
        restauraCorCamposEdicao();
        if (contas.getFornecedor().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Fornecedor' é obrigatório!", "Erro na Validação", 0);
            visaoEditarContasPagar.getjTextFieldFornecedor().requestFocus();
            visaoEditarContasPagar.getjTextFieldFornecedor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório!", "Erro na Validação", 0);
            visaoEditarContasPagar.getjTextFieldDescricao().requestFocus();
            visaoEditarContasPagar.getjTextFieldDescricao().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getValor() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor' é obrigatório!"
                    + "\nPermitidos apenas números inteiros ou reais.", "Erro na Validação", 0);
            visaoEditarContasPagar.getjTextFieldValor().requestFocus();
            visaoEditarContasPagar.getjTextFieldValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getValor() <= 0) {
            JOptionPane.showMessageDialog(null, "O Valor de um Produto deve ser maior que zero!", "Erro na Validação", 0);
            visaoEditarContasPagar.getjTextFieldValor().requestFocus();
            visaoEditarContasPagar.getjTextFieldValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getVencimento() == null) {
            JOptionPane.showMessageDialog(null, "Data de vencimento incompleta ou inexistente", "Erro na Validação", 0);
            visaoEditarContasPagar.getjFormattedTextFieldDataVencimento().requestFocus();
            visaoEditarContasPagar.getjFormattedTextFieldDataVencimento().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        return true;
    }

    private void restauraCorCamposEdicao() {
        visaoEditarContasPagar.getjTextFieldFornecedor().setBackground(Color.white);
        visaoEditarContasPagar.getjTextFieldDescricao().setBackground(Color.white);
        visaoEditarContasPagar.getjTextFieldValor().setBackground(Color.white);
        visaoEditarContasPagar.getjFormattedTextFieldDataVencimento().setBackground(Color.white);
        visaoEditarContasPagar.getjFormattedTextFieldDatadaBaixa().setBackground(Color.white);
       
    }

    
    private void evtBotaoCancelarEdicao() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a edição de contas? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoEditarContasPagar.dispose();
                }
            }

        };
        visaoEditarContasPagar.getBtnCancelar().addActionListener(actionListener);
    }
    
//------------------Tela Baixar Contas----------
private void evtBotaoBaixarSalvar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoGerenciarContasPagar.getTblContasPagar().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Conta selecionada!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirmar Liquidação de Conta "
                            + visaoGerenciarContasPagar.getTblContasPagar().getValueAt(linha, 1)
                            + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoGerenciarContasPagar.getTblContasPagar().getValueAt(linha, 0); //ID do item selecionado
                        Date baixa = Datas.stringToData(visaoBaixarContasPagar.getjFormattedTextFieldDatadeEntrada().getText());
                        ContasPagar contaLiquidada = contasPagarDao.buscarPorId(id);
                        contaLiquidada.setBaixa(baixa);
                        contaLiquidada.setStatus("LIQUIDADA");
                        contasPagarDao.alterar(contaLiquidada);
                        JOptionPane.showMessageDialog(null, "Conta Liquidada com Sucesso!", "Sucesso", 1);
                        preencheTabela(contasPagarDao.buscarTodos(), visaoGerenciarContasPagar.getTblContasPagar()); //Atualiza tabela após remoção
                        visaoBaixarContasPagar.dispose();
                    }
                }
            }
        };
        visaoBaixarContasPagar.getjSalvar().addActionListener(actionListener);
    }

    private void evtBotaoCancelarBaixar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a baixa de contas? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoBaixarContasPagar.dispose();
                }
            }

        };
        visaoBaixarContasPagar.getjCancelar().addActionListener(actionListener);
    }

//----------------Data para String--------------
    public String stringData(Date data) {
        String datas = null;
        try {
            datas = formatoData.format(data);
        } catch (Exception e) {

        }
        return datas;
    }





}
