package Principal;

import conexao.ConexaoBanco;
import modelo.Consultora;
import modelo.Produto;
import modelo.builder.ConsultoraBuilder;
import modelo.dao.ConsultoraDAO;
import modelo.dao.ProdutoDAO;

import java.util.Date;

public class Principal {

    public static void main(String[] args) {
        ConexaoBanco.conectar(); //Conecta com o Banco
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConexaoBanco.desconectar(); //Desconecta
    }

    public static void run() {

        ProdutoDAO produtoDao = new ProdutoDAO(); //instancia um DAO Produto

        Produto p1 = new Produto(); //Cria um objeto novo Produto e insere seus atributos
        p1.setDescricao("Biquini");
        p1.setValor(15.50);

        produtoDao.inserir(p1); //Insere o novo produto

        Produto p2 = new Produto(); //Cria um objeto novo Produto e insere seus atributos
        p2.setDescricao("aaaa");
        p2.setValor(11.50);

        produtoDao.inserir(p2); //Insere o novo produto

        System.out.println(produtoDao.buscarTodos().get(0));
        System.out.println(produtoDao.buscarTodos().get(1));

        Produto p3 = produtoDao.buscarPorId(1);
        System.out.println(p3.getId());
        p3.setValor(112233.0);
        produtoDao.alterar(p3);
        Produto p4 = produtoDao.buscarPorId(1);
        System.out.println("p4");
        System.out.println(p4.getId());
        System.out.println(p4.getValor());
        System.out.println("p1");
        System.out.println(p1.getId());
        System.out.println(p1.getValor());

        System.out.println(produtoDao.buscarTodos().get(0));
        System.out.println(produtoDao.buscarTodos().get(1));

//        produtoDao.remover(10);
        produtoDao.remover(new Produto());

        System.out.println(produtoDao.buscarTodos());


/***********************************************/


        ConsultoraDAO consultoraDAO= new ConsultoraDAO(); //instancia um DAO Produto


        Consultora consultora = new ConsultoraBuilder("Nome")
                .setCpf("123123123")
                .setDataNascimento(new Date())
                .setStatusAtividade(true)
                .build();

        Consultora consultora2 = new ConsultoraBuilder("Nome 2")
                .setCpf("123123123")
                .setDataNascimento(new Date())
                .setStatusAtividade(true)
                .build();

        consultoraDAO.inserir(consultora);
        consultoraDAO.inserir(consultora2);

        System.out.println(consultoraDAO.buscarPorId(1));
        System.out.println(consultoraDAO.buscarPorId(2));

        System.out.println(consultoraDAO.buscarTodos());


    }

}
