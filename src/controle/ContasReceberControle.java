/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ContasReceberDAO;
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
import modelo.ContasReceber;
import modelo.builder.ContasReceberBuilder;
import util.Datas;
import static util.Datas.formatoData;
import visao.contasreceber.FormBaixarContasReceber;
import visao.contasreceber.FormCadastrarContasReceber;
import visao.contasreceber.FormEditarContasContasReceber;
import visao.contasreceber.FormGerenciarContasReceber;

/**
 *
 * @author Ronnie
 */
public class ContasReceberControle {

    private static final ContasReceberDAO contasReceberDao = new ContasReceberDAO();
    private static final FormGerenciarContasReceber visaoGerenciarContasReceber = new FormGerenciarContasReceber();
    private static final FormCadastrarContasReceber visaoCadastrarContasReceber = new FormCadastrarContasReceber();
    private static final FormEditarContasContasReceber visaoEditarContasReceber = new FormEditarContasContasReceber();
    private static final FormBaixarContasReceber visaoBaixarContasReceber = new FormBaixarContasReceber();

    private boolean ouvirGerenciarContasReceber = true;
    private boolean ouvirCadastrarContasReceber = true;
    private boolean ouvirEditarContasReceber = true;
    private boolean ouvirBaixarContasReceber = true;

    private ActionListener actionListener;

    public void renderizarVisaoGerenciarContasReceber() {
        if (ouvirGerenciarContasReceber) {
            evtBotaoCadastrar();
            evtBotaoMaisDetalhes();
            evtBotaoBaixarConta();
            evtBotaoBuscar();
            evtBotaoExcluir();
            ouvirGerenciarContasReceber = false;
        }
        preencheTabela(contasReceberDao.buscarTodos(), visaoGerenciarContasReceber.getTblContasReceber());
        visaoGerenciarContasReceber.setVisible(true);
    }

    public void renderizarVisaoCadastrarContasReceber() {
        if (ouvirCadastrarContasReceber) {
            evtBotaoNovoCadastrar();
            evtBotaoCancelarCadastro();
            ouvirCadastrarContasReceber = false;
        }
        visaoCadastrarContasReceber.setVisible(true);
    }

    public void renderizarVisaoEditarContasReceber() {
        if (ouvirEditarContasReceber) {
            evtBotaoSalvar();
            evtBotaoCancelarEdicao();
            ouvirEditarContasReceber = false;
        }
        visaoEditarContasReceber.setVisible(true);
    }

    public void renderizarVisaoBaixarContasReceber() {
        if (ouvirBaixarContasReceber) {
            evtBotaoCancelarBaixar();
            evtBotaoBaixarSalvar();
            ouvirBaixarContasReceber = false;
        }
       visaoBaixarContasReceber.setVisible(true);
    }
    //------------------------Tela GerenciarContasPagar-----------
    private void preencheTabela(List<ContasReceber> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (ContasReceber contasReceber : lista) {

            modelo.addRow(new Object[]{
                contasReceber.getId(),
                contasReceber.getCliente(),
                contasReceber.getValor(),
                contasReceber.getStatus()
            });

        }
    }

