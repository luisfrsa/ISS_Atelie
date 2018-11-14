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
import static util.Documentos.validaCPF;
import visao.Inicio;
import visao.login.FormLogin;
import visao.login.FormRecuperarSenha;

/**
 *
 * @author Ronnie
 */
public class LoginControle {

    private static final UsuarioDao usuarioDAO = new UsuarioDao();
    private static final FormLogin visaoLogin = new FormLogin();
    private static final FormRecuperarSenha visaoRecuperaSenha = new FormRecuperarSenha();
    private static final Inicio inicio = new Inicio();
    
    
    private boolean ouvirVisaoLogin = true;
    private boolean ouvirVisaoRecuperaSenha=true;
    private ActionListener actionListener;

    public void renderizarVisaoLogin() {
        if (ouvirVisaoLogin) {
            evtBotaoAcessar();
            evtRecuperaSenha();
            ouvirVisaoLogin = false;
        }

        visaoLogin.setVisible(true);
    }

    
     public void renderizarVisaoRecuperaSenha() {
        if (ouvirVisaoRecuperaSenha) {
            evtBotaoBusca();
            evtBotaoValidar();
            ouvirVisaoRecuperaSenha = false;
        }

        visaoRecuperaSenha.setVisible(true);
        visaoRecuperaSenha.getjTextFieldID().setEnabled(false);
        visaoRecuperaSenha.getjTextFieldLembrete().setEnabled(false);
        visaoRecuperaSenha.getjTextFieldUsuario().setEnabled(false);
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

    private void evtRecuperaSenha() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                renderizarVisaoRecuperaSenha();
            }
        };

        visaoLogin.getjButtonRecuperaSenha().addActionListener(actionListener);
    }


//----------------------- Tela Recuperação de senha-------------------------------------------
 private void evtBotaoBusca() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                buscarCpf();
            }
        };

        visaoRecuperaSenha.getBtmBuscar().addActionListener(actionListener);
    }
    
    private void evtBotaoValidar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                validaPergunta();
            }
        };

        visaoRecuperaSenha.getjButtonValidar().addActionListener(actionListener);
    }
 
    public void buscarCpf() {
        String cpf = visaoRecuperaSenha.getjTextFieldCPF().getText();
        restauraCorCamposCPF();
        
        if (validaCpf(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF validado com sucesso", "Sucesso", 1);
        }
    }

    private boolean validaCpf(String cpf) {
        restauraCorCamposCPF();
        Usuario usuario = buscaPorCpf(cpf);
        
        
        if (cpf.equals("")) {
            JOptionPane.showMessageDialog(null, "O campo CPF está vazio", "Erro na Validação", 0);
            visaoRecuperaSenha.getjTextFieldCPF().requestFocus();
            visaoRecuperaSenha.getjTextFieldCPF().setBackground(Color.yellow);

            return false;
        }
        
        if (!validaCPF(cpf)) {
            JOptionPane.showMessageDialog(null, "CPF inválido", "Erro na Validação", 0);
            visaoRecuperaSenha.getjTextFieldCPF().requestFocus();
            visaoRecuperaSenha.getjTextFieldCPF().setBackground(Color.yellow);
            return false;
        }
        
        
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Não existe usuário com esse CPF!, Digite os números na forma: XXXXXXXXXXX, sem ponto e vírgula", "Erro na Validação", 0);
            visaoRecuperaSenha.getjTextFieldCPF().requestFocus();
            visaoRecuperaSenha.getjTextFieldCPF().setBackground(Color.yellow);

            return false;
        }

        visaoRecuperaSenha.getjTextFieldCPF().setText(usuario.getCpf());
        visaoRecuperaSenha.getjTextFieldUsuario().setText(usuario.getUsuario());
        visaoRecuperaSenha.getjTextFieldID().setText(usuario.getId().toString());
        visaoRecuperaSenha.getjTextFieldLembrete().setText(usuario.getLembrete());
        return true;
    }

    public Usuario buscaPorCpf(String cpf) {
        int tamanho = usuarioDAO.buscarTodos().size();
        List<Usuario> listaAux = usuarioDAO.buscarTodos();
        Usuario user = new Usuario();
        for (int k = 0; k < tamanho; k++) {
            if (listaAux.get(k).getCpf().equals(cpf)) {
                user = listaAux.get(k);
                return user;
            }
        }
        return null;
    }

    private void restauraCorCamposCPF() {
        visaoRecuperaSenha.getjTextFieldCPF().setBackground(Color.white);
        visaoRecuperaSenha.getjTextFieldCPF().setBackground(Color.white);

    }

    public void validaPergunta() {
        Integer id = Integer.parseInt(visaoRecuperaSenha.getjTextFieldID().getText());
        String resposta = visaoRecuperaSenha.getjPasswordResposta1().getText();

        Usuario usuario = usuarioDAO.buscarPorId(id);

        if (validaRecuperacaoPergunta(usuario, resposta)) {
            JOptionPane.showMessageDialog(null, "Validação concluída com sucesso! A senha do usuario  " + usuario.getUsuario() + "  é  " +usuario.getSenha(), "Sucesso", 1);
            limparCamposRecuperacao();
            visaoRecuperaSenha.dispose();
        }
    }

    public boolean validaRecuperacaoPergunta(Usuario usuario, String resposta) {
        restauraCorRecuperacao();
        
        if (resposta.equals("")) {
            JOptionPane.showMessageDialog(null, "A resposta está vazia", "Erro na Validação", 0);
            visaoRecuperaSenha.getjPasswordResposta1().requestFocus();
            visaoRecuperaSenha.getjPasswordResposta1().setBackground(Color.yellow);

            return false;
        }

        if (!usuario.getResposta().equals(resposta)) {
            JOptionPane.showMessageDialog(null, "Resposta está incorreta", "Erro na Validação", 0);
            visaoRecuperaSenha.getjPasswordResposta1().requestFocus();
            visaoRecuperaSenha.getjPasswordResposta1().setBackground(Color.yellow);
            return false;
        }

        return true;
    }


     private void restauraCorRecuperacao() {
        visaoRecuperaSenha.getjTextFieldCPF().setBackground(Color.white);
        visaoRecuperaSenha.getjPasswordResposta1().setBackground(Color.white);
        
    }

   private void limparCamposRecuperacao() {
        visaoRecuperaSenha.getjTextFieldCPF().setText("");
        visaoRecuperaSenha.getjTextFieldUsuario().setText("");
        visaoRecuperaSenha.getjTextFieldID().setText("");
        visaoRecuperaSenha.getjTextFieldLembrete().setText("");
        visaoRecuperaSenha.getjPasswordResposta1().setText("");
   }




}
    
   
    
    
    
    
    
    
    
    
    

