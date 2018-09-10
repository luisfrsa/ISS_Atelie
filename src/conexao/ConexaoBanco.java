package conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Classe de Conexão com o Banco de Dados
public class ConexaoBanco {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void conectar() {
        emf = Persistence.createEntityManagerFactory("AtelieJPA");
        em = emf.createEntityManager();
    }

    public static void desconectar() {
        em.close();
        emf.close();
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static EntityManager getEm() {
        return em;
    }
}
