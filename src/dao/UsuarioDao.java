/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author Ronnie
 */
public class UsuarioDao extends AbstractDAOImpl<Usuario> {

    public List<Usuario> buscarTodos() {
        return super.buscarTodos(Usuario.class);
    }

    public Usuario buscarPorId(Integer id) {
        return buscarPorId(Usuario.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(Usuario.class, id);

    }
}
