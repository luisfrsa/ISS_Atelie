
package dao;

import java.util.List;
import modelo.ItemEstoque;


public class ItemEstoqueDAO extends AbstractDAOImpl<ItemEstoque>{
    
    public List<ItemEstoque> buscarTodos() {
        return super.buscarTodos(ItemEstoque.class);
    }

    public ItemEstoque buscarPorId(Integer id) {
        return buscarPorId(ItemEstoque.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(ItemEstoque.class, id);
    }
    
}