    private void evtBotaoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoCadastrarContasReceber();
            }
        };
        visaoGerenciarContasReceber.getBtnCadastrar().addActionListener(actionListener);
    }

    private void evtBotaoMaisDetalhes() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int linha = visaoGerenciarContasReceber.getTblContasReceber().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Conta Selecionada!", "Erro", 0);
                } else {
                    Integer id = (Integer) visaoGerenciarContasReceber.getTblContasReceber().getValueAt(linha, 0); //ID do item selecionado
                    ContasReceber contasReceberSelecionada = contasReceberDao.buscarPorId(id);
                    //Preenche os campos do form editar
                    visaoEditarContasReceber.getjTextFieldStatus().setText(contasReceberSelecionada.getStatus());
                    visaoEditarContasReceber.getJTextFieldID().setText(contasReceberSelecionada.getId().toString());
                    visaoEditarContasReceber.getjTextFieldCliente().setText(contasReceberSelecionada.getCliente());
                    visaoEditarContasReceber.getjTextFieldDescricao().setText(contasReceberSelecionada.getDescricao());
                    visaoEditarContasReceber.getjFormattedTextFieldDataVencimento().setText(stringData(contasReceberSelecionada.getVencimento()));
                    visaoEditarContasReceber.getjFormattedTextFieldDatadaBaixa().setText(stringData(contasReceberSelecionada.getBaixa()));
                    visaoEditarContasReceber.getjTextFieldValor().setText(Double.toString(contasReceberSelecionada.getValor()));
                    visaoEditarContasReceber.getjTextFieldStatus().setEditable(false);
                    visaoEditarContasReceber.getJTextFieldID().setEditable(false);
                    renderizarVisaoEditarContasReceber();
                }

            }
        };
        visaoGerenciarContasReceber.getBtnDetalhes().addActionListener(actionListener);
    }

    private void evtBotaoExcluir() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoGerenciarContasReceber.getTblContasReceber().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Conta selecionada!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirmar a exclusão da Conta "
                            + visaoGerenciarContasReceber.getTblContasReceber().getValueAt(linha, 1)
                            + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoGerenciarContasReceber.getTblContasReceber().getValueAt(linha, 0); //ID do item selecionado
                        ContasReceber contaRemovida = contasReceberDao.buscarPorId(id);
                        contaRemovida.setStatus("REMOVIDA");
                        contasReceberDao.alterar(contaRemovida);
                        JOptionPane.showMessageDialog(null, "Conta Excluida com Sucesso!", "Sucesso", 1);
                        preencheTabela(contasReceberDao.buscarTodos(), visaoGerenciarContasReceber.getTblContasReceber()); //Atualiza tabela após remoção
                    }
                }
            }
        };
        visaoGerenciarContasReceber.getBtnExcluir().addActionListener(actionListener);
    }

    private void evtBotaoBuscar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String busca = visaoGerenciarContasReceber.getTxtCliente().getText();
                if (busca.equals("")) {
                    preencheTabela(contasReceberDao.buscarTodos(), visaoGerenciarContasReceber.getTblContasReceber());
                } else {
                    List<ContasReceber> listaDeBusca = buscaPorCliente(busca);
                    preencheTabela(listaDeBusca, visaoGerenciarContasReceber.getTblContasReceber());
                }
            }
        };
        visaoGerenciarContasReceber.getBtnBuscar().addActionListener(actionListener);

    }
    
    public List<ContasReceber> buscaPorCliente(String busca) {

        List<ContasReceber> listaDeBusca = new ArrayList<>();
        int tamanho = contasReceberDao.buscarTodos().size();
        List <ContasReceber>listaAux = contasReceberDao.buscarTodos();
        for (int k = 0; k < tamanho; k++) {
            if (busca.toLowerCase().contains(listaAux.get(k).getCliente().toLowerCase())){
                listaDeBusca.add(listaAux.get(k));
            }
        }
        return listaDeBusca;
    }

    private void evtBotaoBaixarConta() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoBaixarContasReceber();
            }
        };
        visaoGerenciarContasReceber.getBtnBaixar().addActionListener(actionListener);
    }



