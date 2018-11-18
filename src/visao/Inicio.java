/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import controle.ConsultoraControle;
import controle.InicioControle;
import javax.swing.JLabel;
import javax.swing.JTextField;
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

        jTextFieldCódigo = new javax.swing.JTextField();
        jTextFieldUsuario = new javax.swing.JTextField();
        jTextFieldTipo = new javax.swing.JTextField();
        lblAtelie = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuProdutos = new javax.swing.JMenuItem();
        menuConsultoras = new javax.swing.JMenuItem();
        menuSacolas = new javax.swing.JMenuItem();
        menuUsuario = new javax.swing.JMenuItem();
        menuUsuario1 = new javax.swing.JMenuItem();
        jMenu2Financeiro = new javax.swing.JMenu();
        jMenuContasPagar = new javax.swing.JMenuItem();
        jMenuContasReceber = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        menuFrequencia = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ateliê Doce Beleza");

        jTextFieldCódigo.setToolTipText("Código do Usuário");
        jTextFieldCódigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCódigoActionPerformed(evt);
            }
        });

        jTextFieldUsuario.setToolTipText("Nome do Usuário");

        jTextFieldTipo.setToolTipText("Tipo de Permissão de usuário");
        jTextFieldTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTipoActionPerformed(evt);
            }
        });

        lblAtelie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblAtelie.setForeground(new java.awt.Color(102, 102, 102));
        lblAtelie.setText("Sistema Ateliê Doce Beleza");

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

        menuUsuario.setText("Usuários");
        menuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(menuUsuario);

        menuUsuario1.setText("Notificacao");
        menuUsuario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUsuario1ActionPerformed(evt);
            }
        });
        jMenu1.add(menuUsuario1);

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

        menuRelatorios.setText("Relatórios");

        menuFrequencia.setText("Frequência");
        menuFrequencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFrequenciaActionPerformed(evt);
            }
        });
        menuRelatorios.add(menuFrequencia);

        jMenuBar1.add(menuRelatorios);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 278, Short.MAX_VALUE)
                        .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jTextFieldCódigo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAtelie)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCódigo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addComponent(lblAtelie)
                .addContainerGap())
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

    private void menuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuarioActionPerformed
        inicioControle.abreView(Tela.USUARIO);
    }//GEN-LAST:event_menuUsuarioActionPerformed

    private void jTextFieldCódigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCódigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCódigoActionPerformed

    private void jTextFieldTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTipoActionPerformed
    private void menuUsuario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUsuario1ActionPerformed
        inicioControle.abreView(Tela.NOTIFICACAO);
    }//GEN-LAST:event_menuUsuario1ActionPerformed

    private void menuFrequenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFrequenciaActionPerformed
        inicioControle.abreView(Tela.FREQUENCIA);
    }//GEN-LAST:event_menuFrequenciaActionPerformed

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
    private javax.swing.JTextField jTextFieldCódigo;
    private javax.swing.JTextField jTextFieldTipo;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JLabel lblAtelie;
    private javax.swing.JMenuItem menuConsultoras;
    private javax.swing.JMenuItem menuFrequencia;
    private javax.swing.JMenuItem menuProdutos;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenuItem menuSacolas;
    private javax.swing.JMenuItem menuUsuario;
    private javax.swing.JMenuItem menuUsuario1;
    // End of variables declaration//GEN-END:variables
//----------------Getters and Setters------------------------------------------------------------------
    public JTextField getjTextFieldCódigo() {
        return jTextFieldCódigo;
    }

    public void setjTextFieldCódigo(JTextField jTextFieldCódigo) {
        this.jTextFieldCódigo = jTextFieldCódigo;
    }

    public JTextField getjTextFieldTipo() {
        return jTextFieldTipo;
    }

    public void setjTextFieldTipo(JTextField jTextFieldTipo) {
        this.jTextFieldTipo = jTextFieldTipo;
    }

    public JTextField getjTextFieldUsuario() {
        return jTextFieldUsuario;
    }

    public void setjTextFieldUsuario(JTextField jTextFieldUsuario) {
        this.jTextFieldUsuario = jTextFieldUsuario;
    }
       
    
   
}


