/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import modelo.builder.UsuarioBuilder;
import static util.Documentos.validaCPF;
import visao.usuario.FormCadastrarUsuario;
import visao.usuario.FormGerenciarUsuario;

/**
 *
 * @author Ronnie
 */
public class UsuarioControle {
     private static final UsuarioDao usuarioDAO = new UsuarioDao();
     private static final FormGerenciarUsuario visaoGerenciarUsuario = new FormGerenciarUsuario();
     private static final FormCadastrarUsuario visaoCadastrarUsuario = new FormCadastrarUsuario();
     
     private boolean ouvirGerenciarUsuario = true;
     private boolean ouvirCadastrarUsuario = true;
     private boolean ouvirEditarUsuario = true;
     private boolean ouvirRemoverUsuario = true;

     private ActionListener actionListener;

    public void renderizarVisaoGerenciarUsuario() {
        if (ouvirGerenciarUsuario) {
            evtBotaoCadastrar();
            
            ouvirGerenciarUsuario = false;
        }
        preencheTabela(usuarioDAO.buscarTodos(), visaoGerenciarUsuario.getTblUsuario());
        visaoGerenciarUsuario.setVisible(true);
    }

    public void renderizarVisaoCadastrarUsuario() {
        if (ouvirCadastrarUsuario) {
            evtBotaoNovoCadastrar();
            evtBotaoCancelarCadastro();
            ouvirCadastrarUsuario = false;
        }
        visaoCadastrarUsuario.setVisible(true);
    }
    
    
    
//----------------------------------Tela GerenciarUsuario-----------------------------------------------------------------------------
    private void preencheTabela(List<Usuario> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (Usuario usuario : lista) {

            modelo.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),
                usuario.getCargo(),
                usuario.getStatus()
            });

        }
    }

    private void evtBotaoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoCadastrarUsuario();
            }
        };
        visaoGerenciarUsuario.getBtnCadastrar().addActionListener(actionListener);
    }