//-------------------Tela CadastrarContasPagar-----------------------
    private void evtBotaoNovoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                //Obtendo os dados inseridos na visão
                String descricao = visaoCadastrarContasReceber.getjTextFieldDescricao().getText();

                Double valor;
                try {
                    valor = Double.parseDouble(visaoCadastrarContasReceber.getjTextFieldValor().getText());
                } catch (NumberFormatException e) {
                    valor = null;
                }

                int parcelas;
                try {
                    parcelas = Integer.parseInt(visaoCadastrarContasReceber.getjTextFieldNumeroParcelas().getText());
                } catch (NumberFormatException e) {
                    parcelas = 0;
                }

                Double valorparcela;
                 try {
                    valorparcela = Double.parseDouble(visaoCadastrarContasReceber.getjTextFieldValordaParcela().getText());
                } catch (NumberFormatException e) {
                    valorparcela= null;
                }
                
                
                String cliente = visaoCadastrarContasReceber.getjTextFieldCliente().getText();
                String formaDePagamento = (String) visaoCadastrarContasReceber.getjComboBoxFormaPagamento().getSelectedItem();
                Date entrada = Datas.stringToData(visaoCadastrarContasReceber.getjFormattedTextFieldDataEntrada().getText());

                /*
                ContasPagar contaspagar = new ContasPagarBuilder(descricao)
                        .setEntrada(entrada)
                        .setFormaPagamento(formaDePagamento)
                        .setFornecedor(fornecedor)
                        .setValor(valor)
                        .build();
                
                contasPagarDao.inserir(contaspagar);
                 */
                ContasReceber contasReceber = new ContasReceber();
                contasReceber.setEntrada(entrada);
                contasReceber.setFormaPagamento(formaDePagamento);
                contasReceber.setCliente(cliente);
                contasReceber.setValor(valor);
                contasReceber.setDescricao(descricao);
                contasReceber.setStatus("PENDENTE");

                if (validaCadastroContasPagar(contasReceber)) {
                    Calendar vencimentoparcelas = Calendar.getInstance();//variáveL que calcula vencimento das parcelas
                    vencimentoparcelas.setTime(entrada);
                    if (parcelas > 0 && contasReceber.getFormaPagamento() == "A Prazo") {
                        for (int i = 0; i < parcelas; i++) {
                            
                            contasReceber.setEntrada(entrada);
                            contasReceber.setFormaPagamento(formaDePagamento);
                            contasReceber.setCliente(cliente);
                            contasReceber.setValor(valorparcela);
                            contasReceber.setDescricao(descricao);
                            contasReceber.setStatus("PENDENTE");
                            
                           // -----Calculo do Vencimento (parcela)
                            vencimentoparcelas.add(Calendar.DAY_OF_MONTH, +30);
                            contasReceber.setVencimento(vencimentoparcelas.getTime());
                            contasReceberDao.inserir(contasReceber);
                            contasReceber = new ContasReceber();
                        }
                    } else {
                        
                        contasReceber.setVencimento(entrada);
                        contasReceber.setBaixa(entrada);
                        contasReceber.setStatus("LIQUIDADA");
                        contasReceberDao.inserir(contasReceber);
                    }
                    JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!", "Sucesso", 1);
                    preencheTabela(contasReceberDao.buscarTodos(), visaoGerenciarContasReceber.getTblContasReceber()); //Atualiza tabela após cadastro
                    
                    limparCamposCadastro();
                    visaoCadastrarContasReceber.dispose();
                }

            }

        };

        visaoCadastrarContasReceber.getBtnCadastrar().addActionListener(actionListener);
    }

    private boolean validaCadastroContasPagar(ContasReceber contasReceber) {
        restauraCorCamposCadastro();
        if (contasReceber.getFormaPagamento()== "A Prazo" && visaoCadastrarContasReceber.getjTextFieldNumeroParcelas().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Número de parcelas' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarContasReceber.getjTextFieldNumeroParcelas().requestFocus();
            visaoCadastrarContasReceber.getjTextFieldNumeroParcelas().setBackground(Color.yellow);
            return false;
        }
        if (visaoCadastrarContasReceber.getjComboBoxFormaPagamento().getSelectedItem().toString().equals("A Prazo") && visaoCadastrarContasReceber.getjTextFieldValordaParcela().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor da Parcela ' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarContasReceber.getjTextFieldValordaParcela().requestFocus();
            visaoCadastrarContasReceber.getjTextFieldValordaParcela().setBackground(Color.yellow);
            return false;
        }
        
        if (contasReceber.getEntrada() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Data da entrada' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarContasReceber.getjFormattedTextFieldDataEntrada().requestFocus();
            visaoCadastrarContasReceber.getjFormattedTextFieldDataEntrada().setBackground(Color.yellow);
            return false;
        }
        if (contasReceber.getCliente().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Cliente' é obrigatório!", "Erro na Validação", 0);
            visaoCadastrarContasReceber.getjTextFieldCliente().requestFocus();
            visaoCadastrarContasReceber.getjTextFieldCliente().setBackground(Color.yellow);
            return false;
        }

        if (contasReceber.getValor() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor' é obrigatório!"
                    + "\nPermitidos apenas números inteiros ou reais.", "Erro na Validação", 0);
            visaoCadastrarContasReceber.getjTextFieldValor().requestFocus();
            visaoCadastrarContasReceber.getjTextFieldValor().setBackground(Color.yellow);
            return false;
        }

        if (contasReceber.getValor() <= 0) {
            JOptionPane.showMessageDialog(null, "O Valor de uma Conta deve ser maior que zero!", "Erro na Validação", 0);
            visaoCadastrarContasReceber.getjTextFieldValor().requestFocus();
            visaoCadastrarContasReceber.getjTextFieldValor().setBackground(Color.yellow);
            return false;
        }

        return true;
    }

    private void restauraCorCamposCadastro() {
        visaoCadastrarContasReceber.getjTextFieldCliente().setBackground(Color.white);
        visaoCadastrarContasReceber.getjTextFieldDescricao().setBackground(Color.white);
        visaoCadastrarContasReceber.getjTextFieldValor().setBackground(Color.white);
        visaoCadastrarContasReceber.getjFormattedTextFieldDataEntrada().setBackground(Color.white);
        visaoCadastrarContasReceber.getjTextFieldNumeroParcelas().setBackground(Color.white);
        visaoCadastrarContasReceber.getjTextFieldValordaParcela().setBackground(Color.white);
    }

    private void limparCamposCadastro() {
        visaoCadastrarContasReceber.getjTextFieldCliente().setText("");
        visaoCadastrarContasReceber.getjTextFieldDescricao().setText("");
        visaoCadastrarContasReceber.getjTextFieldValor().setText("");
        visaoCadastrarContasReceber.getjFormattedTextFieldDataEntrada().setText("");
        visaoCadastrarContasReceber.getjTextFieldNumeroParcelas().setText("");
        visaoCadastrarContasReceber.getjTextFieldValordaParcela().setText("");
    }
    private void evtBotaoCancelarCadastro() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro de contas? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoCadastrarContasReceber.dispose();
                }
            }

        };
        visaoCadastrarContasReceber.getBtnCancelar().addActionListener(actionListener);
    }
