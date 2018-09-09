package modelo;

import conexao.ConexaoBanco;

public class ProdutoDAO {

    public void inserir(Produto produto) {
        ConexaoBanco.em.getTransaction().begin();
        ConexaoBanco.em.persist(produto);
        ConexaoBanco.em.getTransaction().commit();
    }

}
