package modelo.dao;

import conexao.ConexaoBanco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import static java.lang.String.format;
import static java.util.Objects.isNull;

public class AbstractDAOImpl<T> implements DAOInterface<T> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDAOImpl.class);

    @Override
    public T inserir(T object) {
        LOG.info(format("Inserindo registro %s na classe %s", object.toString(), object.getClass().getSimpleName()));

        if (isNull(object)) {
            throw new RuntimeException(format("Tentativa de insersão na classe %s de registro nulo", object.getClass().getSimpleName()));
        }
        ConexaoBanco.getEm().getTransaction().begin();
        ConexaoBanco.getEm().persist(object);
        ConexaoBanco.getEm().getTransaction().commit();

        return object;
    }

    @Override
    public T alterar(T object) {
        return inserir(object);
    }

    @Override
    public boolean remover(T object) {
        LOG.info(format("Removendo registro %s na classe %s", object.toString(), object.getClass().getSimpleName()));

        if (isNull(object)) {
            throw new RuntimeException(format("Tentativa de exclusão na classe %s de registro nulo", object.getClass().getSimpleName()));
        }
        ConexaoBanco.getEm().getTransaction().begin();
        ConexaoBanco.getEm().remove(object);
        ConexaoBanco.getEm().getTransaction().commit();

        return true;
    }

    @Override
    public boolean remover(Class<T> clazz, Integer id) {
        T object = buscarPorId(clazz, id);
        if (isNull(object)) {
            throw new RuntimeException(format("Não foi possível remover registro da classe %s, pelo id %d, cause: Id não encontrado", clazz.getSimpleName(), id));
        }
        return remover(object);
    }

    @Override
    public T buscarPorId(Class<T> clazz, Integer id) {
        ConexaoBanco.conectar();
        LOG.info(format("Buscando registro da classe %s pelo id %s", clazz.getSimpleName(), id));
        return ConexaoBanco.getEm().find(clazz, id);
    }

    @Override
    public List<T> buscarTodos(Class<T> clazz) {
        LOG.info(format("Buscando todos os registros da classe %s", clazz.getSimpleName()));

        ConexaoBanco.getEm().getTransaction().begin();
        List<T> lista = ConexaoBanco.getEm().createQuery("select p from " + clazz.getSimpleName() + " p").getResultList();
        ConexaoBanco.getEm().getTransaction().commit();

        return lista;
    }
}
