package modelo.dao;

import conexao.ConexaoBanco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static java.lang.String.format;
import static java.util.Objects.isNull;

public class AbstractDAOImpl<T> implements DAOInterface<T> {


    private static final Logger log = LoggerFactory.getLogger(AbstractDAOImpl.class);

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public AbstractDAOImpl() {
        emf = ConexaoBanco.getEmf();
        em = ConexaoBanco.getEm();
    }

    @Override
    public T inserir(T object) {
        log.info(format("Inserindo registro %s na classe %s",object.toString(),object.getClass().getSimpleName()));
        if (isNull(object)) {
            throw new RuntimeException(format("Tentativa de insersão na classe %s de registro nulo", object.getClass().getSimpleName()));
        }
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
        return object;
    }

    @Override
    public T alterar(T object) {
        return inserir(object);
    }


    @Override
    public boolean remover(T object) {
        log.info(format("Removendo registro %s na classe %s",object.toString(),object.getClass().getSimpleName()));
        if (isNull(object)) {
            throw new RuntimeException(format("Tentativa de exclusão na classe %s de registro nulo", object.getClass().getSimpleName()));
        }
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
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
        log.info(format("Buscando registro da classe %s pelo id %s",clazz.getSimpleName(), id));
        return em.find(clazz, id);
    }

    @Override
    public List<T> buscarTodos(Class<T> clazz) {
        log.info(format("Buscando todos os registros da classe %s",clazz.getSimpleName()));
        em.getTransaction().begin();
        List<T> lista = em.createQuery("select p from " + clazz.getSimpleName() + " p").getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
