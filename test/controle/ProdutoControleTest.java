package controle;

import dao.ItemEstoqueDAO;
import dao.ProdutoDAO;
import java.util.List;
import javax.swing.JTable;
import modelo.ItemEstoque;
import modelo.Produto;
import modelo.builder.ProdutoBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import controle.ProdutoControle;
import static org.junit.Assert.*;

/**
 *
 * @author William Rodrigues
 */
public class ProdutoControleTest {

    private ProdutoControle controleProduto = new ProdutoControle();

    public ProdutoControleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private Produto criaProdutoValido() {
        return new ProdutoBuilder("Descrição válida")
                .setValor(100.0)
                .setCor("Cor válida")
                .setTamanho("Tamanho válido")
                .setMarca("Marca válida")
                .setModelo("Modelo válido")
                .build();
    }

    //TESTE DE CAIXA PRETA    
    @Test
    public void testInserir() {
        //Criando novo Produto válido
        Produto produto = criaProdutoValido();
        Produto retorno = controleProduto.getDaoProduto().inserir(produto);

        //Verificando o retorno da Inserção
        assertEquals("Descrição válida", retorno.getDescricao());
        assertEquals(Double.valueOf(100.0), retorno.getValor());
        assertEquals("Cor válida", retorno.getCor());
        assertEquals("Tamanho válido", retorno.getTamanho());
        assertEquals("Marca válida", retorno.getMarca());
        assertEquals("Modelo válido", retorno.getModelo());

        //Removendo o Produto criado para teste
        controleProduto.getDaoProduto().remover(produto);
    }

    @Test
    public void testAlterar() {
        //Criando novo Produto válido
        Produto produto = criaProdutoValido();
        Produto retorno = controleProduto.getDaoProduto().inserir(produto);

        //Alterando o Produto criado
        retorno.setDescricao("Nova descrição");
        retorno.setValor(200.0);
        retorno.setCor("Nova cor");
        retorno.setTamanho("Novo tamanho");
        retorno.setMarca("Nova marca");
        retorno.setModelo("Novo modelo");
        retorno = controleProduto.getDaoProduto().alterar(retorno);

        //Verificando retorno após a alteração
        assertEquals("Nova descrição", retorno.getDescricao());
        assertEquals(Double.valueOf(200.0), retorno.getValor());
        assertEquals("Nova cor", retorno.getCor());
        assertEquals("Novo tamanho", retorno.getTamanho());
        assertEquals("Nova marca", retorno.getMarca());
        assertEquals("Novo modelo", retorno.getModelo());

        //Removendo o Produto criado para teste
        controleProduto.getDaoProduto().remover(produto);
    }

    //TESTE DE CAIXA BRANCA
    //Análise de Valor Limite    
    @Test
    public void testValidaCadastroProduto() {
        Boolean retorno;
        Produto produto;

        //Validando Produto (válido)
        produto = criaProdutoValido();
        retorno = controleProduto.validaCadastroProduto(produto);
        assertTrue(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setValor(null);
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setDescricao("");
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(0));
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);

        //Validando Produto (válido)
        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(1));
        retorno = controleProduto.validaCadastroProduto(produto);
        assertTrue(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(-1));
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);
    }

    @Test
    public void testValidaQuantidadeEstoque() {
        Boolean retorno;

        //Validando quantidade (inválida)
        retorno = controleProduto.validaQuantidadeEstoque(null);
        assertFalse(retorno);

        //Validando quantidade (inválida)
        retorno = controleProduto.validaQuantidadeEstoque(-1);
        assertFalse(retorno);

        //Validando quantidade (válida)
        retorno = controleProduto.validaQuantidadeEstoque(0);
        assertTrue(retorno);

        //Validando quantidade (válida)
        retorno = controleProduto.validaQuantidadeEstoque(1);
        assertTrue(retorno);
    }

    @Test
    public void testValidaEdicaoProduto() {
        Boolean retorno;
        Produto produto;

        //Validando Produto (válido)
        produto = criaProdutoValido();
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertTrue(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setValor(null);
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setDescricao("");
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(0));
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);

        //Validando Produto (válido)
        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(1));
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertTrue(retorno);

        //Validando Produto (inválido)
        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(-1));
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);
    }

}
