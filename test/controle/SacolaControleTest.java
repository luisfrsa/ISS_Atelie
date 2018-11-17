package controle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import modelo.Consultora;
import modelo.ItemSacola;
import modelo.Produto;
import modelo.Sacola;
import modelo.builder.ProdutoBuilder;
import modelo.builder.SacolaBuilder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luis e William
 */
public class SacolaControleTest {
    
   private SacolaControle controleSacola = new SacolaControle();

    public SacolaControleTest() {
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

    //------------------------------ TESTES LUIS ------------------------------
    @Test
    public void testCalculoLucro() {
        List<ItemSacola> listaItens = new ArrayList<>();
        ItemSacola itemSacola1 = new ItemSacola();
        ItemSacola itemSacola2 = new ItemSacola();
//        itemSacola.setProduto();
        Produto p1 = new ProdutoBuilder("Produto1").setValor(10.01).build();
        Produto p2 = new ProdutoBuilder("Produto2").setValor(3.30).build();

        itemSacola1.setProduto(p1);
        itemSacola2.setProduto(p2);

        itemSacola1.setQuantidade(10);
        itemSacola2.setQuantidade(10);

        listaItens.add(itemSacola1);
        listaItens.add(itemSacola2);
        SacolaControle sacolaControle = new SacolaControle();

        Sacola sacola = new SacolaBuilder()
                .setListaProdutos(listaItens)
                .build();
        double lucro = sacolaControle.calculoLucroSacolas(sacola);
        assertEquals((int) 133.1, (int) lucro);
    }

    @Test
    public void testCalculoLucro2() {
        List<ItemSacola> listaItens = new ArrayList<>();
        ItemSacola itemSacola1 = new ItemSacola();
        ItemSacola itemSacola2 = new ItemSacola();
//        itemSacola.setProduto();
        Produto p1 = new ProdutoBuilder("Produto1").setValor(123.00).build();
        Produto p2 = new ProdutoBuilder("Produto2").setValor(312.00).build();

        itemSacola1.setProduto(p1);
        itemSacola2.setProduto(p2);

        itemSacola1.setQuantidade(2);
        itemSacola2.setQuantidade(3);

        listaItens.add(itemSacola1);
        listaItens.add(itemSacola2);
        SacolaControle sacolaControle = new SacolaControle();

        Sacola sacola = new SacolaBuilder()
                .setListaProdutos(listaItens)
                .build();
        double lucro = sacolaControle.calculoLucroSacolas(sacola);

        assertEquals((int) 1182, (int) lucro);
    }

    //------------------------------ TESTES WILLIAM ------------------------------
    //Testes de caixa branca sobre o método de válidar Sacola
    
    private Sacola criaSacolaValida(){
        
        Consultora consultora = new Consultora();
        Produto produto = new Produto();
        ItemSacola itemSacola = new ItemSacola();
        List<ItemSacola> listaItens = new ArrayList<>();
        listaItens.add(itemSacola);
        
        Sacola sacola = new SacolaBuilder()
                .setId(999)
                .setDataCriacao(Calendar.getInstance().getTime())
                .setDataAcerto(Calendar.getInstance().getTime())
                .setConsultora(consultora)
                .build();
        
        return sacola;        
    }
    
    @Test
    //Caminho 1
    public void testValidaSacola1() {
        
        boolean retorno;
        Sacola sacola = criaSacolaValida();
        sacola.setConsultora(null);
        retorno = controleSacola.validaSacola(sacola);
        assertFalse(retorno);
        
    }
    
    @Test
    //Caminho 2
    public void testValidaSacola2() {
        
        boolean retorno;
        Sacola sacola = criaSacolaValida();
        sacola.setDataAcerto(null);
        retorno = controleSacola.validaSacola(sacola);
        assertFalse(retorno);
        
    }
    
    @Test
    //Caminho 3
    public void testValidaSacola3() {
        
        boolean retorno;
        Sacola sacola = criaSacolaValida();
        sacola.setListaItens(new ArrayList<>());
        retorno = controleSacola.validaSacola(sacola);
        assertFalse(retorno);
        
    }
    
    @Test
    //Caminho 4
    public void testValidaSacola4() {
        
        boolean retorno;
        Sacola sacola = criaSacolaValida();
        retorno = controleSacola.validaSacola(sacola);
        assertTrue(retorno);
        
    }

}
