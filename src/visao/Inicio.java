/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ConsultoraControle;
import controle.InicioControle;
import modelo.enums.Tela;

/**
 * @author luis
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private static final InicioControle inicioControle = new InicioControle();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuProdutos = new javax.swing.JMenuItem();
        menuConsultoras = new javax.swing.JMenuItem();
        menuSacolas = new javax.swing.JMenuItem();
        jMenu2Financeiro = new javax.swing.JMenu();
        jMenuContasPagar = new javax.swing.JMenuItem();
        jMenuContasReceber = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ateliê Doce Beleza");

        jMenu1.setText("Menu");

        menuProdutos.setText("Produtos");
        menuProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProdutosActionPerformed(evt);
            }
        });
        jMenu1.add(menuProdutos);

        menuConsultoras.setText("Consultoras");
        menuConsultoras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConsultorasActionPerformed(evt);
            }
        });
        jMenu1.add(menuConsultoras);

        menuSacolas.setText("Sacolas");
        menuSacolas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSacolasActionPerformed(evt);
            }
        });
        jMenu1.add(menuSacolas);

        jMenuBar1.add(jMenu1);

        jMenu2Financeiro.setText("Financeiro");

        jMenuContasPagar.setText("Contas a Pagar");
        jMenuContasPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContasPagarActionPerformed(evt);
            }
        });
        jMenu2Financeiro.add(jMenuContasPagar);

        jMenuContasReceber.setText("Contas a Receber");
        jMenuContasReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContasReceberActionPerformed(evt);
            }
        });
        jMenu2Financeiro.add(jMenuContasReceber);

        jMenuBar1.add(jMenu2Financeiro);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProdutosActionPerformed
        // TODO add your handling code here:
        inicioControle.abreView(Tela.PRODUTO);
    }//GEN-LAST:event_menuProdutosActionPerformed

    private void menuConsultorasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConsultorasActionPerformed
        // TODO add your handling code here:
        inicioControle.abreView(Tela.CONSULTORA);
    }//GEN-LAST:event_menuConsultorasActionPerformed

    private void menuSacolasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSacolasActionPerformed
        // TODO add your handling code here:
        inicioControle.abreView(Tela.SACOLA);
    }//GEN-LAST:event_menuSacolasActionPerformed

    private void jMenuContasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuContasPagarActionPerformed
        inicioControle.abreView(Tela.CONTASPAGAR);
    }//GEN-LAST:event_jMenuContasPagarActionPerformed

    private void jMenuContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuContasReceberActionPerformed
        inicioControle.abreView(Tela.CONTASRECEBER);
    }//GEN-LAST:event_jMenuContasReceberActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2Financeiro;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuContasPagar;
    private javax.swing.JMenuItem jMenuContasReceber;
    private javax.swing.JMenuItem menuConsultoras;
    private javax.swing.JMenuItem menuProdutos;
    private javax.swing.JMenuItem menuSacolas;
    // End of variables declaration//GEN-END:variables
}
