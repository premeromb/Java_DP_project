

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StockManagerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StockManagerTest
{
    private Ocio ocio1;
    private Hogar hogar1;
    private Alimentacion alimenta1;
    
    private StockManager stm;
    
    /**
     * Default constructor for test class StockManagerTest
     */
    public StockManagerTest()
    {
        stm = stm.getInstance();
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        ocio1 = new Ocio(1, "ff", 33, 3, 12);
        hogar1 = new Hogar(2, "ff", 34, 3, 233, "kitchen");
        alimenta1 = new Alimentacion(3, "kitchen", 34, 33, 3, "kitchen");
        
    }
    

    

    @Test
    public void preuba1()
    {
        assertEquals(true, stm.addProductStock(ocio1));
        assertEquals(true, stm.addProductStock(hogar1));
        assertEquals(true, stm.addProductStock(alimenta1));
        Vip vip1 = new Vip(1, "Pedro", 23, "cc", stm);
        assertEquals(true, stm.addClient(vip1));
        vip1.addProductToFav("p1", ocio1);
        vip1.addProductToFav("p2", hogar1);
        
        assertEquals(true, vip1.prepararPedido());
        assertEquals(235.72, vip1.obtenerPrecio(), 0.1);
        assertEquals(true, vip1.realizarPedido());
    }
}