//--------------------Tela EditarContas a Pagar------------------------------------
    private void evtBotaoSalvar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //coletando dados da  visaoeditar
                Integer id = Integer.parseInt(visaoEditarContasReceber.getJTextFieldID().getText());
                String cliente = visaoEditarContasReceber.getjTextFieldCliente().getText();
                String descricao = visaoEditarContasReceber.getjTextFieldDescricao().getText();

                 Double valor;
                try {
                    valor = Double.parseDouble(visaoEditarContasReceber.getjTextFieldValor().getText());
                } catch (Exception e) {
                    valor = null;
                }
           
               Date datavencimento = Datas.stringToData(visaoEditarContasReceber.getjFormattedTextFieldDataVencimento().getText());
               Date databaixa =  Datas.stringToData(visaoEditarContasReceber.getjFormattedTextFieldDatadaBaixa().getText());

               ContasReceber contanova = contasReceberDao.buscarPorId(id);
               
               contanova.setCliente(cliente);
               contanova.setDescricao(descricao);
               contanova.setValor(valor);
               contanova.setVencimento(datavencimento);
               contanova.setBaixa(databaixa);
                if(contanova.getBaixa()!=null){
                    contanova.setStatus("LIQUIDADA");
                }
                if (validarContasEdicao(contanova)) {
                    contasReceberDao.alterar(contanova);
                    JOptionPane.showMessageDialog(null, "Conta Editada com Sucesso!", "Sucesso", 1);
                    visaoEditarContasReceber.dispose();
                    preencheTabela(contasReceberDao.buscarTodos() , visaoGerenciarContasReceber.getTblContasReceber());
                }
            }
        };
        visaoEditarContasReceber.getBtnSalvar().addActionListener(actionListener);
    }
               
               
               
               
               
    private boolean validarContasEdicao(ContasReceber contas) {
        restauraCorCamposEdicao();
        if (contas.getCliente().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Cliente' é obrigatório!", "Erro na Validação", 0);
            visaoEditarContasReceber.getjTextFieldCliente().requestFocus();
            visaoEditarContasReceber.getjTextFieldCliente().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getDescricao().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório!", "Erro na Validação", 0);
            visaoEditarContasReceber.getjTextFieldDescricao().requestFocus();
            visaoEditarContasReceber.getjTextFieldDescricao().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getValor() == null) {
            JOptionPane.showMessageDialog(null, "O campo 'Valor' é obrigatório!"
                    + "\nPermitidos apenas números inteiros ou reais.", "Erro na Validação", 0);
            visaoEditarContasReceber.getjTextFieldValor().requestFocus();
            visaoEditarContasReceber.getjTextFieldValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getValor() <= 0) {
            JOptionPane.showMessageDialog(null, "O Valor de um Produto deve ser maior que zero!", "Erro na Validação", 0);
            visaoEditarContasReceber.getjTextFieldValor().requestFocus();
            visaoEditarContasReceber.getjTextFieldValor().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        if (contas.getVencimento() == null) {
            JOptionPane.showMessageDialog(null, "Data de vencimento incompleta ou inexistente", "Erro na Validação", 0);
            visaoEditarContasReceber.getjFormattedTextFieldDataVencimento().requestFocus();
            visaoEditarContasReceber.getjFormattedTextFieldDataVencimento().setBackground(new java.awt.Color(255, 204, 204));
            return false;
        }

        return true;
    }

    private void restauraCorCamposEdicao() {
        visaoEditarContasReceber.getjTextFieldCliente().setBackground(Color.white);
        visaoEditarContasReceber.getjTextFieldDescricao().setBackground(Color.white);
        visaoEditarContasReceber.getjTextFieldValor().setBackground(Color.white);
        visaoEditarContasReceber.getjFormattedTextFieldDataVencimento().setBackground(Color.white);
        visaoEditarContasReceber.getjFormattedTextFieldDatadaBaixa().setBackground(Color.white);
       
    }

    
    private void evtBotaoCancelarEdicao() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a edição de contas? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoEditarContasReceber.dispose();
                }
            }

        };
        visaoEditarContasReceber.getBtnCancelar().addActionListener(actionListener);
    }
    
