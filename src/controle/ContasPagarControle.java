/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ContasPagarDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import visao.contaspagar.FormBaixarContasPagar;
import visao.contaspagar.FormCadastrarContasPagar;
import visao.contaspagar.FormEditarContasPagar;
import visao.contaspagar.FormGerenciarContasPagar;

/**
 *
 * @author Ronnie
 */
public class ContasPagarControle {
    private static ContasPagarDAO contaspagarDao = new ContasPagarDAO();
    private static FormGerenciarContasPagar visaoGerenciarContasPagar = new FormGerenciarContasPagar();
    private static FormCadastrarContasPagar visaoCadastrarContasPagar = new FormCadastrarContasPagar(); 
    private static FormEditarContasPagar visaoContasPagar = new FormEditarContasPagar();
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
      visaoGerenciarContasPagar.setVisible(true);
    }

   
    
    public void renderizarVisaoCadastrarContasPagar() {
       
   
   }

    //Tela GerenciarContasPagar
    
        private void preencheTabela(){
        
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

}


