/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.Calendar;
import java.util.List;
import modelo.Consultora;
import modelo.builder.ConsultoraBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis
 */
public class ConsultoraControleTest {

    private ConsultoraControle consultoraControle = new ConsultoraControle();

    public ConsultoraControleTest() {
    }

    @Before
    public void setUp() {
        List<Consultora> consultoras = consultoraControle.buscarTodas();
        for (Consultora c : consultoras) {
            consultoraControle.excluirFisicamente(c.getId());
        }
    }

    /*Teste caixa preta*/
    @Test
    public void testInserir() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setId(null);
        Consultora retorno = consultoraControle.salva(consultora);
        assertEquals("Nome de tamanho ok", retorno.getNome());
        assertEquals("581.547.790-72", retorno.getCpf());
        assertEquals("123456", retorno.getSenha());
        consultoraControle.excluirFisicamente(retorno.getId());

    }

    @Test(expected = RuntimeException.class)
    public void testInserirNomePequeno() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("No");
        Consultora retorno = consultoraControle.salva(consultora);
    }

    public void testInserirNomeOk() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nom");
        Consultora retorno = consultoraControle.salva(consultora);
        assertEquals("Nom", retorno.getNome());

        consultoraControle.excluirFisicamente(retorno.getId());

    }

    public void testInserirNomeOk1() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome");
        Consultora retorno = consultoraControle.salva(consultora);
        assertEquals("Nome", retorno.getNome());

        consultoraControle.excluirFisicamente(retorno.getId());
    }

    @Test(expected = RuntimeException.class)
    public void testInserirNomeGrande() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome com 51 caracteres Nome com 51 caracteres Nome ");
        Consultora retorno = consultoraControle.salva(consultora);
    }

    public void testInserirNomeGrandeOk() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome com 49 caracteres Nome com 49 caracteres Nom");
        Consultora retorno = consultoraControle.salva(consultora);
        assertEquals("Nome com 49 caracteres Nome com 49 caracteres Nom", retorno.getNome());
        consultoraControle.excluirFisicamente(retorno.getId());
    }

    public void testInserirNomeGrandeOk1() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome com 50 caracteres Nome com 50 caracteres Nome");
        Consultora retorno = consultoraControle.salva(consultora);
        assertEquals("Nome com 50 caracteres Nome com 50 caracteres Nome", retorno.getNome());
        consultoraControle.excluirFisicamente(retorno.getId());

    }

    @Test
    public void testAlterar() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setCpf("287.217.720-53");
        consultora.setId(null);
        Consultora retorno = consultoraControle.salva(consultora);
        retorno.setNome("NOVO NOME");
        retorno.setSenha("NOVA SENHA");
        retorno = consultoraControle.salva(consultora);
        assertEquals("NOVO NOME", retorno.getNome());
        assertEquals("NOVA SENHA", retorno.getSenha());
        consultoraControle.excluirFisicamente(retorno.getId());
    }

    /*Teste caixa preta*/

 /*Teste caixa branca*/
    @Test
    public void testValidarConsultoraOK() {
        Consultora consultora = criaConsultoraCorreta();
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals(null, retorno);
    }

    @Test
    public void testValidarNomeNulo() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome(null);
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals("O Nome deve possuir no mínimo 3 caracteres", retorno);

    }

    @Test
    public void testValidarNomePequeno() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("No");
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals("O Nome deve possuir no mínimo 3 caracteres", retorno);
    }

    @Test
    public void testValidarNomeGrande() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome muito grande nome muito grande Nome muito grande nome muito grande Nome muito grande nome muito grande Nome muito grande nome muito grande");
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals("O Nome deve possuir no máximo 50 caracteres", retorno);
    }

    @Test
    public void testValidarCPFNulo() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome de tamanho ok");
        consultora.setCpf(null);
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals("CPF Inválido", retorno);

    }

    @Test
    public void testValidarCPFErrado() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome de tamanho ok");
        consultora.setCpf("123.456.789-10");
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals("CPF Inválido", retorno);

    }

    @Test
    public void testValidarDataNula() {
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome de tamanho ok");
        consultora.setCpf("581.547.790-72");
        consultora.setDataNascimento(null);
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals("Não foi possível converter data de nascimento, favor informar dd/mm/yyyy", retorno);

    }

    @Test
    public void testValidarCpfRepetido() {
        Consultora inserida = criaConsultoraCorreta();
        inserida.setId(null);
        inserida = consultoraControle.salva(inserida);
        Consultora consultora = criaConsultoraCorreta();
        consultora.setNome("Nome de tamanho ok");
        String retorno = consultoraControle.validarConsultora(consultora);
        assertEquals("Consultora Nome de tamanho ok já possui CPF 581.547.790-72", retorno);
        consultoraControle.excluirFisicamente(inserida.getId());

    }

    private Consultora criaConsultoraCorreta() {
        return new ConsultoraBuilder("Nome de tamanho ok")
                .setCpf("581.547.790-72")
                .setDataNascimento(Calendar.getInstance().getTime())
                .setSenha("123456")
                .setStatusAtividade(true)
                .build();
    }
    /*Teste caixa branca*/

}