//--------------------------------Tela Cadastrar Usuario------------------------------------------------ 
    private void evtBotaoNovoCadastrar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //Obtendo os dados inseridos na visão
                String nome = visaoCadastrarUsuario.getjTextFieldNome().getText();
                String cargo = visaoCadastrarUsuario.getjTextFieldCargo().getText();
                String usuario = visaoCadastrarUsuario.getjTextFieldUsuario().getText();
                String senha = visaoCadastrarUsuario.getjPasswordSenha().getText();
                String senha1 = visaoCadastrarUsuario.getjPasswordSenha1().getText();
                String cpf = visaoCadastrarUsuario.getjTextFieldCPF().getText();

                Usuario usuarioBuilder = new UsuarioBuilder(cpf)
                        .setNome(nome)
                        .setCargo(cargo)
                        .setUsuario(usuario)
                        .setSenha(senha)
                        .setStatus("ATIVO")
                        .build();

                if (validaCadastroUsuario(usuarioBuilder, senha1)) {

                    usuarioDAO.inserir(usuarioBuilder);
                    JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!", "Sucesso", 1);
                    preencheTabela(usuarioDAO.buscarTodos(), visaoGerenciarUsuario.getTblUsuario()); //Atualiza tabela após cadastro
                    visaoCadastrarUsuario.dispose();
                    limparCamposCadastro();
                }

            }

        };

        visaoCadastrarUsuario.getBtnCadastrar().addActionListener(actionListener);
    }

    private boolean validaCadastroUsuario(Usuario usuario, String senha1) {
        restauraCorCamposCadastro();
        int tamanhoSenha = usuario.getSenha().length();
        String usuariovalidacao = buscaPorUsuario(usuario.getUsuario());

        if (usuario.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjTextFieldNome().requestFocus();
            visaoCadastrarUsuario.getjTextFieldNome().setBackground(Color.yellow);
            return false;
        }

        if (usuario.getUsuario().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Usuario' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjTextFieldUsuario().requestFocus();
            visaoCadastrarUsuario.getjTextFieldUsuario().setBackground(Color.yellow);
            return false;
        }

        if (!usuariovalidacao.equals("NAOEXISTE")) {
            JOptionPane.showMessageDialog(null, "O Já existe um usuário com este nome!", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjTextFieldUsuario().requestFocus();
            visaoCadastrarUsuario.getjTextFieldUsuario().setBackground(Color.yellow);
            return false;
        }

        if (tamanhoSenha < 6) {
            JOptionPane.showMessageDialog(null, "O campo 'senha' não pode conter menos que seis caracteres !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjPasswordSenha().requestFocus();
            visaoCadastrarUsuario.getjPasswordSenha().setBackground(Color.yellow);
            return false;
        }

        if (usuario.getSenha().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'senha' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjPasswordSenha().requestFocus();
            visaoCadastrarUsuario.getjPasswordSenha().setBackground(Color.yellow);
            return false;
        }

        if (senha1.equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'repetir senha' não pode ser vazio !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjPasswordSenha1().requestFocus();
            visaoCadastrarUsuario.getjPasswordSenha1().setBackground(Color.yellow);
            return false;
        }

        if (!usuario.getSenha().equals(senha1)) {
            JOptionPane.showMessageDialog(null, "O campo 'Senha' e 'Repetir Senha' não pode ser diferente !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjPasswordSenha().requestFocus();
            visaoCadastrarUsuario.getjPasswordSenha().setBackground(Color.yellow);
            visaoCadastrarUsuario.getjPasswordSenha1().requestFocus();
            visaoCadastrarUsuario.getjPasswordSenha1().setBackground(Color.yellow);
            return false;
        }
        if (!validaCPF(usuario.getCpf())) {
            JOptionPane.showMessageDialog(null, "O Campo 'CPF' não foi validado, digite-o conforme: XXX.XXX.XXX-XX, respeitando traços e pontos, ou na forma XXXXXXXXXXX (sem traços e pontos)!", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjTextFieldCPF().requestFocus();
            visaoCadastrarUsuario.getjTextFieldCPF().setBackground(Color.yellow);

            return false;
        }

        return true;
    }

    private void restauraCorCamposCadastro() {
        visaoCadastrarUsuario.getjTextFieldNome().setBackground(Color.white);
        visaoCadastrarUsuario.getjTextFieldCargo().setBackground(Color.white);
        visaoCadastrarUsuario.getjTextFieldUsuario().setBackground(Color.white);
        visaoCadastrarUsuario.getjPasswordSenha().setBackground(Color.white);
        visaoCadastrarUsuario.getjPasswordSenha1().setBackground(Color.white);
        visaoCadastrarUsuario.getjTextFieldCPF().setBackground(Color.white);
    }

    private void limparCamposCadastro() {
        visaoCadastrarUsuario.getjTextFieldNome().setText("");
        visaoCadastrarUsuario.getjTextFieldCargo().setText("");
        visaoCadastrarUsuario.getjTextFieldUsuario().setText("");
        visaoCadastrarUsuario.getjPasswordSenha().setText("");
        visaoCadastrarUsuario.getjPasswordSenha1().setText("");
        visaoCadastrarUsuario.getjTextFieldCPF().setText("");
    }

    public String buscaPorUsuario(String usuario) {

        List<Usuario> listaDeBusca = new ArrayList<>();
        int tamanho = usuarioDAO.buscarTodos().size();
        List<Usuario> listaAux = usuarioDAO.buscarTodos();
        for (int k = 0; k < tamanho; k++) {
            if (listaAux.get(k).getUsuario().equals(usuario)) {
                return usuario;
            }
        }
        return "NAOEXISTE";
    }

    private void evtBotaoCancelarCadastro() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar o cadastro de Usuário? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoCadastrarUsuario.dispose();
                    restauraCorCamposCadastro();
                    limparCamposCadastro();
                }
            }

        };
        visaoCadastrarUsuario.getBtnCancelar().addActionListener(actionListener);
    }

}
