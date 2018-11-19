package controle;

import modelo.Produto;
import modelo.builder.ProdutoBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author William Rodrigues
 */
public class ProdutoControleTest {

    private final ProdutoControle controleProduto = new ProdutoControle();

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

    //--------------- PARTICIONAMENTO DE EQUIVALENCIA ---------------
    
    @Test
    /*Cobertura: Validar cadastro de Produto válido
    Resultado esperado: Retorno verdadeiro*/
    public void testValidaCadastroProduto1() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        retorno = controleProduto.validaCadastroProduto(produto);
        assertTrue(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto sem valor
    Resultado esperado: Retorno falso*/
    public void testValidaCadastroProduto2() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(null);
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto sem descrição
    Resultado esperado: Retorno falso*/
    public void testValidaCadastroProduto3() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setDescricao("");
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto com valor = -34
    Resultado esperado: Retorno falso*/
    public void testValidaCadastroProduto4() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(-34));
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto com valor = 27.5
    Resultado esperado: Retorno verdadeiro*/
    public void testValidaCadastroProduto5() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(27.5);
        retorno = controleProduto.validaCadastroProduto(produto);
        assertTrue(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto sem quantidade de estoque
    Resultado esperado: Retorno falso*/
    public void testValidaQuantidadeEstoque1() {
        Boolean retorno;

        retorno = controleProduto.validaQuantidadeEstoque(null);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto com quantidade de estoque = -97
    Resultado esperado: Retorno falso*/
    public void testValidaQuantidadeEstoque2() {
        Boolean retorno;

        retorno = controleProduto.validaQuantidadeEstoque(-97);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto com quantidade de estoque = 532
    Resultado esperado: Retorno verdadeiro*/
    public void testValidaQuantidadeEstoque3() {
        Boolean retorno;

        retorno = controleProduto.validaQuantidadeEstoque(532);
        assertTrue(retorno);
    }

    @Test
    /*Cobertura: Validar edição de Produto válido
    Resultado esperado: Retorno verdadeiro*/
    public void testValidaEdicaoProduto1() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertTrue(retorno);
    }

    @Test
    /*Cobertura: Validar edição de Produto sem valor
    Resultado esperado: Retorno falso*/
    public void testValidaEdicaoProduto2() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(null);
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar edição de Produto sem descrição
    Resultado esperado: Retorno falso*/
    public void testValidaEdicaoProduto3() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setDescricao("");
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar edição de Produto com valor = -305.7
    Resultado esperado: Retorno falso*/
    public void testValidaEdicaoProduto4() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(-305.7);
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar edição de Produto com valor = 19
    Resultado esperado: Retorno falso*/
    public void testValidaEdicaoProduto5() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(19));
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertTrue(retorno);
    }

    //--------------- ANALISE DE VALOR LIMITE ---------------
    
    @Test
    /*Cobertura: Validar cadastro de Produto com valor = 0
    Resultado esperado: Retorno falso*/
    public void testValidaCadastroProduto6() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(0));
        retorno = controleProduto.validaCadastroProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto com valor = 1
    Resultado esperado: Retorno verdadeiro*/
    public void testValidaCadastroProduto7() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(1));
        retorno = controleProduto.validaCadastroProduto(produto);
        assertTrue(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto com quantidade de estoque = -1
    Resultado esperado: Retorno falso*/
    public void testValidaQuantidadeEstoque4() {
        Boolean retorno;

        retorno = controleProduto.validaQuantidadeEstoque(-1);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar cadastro de Produto com quantidade de estoque = 0
    Resultado esperado: Retorno verdadeiro*/
    public void testValidaQuantidadeEstoque5() {
        Boolean retorno;

        retorno = controleProduto.validaQuantidadeEstoque(0);
        assertTrue(retorno);
    }

    @Test
    /*Cobertura: Validar edição de Produto com valor = 0
    Resultado esperado: Retorno falso*/
    public void testValidaEdicaoProduto6() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(0));
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertFalse(retorno);
    }

    @Test
    /*Cobertura: Validar edição de Produto com valor = 1
    Resultado esperado: Retorno falso*/
    public void testValidaEdicaoProduto7() {
        Boolean retorno;
        Produto produto;

        produto = criaProdutoValido();
        produto.setValor(Double.valueOf(1));
        retorno = controleProduto.validaEdicaoProduto(produto);
        assertTrue(retorno);
    }

}
