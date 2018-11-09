/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
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
import util.Datas;

/**
 *
 * @author luis
 */
public class SacolaControleTest {
    
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

    /**
     * Test of renderizarVisaoGerenciarSacolas method, of class SacolaControle.
     */
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
        assertEquals((int)133.1,(int) lucro);     
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
        
        assertEquals((int)1182,(int) lucro);     
    }
 
    
}
