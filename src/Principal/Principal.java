package Principal;

import conexao.ConexaoBanco;
import controle.ProdutoControle;
import dao.ProdutoDAO;
import visao.Inicio;
import visao.produto.FormGerenciarProdutos;

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
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
    }

}
