/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author luisr
 */
public class DatasTest {

    public DatasTest() {
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

    @Test
    public void testStringToData() {
        String data = "05/08/1901";
        Date result = Datas.stringToData(data);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(1901, cal.get(Calendar.YEAR));
        assertEquals(7, cal.get(Calendar.MONTH));
        assertEquals(5, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testStringToData2() {
        String data = "31/12/2018";
        Date result = Datas.stringToData(data);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(2018, cal.get(Calendar.YEAR));
        assertEquals(11, cal.get(Calendar.MONTH));
        assertEquals(31, cal.get(Calendar.DAY_OF_MONTH));
    }
    
      @Test
    public void testStringToData3() {
        String data = "01/01/2000";
        Date result = Datas.stringToData(data);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assertEquals(2000, cal.get(Calendar.YEAR));
        assertEquals(0, cal.get(Calendar.MONTH));
        assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
    }
}
