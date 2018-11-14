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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.ContasReceber;
import modelo.Usuario;
import modelo.builder.UsuarioBuilder;
import util.DadosUsuario;
import util.Datas;
import static util.Documentos.validaCPF;
import visao.login.FormLogin;
import visao.usuario.FormCadastrarUsuario;
import visao.usuario.FormEditarUsuario;
import visao.usuario.FormGerenciarUsuario;

/**
 *
 * @author Ronnie
 */
public class UsuarioControle {
     private static final UsuarioDao usuarioDAO = new UsuarioDao();
     private static final FormGerenciarUsuario visaoGerenciarUsuario = new FormGerenciarUsuario();
     private static final FormCadastrarUsuario visaoCadastrarUsuario = new FormCadastrarUsuario();
     private static final FormEditarUsuario visaoEditarUsuario = new FormEditarUsuario();
     
     private boolean ouvirGerenciarUsuario = true;
     private boolean ouvirCadastrarUsuario = true;
     private boolean ouvirEditarUsuario = true;
     private boolean ouvirRemoverUsuario = true;

     private ActionListener actionListener;
     
    
     
    public void renderizarVisaoGerenciarUsuario() {
        if (ouvirGerenciarUsuario) {
            evtBotaoBuscar();
            evtBotaoCadastrar();
            evtBotaoAtivaDesativa();
            evtBotaoMaisDetalhes();
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
    
    public void renderizarVisaoEditarUsuario() {
        if (ouvirEditarUsuario) {
            evtBotaoCancelarEditar();
            evtBotaoSalvar();
            ouvirEditarUsuario = false;
        }
        visaoEditarUsuario.setVisible(true);
    }
    
//----------------------------------Tela GerenciarUsuario-----------------------------------------------------------------------------
    private void preencheTabela(List<Usuario> lista, JTable tabela) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setNumRows(0);
        for (Usuario usuario : lista) {

            modelo.addRow(new Object[]{
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getTipo(),
                usuario.getStatus()
            });

        }
    }

     private void evtBotaoBuscar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String busca = visaoGerenciarUsuario.getTxtUsuario().getText();
                if (busca.equals("")) {
                    preencheTabela(usuarioDAO.buscarTodos(), visaoGerenciarUsuario.getTblUsuario());
                } else {
                    List<Usuario> listaDeBusca = buscaPorUsuarioLista(busca);
                    preencheTabela(listaDeBusca, visaoGerenciarUsuario.getTblUsuario());
                }
            }
        };
        visaoGerenciarUsuario.getBtnBuscar().addActionListener(actionListener);

    }
    
    public List<Usuario> buscaPorUsuarioLista(String usuario) {

        List<Usuario> listaDeBusca = new ArrayList<>();
        int tamanho = usuarioDAO.buscarTodos().size();
        List<Usuario> listaAux = usuarioDAO.buscarTodos();
        
        for (int k = 0; k < tamanho; k++) {
            if (listaAux.get(k).getUsuario().equals(usuario)) {
                listaDeBusca.add(listaAux.get(k));
            }
        }
        return listaDeBusca;
    }
    
    private void evtBotaoCadastrar() {

        Usuario user = usuarioDAO.buscarPorId(DadosUsuario.id);
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (permissao(user)) {
                    renderizarVisaoCadastrarUsuario();

                }
            }
        };
        visaoGerenciarUsuario.getBtnCadastrar().addActionListener(actionListener);
    }

    private boolean permissao(Usuario user) {
        if (user.getTipo().equals("Convidado")) {
            JOptionPane.showMessageDialog(null, "O seu usuário(Convidado) não tem permissão para acessar essa funcionalidade, contacte seu admininistrador!", "Erro na Validação", 0);

            return false;
        }
        return true;
    }

 private void evtBotaoMaisDetalhes() {
        actionListener = new ActionListener() {
            Usuario user = usuarioDAO.buscarPorId(DadosUsuario.id);
            @Override
         public void actionPerformed(ActionEvent ae) {
             if (permissao(user)) {
                 int linha = visaoGerenciarUsuario.getTblUsuario().getSelectedRow(); //Linha selecionada da tabela
                 if (linha < 0) {
                     JOptionPane.showMessageDialog(null, "Nenhum Usuário Selecionado!", "Erro", 0);
                 } else {
                     Integer id = (Integer) visaoGerenciarUsuario.getTblUsuario().getValueAt(linha, 0); //ID do item selecionado
                     Usuario usuarioSelecionado = usuarioDAO.buscarPorId(id);
                     //Preenche os campos do form editar
                     visaoEditarUsuario.getjTextFieldStatus().setText(usuarioSelecionado.getStatus());
                     visaoEditarUsuario.getJTextFieldID().setText(usuarioSelecionado.getId().toString());
                     visaoEditarUsuario.getjTextFieldTipo().setText(usuarioSelecionado.getTipo());
                     visaoEditarUsuario.getjTextFieldNome().setText(usuarioSelecionado.getNome());
                     visaoEditarUsuario.getjTextFieldUsuario().setText(usuarioSelecionado.getUsuario());
                     visaoEditarUsuario.getjPasswordSenha().setText(usuarioSelecionado.getSenha());
                     visaoEditarUsuario.getjPasswordSenha1().setText(usuarioSelecionado.getSenha());
                     visaoEditarUsuario.getjTextFieldCargo().setText(usuarioSelecionado.getCargo());
                     visaoEditarUsuario.getjTextFieldTipo().setEditable(false);
                     visaoEditarUsuario.getjTextFieldUsuario().setEditable(false);
                     visaoEditarUsuario.getjTextFieldStatus().setEditable(false);
                     visaoEditarUsuario.getJTextFieldID().setEditable(false);
                     renderizarVisaoEditarUsuario();
                 }
             }

         }
     };
        visaoGerenciarUsuario.getBtnDetalhes().addActionListener(actionListener);
    }

    private void evtBotaoAtivaDesativa() {
        Usuario user = usuarioDAO.buscarPorId(DadosUsuario.id);
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
             if(permissao(user)){     
                int linha = visaoGerenciarUsuario.getTblUsuario().getSelectedRow(); //Linha selecionada da tabela
                if (linha < 0) {
                    JOptionPane.showMessageDialog(null, "Nenhum usuario selecionado!", "Erro", 0);
                } else {
                    int opcao = JOptionPane.showConfirmDialog(null, "Confirmar a alteração de status do usuário "
                            + visaoGerenciarUsuario.getTblUsuario().getValueAt(linha, 1)
                            + "?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (opcao == 0) {
                        Integer id = (Integer) visaoGerenciarUsuario.getTblUsuario().getValueAt(linha, 0); //ID do item selecionado
                        Usuario usuario = usuarioDAO.buscarPorId(id);
                        if (usuario.getStatus().equals("ATIVO")) {
                            usuario.setStatus("DESATIVADO");
                        } else {
                            usuario.setStatus("ATIVO");
                        }
                        usuarioDAO.alterar(usuario);
                        JOptionPane.showMessageDialog(null, "Status de usuário modificado com Sucesso!", "Sucesso", 1);
                        preencheTabela(usuarioDAO.buscarTodos(), visaoGerenciarUsuario.getTblUsuario()); //Atualiza tabela após remoção
                        }
                    }
                }
            }
        };
        visaoGerenciarUsuario.getBtnAtivarDesati().addActionListener(actionListener);
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
                String tipo = (String)visaoCadastrarUsuario.getjComboBoxTipodeUsuario().getSelectedItem();
                String lembrete = visaoCadastrarUsuario.getjTextFieldLembrete().getText();
                String resposta = visaoCadastrarUsuario.getjPasswordResposta().getText();
                String resposta1 = visaoCadastrarUsuario.getjPasswordResposta1().getText();
                String cpf = visaoCadastrarUsuario.getjTextFieldCPF().getText();
                
                
                Usuario usuarioBuilder = new UsuarioBuilder(cpf)
                        .setNome(nome)
                        .setCargo(cargo)
                        .setUsuario(usuario)
                        .setSenha(senha)
                        .setTipo(tipo)
                        .setLembrete(lembrete)
                        .setResposta(resposta)
                        .setStatus("ATIVO")
                        .build();

                if (validaCadastroUsuario(usuarioBuilder, senha1, resposta1)) {

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

    private boolean validaCadastroUsuario(Usuario usuario, String senha1, String resposta1) {
        restauraCorCamposCadastro();
        
        int tamanhoSenha = usuario.getSenha().length();
        String usuariovalidacao = buscaPorUsuario(usuario.getUsuario());
        String usuariocpf = buscaPorCpf(usuario.getCpf());
        int tamanhoLembrete = usuario.getLembrete().length();
        int tamanhoResposta = usuario.getResposta().length();
        
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
        if (!usuariocpf.equals("NAOEXISTE")) {
            JOptionPane.showMessageDialog(null, "CPF já existente, digite um outro CPF!", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjTextFieldCPF().requestFocus();
            visaoCadastrarUsuario.getjTextFieldCPF().setBackground(Color.yellow);
            return false;
        }
        
        if (tamanhoLembrete < 10) {
            JOptionPane.showMessageDialog(null, "O campo 'Pergunta ou lembrete de recuperação de senha' não pode conter menos que 10 caracteres !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjTextFieldLembrete().requestFocus();
            visaoCadastrarUsuario.getjTextFieldLembrete().setBackground(Color.yellow);
            return false;
        }
        
        if (tamanhoResposta < 6) {
            JOptionPane.showMessageDialog(null, "O campo 'Resposta da recuperação de senha' não pode conter menos que 6 caracteres !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjPasswordResposta().requestFocus();
            visaoCadastrarUsuario.getjPasswordResposta().setBackground(Color.yellow);
            return false;
        }
        
        if (!resposta1.equals(usuario.getResposta())) {
            JOptionPane.showMessageDialog(null, "O campo 'Resposta da recuperação de senha' não pode ser diferente do campo 'Confirmação da resposta' !", "Erro na Validação", 0);
            visaoCadastrarUsuario.getjPasswordResposta().requestFocus();
            visaoCadastrarUsuario.getjPasswordResposta().setBackground(Color.yellow);
            visaoCadastrarUsuario.getjPasswordResposta1().requestFocus();
            visaoCadastrarUsuario.getjPasswordResposta1().setBackground(Color.yellow);
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
        visaoCadastrarUsuario.getjTextFieldLembrete().setBackground(Color.white);
        visaoCadastrarUsuario.getjPasswordResposta().setBackground(Color.white);
        visaoCadastrarUsuario.getjPasswordResposta1().setBackground(Color.white);
    }

    private void limparCamposCadastro() {
        visaoCadastrarUsuario.getjTextFieldNome().setText("");
        visaoCadastrarUsuario.getjTextFieldCargo().setText("");
        visaoCadastrarUsuario.getjTextFieldUsuario().setText("");
        visaoCadastrarUsuario.getjPasswordSenha().setText("");
        visaoCadastrarUsuario.getjPasswordSenha1().setText("");
        visaoCadastrarUsuario.getjTextFieldCPF().setText("");
        visaoCadastrarUsuario.getjTextFieldLembrete().setText("");
        visaoCadastrarUsuario.getjPasswordSenha().setText("");
        visaoCadastrarUsuario.getjPasswordResposta1().setText("");
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
    
    public String buscaPorCpf(String cpf) {

        List<Usuario> listaDeBusca = new ArrayList<>();
        int tamanho = usuarioDAO.buscarTodos().size();
        List<Usuario> listaAux = usuarioDAO.buscarTodos();
        for (int k = 0; k < tamanho; k++) {
            if (listaAux.get(k).getCpf().equals(cpf)) {
                return cpf;
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
 
    

//--------------------Tela Editar--------------------------------------------


  private void evtBotaoSalvar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                //coletando dados da  visaoeditar
                Integer id = Integer.parseInt(visaoEditarUsuario.getJTextFieldID().getText());
                
                
                String tipo = (String)visaoEditarUsuario.getjComboBoxTipo().getSelectedItem();
                String nome = visaoEditarUsuario.getjTextFieldNome().getText();
                String senha = visaoEditarUsuario.getjPasswordSenha().getText();
                String senha1 = visaoEditarUsuario.getjPasswordSenha1().getText();
                String cargo = visaoEditarUsuario.getjTextFieldCargo().getText();
               
               Usuario usuarionovo = usuarioDAO.buscarPorId(id);
               
               usuarionovo.setTipo(tipo);
               usuarionovo.setNome(nome);
               usuarionovo.setSenha(senha);
               usuarionovo.setCargo(cargo);
                
               
               if (validaUsuarioEdicao(usuarionovo, senha1)) {
                    usuarioDAO.alterar(usuarionovo);
                    JOptionPane.showMessageDialog(null, "Usuário editado com Sucesso!", "Sucesso", 1);
                    visaoEditarUsuario.dispose();
                    preencheTabela(usuarioDAO.buscarTodos() , visaoGerenciarUsuario.getTblUsuario());
                }
            }
        };
        visaoEditarUsuario.getBtnSalvar().addActionListener(actionListener);
    }
               

    private boolean validaUsuarioEdicao(Usuario usuario, String senha1) {
       restauraCorCamposEditar();

        int tamanhoSenha = usuario.getSenha().length();
        String usuariovalidacao = buscaPorUsuario(usuario.getUsuario());
       
         if (usuario.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'Nome' não pode ser vazio !", "Erro na Validação", 0);
            visaoEditarUsuario.getjTextFieldNome().requestFocus();
            visaoEditarUsuario.getjTextFieldNome().setBackground(Color.yellow);
            return false;
        }

        if (tamanhoSenha < 6) {
            JOptionPane.showMessageDialog(null, "O campo 'senha' não pode conter menos que seis caracteres !", "Erro na Validação", 0);
            visaoEditarUsuario.getjPasswordSenha().requestFocus();
            visaoEditarUsuario.getjPasswordSenha().setBackground(Color.yellow);
            return false;
        }

        if (usuario.getSenha().equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'senha' não pode ser vazio !", "Erro na Validação", 0);
            visaoEditarUsuario.getjPasswordSenha().requestFocus();
            visaoEditarUsuario.getjPasswordSenha().setBackground(Color.yellow);
            return false;
        }

        if (senha1.equals("")) {
            JOptionPane.showMessageDialog(null, "O campo 'repetir senha' não pode ser vazio !", "Erro na Validação", 0);
            visaoEditarUsuario.getjPasswordSenha1().requestFocus();
            visaoEditarUsuario.getjPasswordSenha1().setBackground(Color.yellow);
            return false;
        }

        if (!usuario.getSenha().equals(senha1)) {
            JOptionPane.showMessageDialog(null, "O campo 'Senha' e 'Repetir Senha' não pode ser diferente !", "Erro na Validação", 0);
            visaoEditarUsuario.getjPasswordSenha().requestFocus();
            visaoEditarUsuario.getjPasswordSenha().setBackground(Color.yellow);
            visaoEditarUsuario.getjPasswordSenha1().requestFocus();
            visaoEditarUsuario.getjPasswordSenha1().setBackground(Color.yellow);
            return false;
        }
        
        
        return true;
    }

private void restauraCorCamposEditar() {
        visaoEditarUsuario.getjTextFieldTipo().setBackground(Color.white);
        visaoEditarUsuario.getjTextFieldNome().setBackground(Color.white);
        visaoEditarUsuario.getjTextFieldCargo().setBackground(Color.white);
        visaoEditarUsuario.getjPasswordSenha().setBackground(Color.white);
        visaoEditarUsuario.getjPasswordSenha1().setBackground(Color.white);
       
    }

    
private void evtBotaoCancelarEditar() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                int opcao = JOptionPane.showConfirmDialog(null, "Deseja cancelar a edição de Usuário? ", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == 0) {
                    visaoEditarUsuario.dispose();
                    restauraCorCamposEditar();
                    }
            }

        };
        visaoEditarUsuario.getBtnCancelar().addActionListener(actionListener);
    }



}