//------------------Tela Baixar Contas----------
private void evtBotaoBaixarSalvar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int linha = visaoGerenciarContasReceber.getTblContasReceber().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhuma Conta selecionada!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirmar Liquidação de Conta "
                            + visaoGerenciarContasReceber.getTblContasReceber().getValueAt(linha, 1)
                            + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoGerenciarContasReceber.getTblContasReceber().getValueAt(linha, 0); //ID do item selecionado
                        Date baixa = Datas.stringToData(visaoBaixarContasReceber.getjFormattedTextFieldDatadeEntrada().getText());
                        ContasReceber contaLiquidada = contasReceberDao.buscarPorId(id);
                        contaLiquidada.setBaixa(baixa);
                        contaLiquidada.setStatus("LIQUIDADA");
                        contasReceberDao.alterar(contaLiquidada);
                        JOptionPane.showMessageDialog(null, "Conta Liquidada com Sucesso!", "Sucesso", 1);
                        preencheTabela(contasReceberDao.buscarTodos(), visaoGerenciarContasReceber.getTblContasReceber()); //Atualiza tabela após remoção
                        visaoBaixarContasReceber.dispose();
                    }
                }
            }
        };
        visaoBaixarContasReceber.getjSalvar().addActionListener(actionListener);
    }

    private void evtBotaoCancelarBaixar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a baixa de contas? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoBaixarContasReceber.dispose();
                }
            }

        };
        visaoBaixarContasReceber.getjCancelar().addActionListener(actionListener);
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
