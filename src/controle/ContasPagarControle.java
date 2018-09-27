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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.ContasPagar;
import modelo.Produto;
import modelo.builder.ContasPagarBuilder;
import util.Datas;
import visao.contaspagar.FormBaixarContasPagar;
import visao.contaspagar.FormCadastrarContasPagar;
import visao.contaspagar.FormEditarContasPagar;
import visao.contaspagar.FormGerenciarContasPagar;

/**
 *
 * @author Ronnie
 */
public class ContasPagarControle {
    private static ContasPagarDAO contasPagarDao = new ContasPagarDAO();
    private static FormGerenciarContasPagar visaoGerenciarContasPagar = new FormGerenciarContasPagar();
    private static FormCadastrarContasPagar visaoCadastrarContasPagar = new FormCadastrarContasPagar(); 
    private static FormEditarContasPagar visaoEditarContasPagar = new FormEditarContasPagar();
    private static FormBaixarContasPagar visaoBaixarContasPagar = new FormBaixarContasPagar();
  
    private boolean ouvirGerenciarContasPagar = true;
    private boolean ouvirCadastrarContasPagar = true;
    private boolean ouvirEditarContasPagar = true;
    private boolean ouvirBaixarContasPagar = true;
    
    private ActionListener actionListener;
    
    public void renderizarVisaoGerenciarContasPagar() {
      if(ouvirGerenciarContasPagar){
          evtBotaoCadastrar();
          ouvirGerenciarContasPagar=false;
      }
      preencheTabela(contasPagarDao.buscarTodos());
      visaoGerenciarContasPagar.setVisible(true);
    }

    public void renderizarVisaoCadastrarContasPagar() {
        if (ouvirCadastrarContasPagar) {
            evtBotaoNovoCadastrar();
            ouvirCadastrarContasPagar = false;
        }
        visaoCadastrarContasPagar.setVisible(true);
    }

    public void renderizarVisaoEditarContasPagar() {

    }

    public void renderizarVisaoBaixarContasPagar() {

    }

    //Tela GerenciarContasPagar
    private void preencheTabela(List<ContasPagar> lista) {
        DefaultTableModel modelo = (DefaultTableModel) visaoGerenciarContasPagar.getTblContasPagar().getModel();
        modelo.setNumRows(0);
        for (ContasPagar contaspagar : lista) {
            modelo.addRow(new Object[]{
                contaspagar.getId(),
                contaspagar.getFornecedor(),
                contaspagar.getValor()
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

//Tela CadastrarContasPagar
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

                String fornecedor = visaoCadastrarContasPagar.getjTextFieldFornecedor().getText();
                String formaDePagamento = (String) visaoCadastrarContasPagar.getjComboBoxFormaPagamento().getSelectedItem();
                Date entrada = Datas.stringToData(visaoCadastrarContasPagar.getjFormattedTextFieldDataEntrada().getText());
                Date vencimento = entrada;
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
                contaspagar.setStatus("Ativo");

                /*  Calendar c = Calendar.getInstance();
                     c.setTime(vencimento);
                     c.add(Calendar.DATE, +30);
                if (parcelas > 0 && contaspagar.getFormaPagamento() == "A Prazo") {
                    for (int i = 0; i <=parcelas; i++) {
                        contasPagarDao.inserir(contaspagar);
                    }
                }
                 */
                if (validaCadastroContasPagar(contaspagar)) {
                    contasPagarDao.inserir(contaspagar);
                    JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!", "Sucesso", 1);
                    preencheTabela(contasPagarDao.buscarTodos()); //Atualiza tabela após cadastro
                    visaoCadastrarContasPagar.dispose();
                }

            }

        };

        visaoCadastrarContasPagar.getBtnCadastrar().addActionListener(actionListener);
    }

    private boolean validaCadastroContasPagar(ContasPagar contaspagar) {

        if (contaspagar.getFornecedor().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Descrição' é obrigatório!", "Erro na Validação", 0);
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

}
