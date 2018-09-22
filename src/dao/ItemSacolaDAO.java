package dao;

import java.util.List;
import modelo.ItemSacola;

public class ItemSacolaDAO extends AbstractDAOImpl<ItemSacola> {

    public List<ItemSacola> buscarTodos() {
        return super.buscarTodos(ItemSacola.class);
    }

    public ItemSacola buscarPorId(Integer id) {
        return buscarPorId(ItemSacola.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(ItemSacola.class, id);
    }
}
