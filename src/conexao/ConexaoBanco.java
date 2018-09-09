package conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static java.util.Objects.isNull;

//Classe de Conexão com o Banco de Dados

public class ConexaoBanco {

    private static EntityManagerFactory emf = null;
    private static EntityManager em= null;


    public static void conectar() {
        if(isNull(emf) || isNull(em)) {
            emf = Persistence.createEntityManagerFactory("AtelieJPA");
            em = emf.createEntityManager();
        }
    }

    public static void desconectar() {
        em.close();
        emf.close();
    }

    public static EntityManagerFactory getEmf() {
        if(isNull(emf)){
            conectar();
        }
        return emf;
    }

    public static EntityManager getEm() {
        if(isNull(em)){
            conectar();
        }
        return em;
    }
}
