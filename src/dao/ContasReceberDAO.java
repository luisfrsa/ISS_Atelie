/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.ContasReceber;

/**
 *
 * @author Ronnie
 */
public class ContasReceberDAO extends AbstractDAOImpl<ContasReceber> {

    public List<ContasReceber> buscarTodos() {
        return super.buscarTodos(ContasReceber.class);
    }

    public ContasReceber buscarPorId(Integer id) {
        return buscarPorId(ContasReceber.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(ContasReceber.class, id);
    }

}
