package modelo.dao;

import modelo.Consultora;
import modelo.Produto;

import java.util.List;

public class ConsultoraDAO extends AbstractDAOImpl<Consultora> {


    public List<Consultora> buscarTodos() {
        return super.buscarTodos(Consultora.class);
    }

    public Consultora buscarPorId(Integer id) {
        return super.buscarPorId(Consultora.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(Consultora.class, id);
    }

 }
