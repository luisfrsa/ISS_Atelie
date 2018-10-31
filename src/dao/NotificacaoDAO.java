package dao;

import modelo.Notificacao;

import java.util.List;

public class NotificacaoDAO extends AbstractDAOImpl<Notificacao> {


    public List<Notificacao> buscarTodos() {
        return super.buscarTodos(Notificacao.class);
    }

    public Notificacao buscarPorId(Integer id) {
        return super.buscarPorId(Notificacao.class, id);
    }

    public boolean remover(Integer id) {
        return super.remover(Notificacao.class, id);
    }

 }
