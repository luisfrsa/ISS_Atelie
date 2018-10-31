/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.usuario;

import visao.contaspagar.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Ronnie
 */
public class FormEditarUsuario extends javax.swing.JFrame {

    /**
     * Creates new form FormContasPagar
     */
    public FormEditarUsuario() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNome = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelCadastrarContasPagar = new javax.swing.JLabel();
        jLabelUsuario = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelSenha = new javax.swing.JLabel();
        jLabelSenha1 = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabelCargo = new javax.swing.JLabel();
        jLabelID = new javax.swing.JLabel();
        JTextFieldID = new javax.swing.JTextField();
        jLabelStatus = new javax.swing.JLabel();
        jTextFieldStatus = new javax.swing.JTextField();
        jPasswordSenha = new javax.swing.JPasswordField();
        jPasswordSenha1 = new javax.swing.JPasswordField();
        jTextFieldCargo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Contas a Pagar");

        jLabelNome.setText("Nome:");

        jLabelCadastrarContasPagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCadastrarContasPagar.setText("Editar Contas a Pagar");

        jLabelUsuario.setText("Usuário:");

        jLabelSenha.setText("Senha:");

        jLabelSenha1.setText("RepetirSenha:");

        btnSalvar.setBackground(new java.awt.Color(0, 153, 102));
        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setText("Cancelar");

        jLabelCargo.setText("Cargo:");

        jLabelID.setText("Id:");

        JTextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTextFieldIDActionPerformed(evt);
            }
        });

        jLabelStatus.setText("Status: ");

        jTextFieldStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelCadastrarContasPagar)
                        .addGap(0, 315, Short.MAX_VALUE))
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addComponent(btnCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelSenha)
                                    .addComponent(jLabelCargo)
                                    .addComponent(jLabelUsuario)
                                    .addComponent(jLabelSenha1)
                                    .addComponent(jLabelNome)
                                    .addComponent(jLabelID)
                                    .addComponent(jLabelStatus))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addComponent(jTextFieldNome)
                                    .addComponent(JTextFieldID)
                                    .addComponent(jTextFieldStatus)
                                    .addComponent(jPasswordSenha)
                                    .addComponent(jPasswordSenha1)
                                    .addComponent(jTextFieldCargo, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))))
                        .addGap(164, 164, 164)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabelCadastrarContasPagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelID)
                    .addComponent(JTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNome)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSenha)
                    .addComponent(jPasswordSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSenha1)
                    .addComponent(jPasswordSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCargo)
                    .addComponent(jTextFieldCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar))
                .addGap(22, 22, 22))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void JTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTextFieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTextFieldIDActionPerformed

    private void jTextFieldStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStatusActionPerformed

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
            java.util.logging.Logger.getLogger(FormEditarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormEditarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormEditarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormEditarUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormEditarUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextFieldID;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabelCadastrarContasPagar;
    private javax.swing.JLabel jLabelCargo;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelSenha;
    private javax.swing.JLabel jLabelSenha1;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordSenha;
    private javax.swing.JPasswordField jPasswordSenha1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldCargo;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldStatus;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables

    public JTextField getJTextFieldID() {
        return JTextFieldID;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JPasswordField getjPasswordSenha() {
        return jPasswordSenha;
    }

    public JPasswordField getjPasswordSenha1() {
        return jPasswordSenha1;
    }

    public JTextField getjTextFieldCargo() {
        return jTextFieldCargo;
    }

    public JTextField getjTextFieldNome() {
        return jTextFieldNome;
    }

    public JTextField getjTextFieldStatus() {
        return jTextFieldStatus;
    }

    public JTextField getjTextFieldUsuario() {
        return jTextFieldUsuario;
    }

 
    
    
   
   
}