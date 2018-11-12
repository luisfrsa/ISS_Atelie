package Principal;

import conexao.ConexaoBanco;
import controle.LoginControle;

public class Principal {

    public static void main(String[] args) {
        ConexaoBanco.conectar();
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println("CLOSED");
//        ConexaoBanco.desconectar();

    }

    public static void run() {
        LoginControle logincontrole = new LoginControle();
        logincontrole.renderizarVisaoLogin();
       
    }

}
