package modelo.dao;

import conexao.ConexaoBanco;
import modelo.Produto;

import java.util.List;

public class ProdutoDAO extends AbstractDAOImpl<Produto> {

    public List<Produto> buscarTodos() {
        return super.buscarTodos(Produto.class);
    }

    public Produto buscarPorId(Integer id) {
        return super.buscarPorId(Produto.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(Produto.class, id);
    }

}
