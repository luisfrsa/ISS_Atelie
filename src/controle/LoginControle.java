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
import modelo.Usuario;
import modelo.builder.UsuarioBuilder;
import visao.Inicio;
import visao.login.FormLogin;

/**
 *
 * @author Ronnie
 */
public class LoginControle {

    private static final UsuarioDao usuarioDAO = new UsuarioDao();
    private static final FormLogin visaoLogin = new FormLogin();
    private static final Inicio inicio = new Inicio();
    
    private boolean ouvirVisaoLogin = true;

    private ActionListener actionListener;

    public void renderizarVisaoLogin() {
        if (ouvirVisaoLogin) {
            evtBotaoAcessar();
            ouvirVisaoLogin = false;
        }

        visaoLogin.setVisible(true);
    }

//  ------------------------------ Tela Login --------------------------------------------  
    private void evtBotaoAcessar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                validarLogin();
            }
        };

        visaoLogin.getBtnAcessar().addActionListener(actionListener);
    }

    public void validarLogin() {
        
       Usuario admBuilder =usuarioDAO.buscarPorId(1); //Criar Administrador 
        if (admBuilder==null){
            admBuilder = new UsuarioBuilder("26898956094")
                .setNome("Administrador")
                .setCargo("Administrador")
                .setUsuario("adm")
                .setSenha("123456")
                .setTipo("Administrador")
                .setStatus("ATIVO")
                .build();
            usuarioDAO.inserir(admBuilder);
        }
   
        String usuario = visaoLogin.getjTextFieldUsuario().getText();
        String senha = visaoLogin.getjPasswordSenha().getText();

        if (validaSenhaLogin(usuario, senha)) {
            JOptionPane.showMessageDialog(null, "Login efetuado com sucesso", "Sucesso", 1);
            Usuario user = buscaPorUsuario(usuario, senha);
            util.DadosUsuario.id=user.getId();
            util.DadosUsuario.tipo=user.getTipo();
            util.DadosUsuario.user=user.getUsuario();
            inicio.getjTextFieldUsuario().setBorder(null);
            inicio.getjTextFieldCódigo().setBorder(null);
            inicio.getjTextFieldTipo().setBorder(null);
            inicio.getjTextFieldUsuario().setText(user.getUsuario());
            inicio.getjTextFieldCódigo().setText(Integer.toString(user.getId()));
            inicio.getjTextFieldTipo().setText(user.getTipo());
            inicio.getjTextFieldUsuario().setEnabled(false);
            inicio.getjTextFieldCódigo().setEnabled(false);
            inicio.getjTextFieldTipo().setEnabled(false);
            inicio.setVisible(true);
            visaoLogin.dispose();
        }

    }


    private boolean validaSenhaLogin(String usuario, String senha) {

        restauraCorCamposCadastro();
        Usuario user = buscaPorUsuario(usuario, senha);

        if (usuario.equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'usuário' não pode ser vazio !", "Erro na Validação", 0);
            visaoLogin.getjTextFieldUsuario().requestFocus();
            visaoLogin.getjTextFieldUsuario().setBackground(Color.yellow);
            return false;
        }

        if (senha.equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'senha' não pode ser vazio !", "Erro na Validação", 0);
            visaoLogin.getjPasswordSenha().requestFocus();
            visaoLogin.getjPasswordSenha().setBackground(Color.yellow);
            return false;
        }
        
        if (!usuario.equals(user.getUsuario())) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválida!", "Erro na Validação", 0);
            visaoLogin.getjTextFieldUsuario().requestFocus();
            visaoLogin.getjTextFieldUsuario().setBackground(Color.yellow);
            return false;
        }
        
        if (!senha.equals(user.getSenha())) {
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválida!", "Erro na Validação", 0);
            visaoLogin.getjPasswordSenha().requestFocus();
            visaoLogin.getjPasswordSenha().setBackground(Color.yellow);
            return false;
        }
       
        if (user.getStatus().equals("DESATIVADO")) {
            JOptionPane.showMessageDialog(null, "O usuário " + user.getUsuario() + " foi desativado do sistema! Contacte o Administrador.", "Erro na Validação", 0);
            return false;
        }
        
        return true;
    }


public Usuario buscaPorUsuario(String usuario, String senha) {

        List<Usuario> listaDeBusca = new ArrayList<>();
        int tamanho = usuarioDAO.buscarTodos().size();
        Usuario user = new Usuario();
        List<Usuario> listaAux = usuarioDAO.buscarTodos();
        for (int k = 0; k < tamanho; k++) {
            if (listaAux.get(k).getUsuario().equals(usuario)&&listaAux.get(k).getSenha().equals(senha)) {
                user=listaAux.get(k);
                return user;
            }
        }
        return user;
    }

    private void restauraCorCamposCadastro() {
        visaoLogin.getjTextFieldUsuario().setBackground(Color.white);
        visaoLogin.getjPasswordSenha().setBackground(Color.white);

    }
}
