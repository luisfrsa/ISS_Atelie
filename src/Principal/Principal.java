package Principal;

import conexao.ConexaoBanco;
import modelo.Produto;
import modelo.ProdutoDAO;

public class Principal {

    public static void main(String[] args) {
        
        ConexaoBanco.conectar(); //Conecta com o Banco
        
        ProdutoDAO produtoDao = new ProdutoDAO(); //instancia um DAO Produto
        
        Produto p1 = new Produto(); //Cria um objeto novo Produto e insere seus atributos
        p1.setDescricao("Biquini");
        p1.setValor(Float.parseFloat("15.50"));
        
        produtoDao.inserir(p1); //Insere o novo produto
        
        ConexaoBanco.desconectar(); //Desconecta
        
    }
    
}
