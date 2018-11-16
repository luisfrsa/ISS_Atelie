/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.Calendar;
import modelo.Notificacao;
import modelo.builder.NotificacaoBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luis
 */
public class NotificacaoControleTest {

    private NotificacaoControle notificacaoControle = new NotificacaoControle();

   
    /*Teste caixa preta*/
    @Test
    public void testInserir() {
        Notificacao notificacao = criaNotificacaoCorreta();
        notificacao.setId(null);
        Notificacao retorno = notificacaoControle.salvar(notificacao);
        assertEquals("Nome de tamanho ok", retorno.getNome());
        assertEquals((int) 1, (int) retorno.getPrioridade());
        assertEquals(true, retorno.getStatus());
        notificacaoControle.excluirFisicamente(retorno.getId());

    }

    @Test
    public void testAlterar() {
        Notificacao notificacao = criaNotificacaoCorreta();
        notificacao.setId(null);
        Notificacao retorno = notificacaoControle.salvar(notificacao);
        retorno.setNome("NOVO NOME");
        retorno.setPrioridade(2);
        retorno = notificacaoControle.salvar(notificacao);
        assertEquals("NOVO NOME", retorno.getNome());
        assertEquals((int) 2, (int) retorno.getPrioridade());
        notificacaoControle.excluirFisicamente(retorno.getId());
    }

    /*Teste caixa preta*/

 /*Teste caixa branca*/
    @Test
    public void testValidarNotificacaoOK() {
        Notificacao notificacao = criaNotificacaoCorreta();
        String retorno = notificacaoControle.validarNotificacao(notificacao);
        assertEquals(null, retorno);
    }

    @Test
    public void testValidarNotificacaoNomeNulo() {
        Notificacao notificacao = criaNotificacaoCorreta();
        notificacao.setNome("No");
        String retorno = notificacaoControle.validarNotificacao(notificacao);
        assertEquals("O Nome deve possuir no mínimo 3 caracteres", retorno);
    }

    @Test
    public void testValidarNotificacaoNomeGrande() {
        Notificacao notificacao = criaNotificacaoCorreta();
        notificacao.setNome("Nome muito grande Nome muito grande Nome muito grande Nome muito grande Nome muito grande Nome muito grande Nome muito grande");
        String retorno = notificacaoControle.validarNotificacao(notificacao);
        assertEquals("O Nome deve possuir no máximo 50 caracteres", retorno);
    }

    @Test
    public void testValidarNotificacaoDescricaoPequena() {
        Notificacao notificacao = criaNotificacaoCorreta();
        notificacao.setDescricao("De");
        String retorno = notificacaoControle.validarNotificacao(notificacao);
        assertEquals("A descrição deve possuir no mínimo 3 caracteres", retorno);
    }

    @Test
    public void testValidarNotificacaoDescricaoGrande() {
        Notificacao notificacao = criaNotificacaoCorreta();
        notificacao.setDescricao("Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande Descricao muito grande ");
        String retorno = notificacaoControle.validarNotificacao(notificacao);
        assertEquals("A descrição deve possuir no máximo 300 caracteres", retorno);
    }

    /*Teste caixa branca*/
    private Notificacao criaNotificacaoCorreta() {
        return new NotificacaoBuilder("Nome de tamanho ok")
                .setId(999999)
                .setDescricao("Descricao")
                .setPrioridade(1)
                .setStatus(true)
                .setData(Calendar.getInstance().getTime())
                .build();
    }
}
