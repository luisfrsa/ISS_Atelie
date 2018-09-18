package dao;

import java.util.List;
import modelo.Sacola;

public class SacolaDAO extends AbstractDAOImpl<Sacola> {
    
    public List<Sacola> buscarTodas() {
        return super.buscarTodos(Sacola.class);
    }

    public Sacola buscarPorId(Integer id) {
        return buscarPorId(Sacola.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(Sacola.class, id);
    }

}
