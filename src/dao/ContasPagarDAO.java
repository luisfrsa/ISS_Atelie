/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.ContasPagar;

/**
 *
 * @author Ronnie
 */
public class ContasPagarDAO extends AbstractDAOImpl<ContasPagar> {

    public List<ContasPagar> buscarTodos() {
        return super.buscarTodos(ContasPagar.class);
    }

    public ContasPagar buscarPorId(Integer id) {
        return buscarPorId(ContasPagar.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(ContasPagar.class, id);
    }

}
