package Principal;

import controle.GerenciarProdutosControle;
import modelo.dao.ProdutoDAO;
import visao.FormGerenciarProdutos;

public class Principal {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {

        }
    }

    public static void run() {

        ProdutoDAO produtoDao = new ProdutoDAO(); //instancia um DAO Produto        
        FormGerenciarProdutos visaoGerenciarProdutos = new FormGerenciarProdutos(); //Instancia a visao
        GerenciarProdutosControle controleGerenciarProdutos = new GerenciarProdutosControle(produtoDao, visaoGerenciarProdutos); //Intancia o controle
        visaoGerenciarProdutos.setVisible(true); //Torna a visao ativa

    }

}
