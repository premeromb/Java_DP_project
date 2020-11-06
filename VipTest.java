import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class VipTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VipTest
{
    Vip vip1 = new Vip(1, "Luis", 34, "Cáceres", null);
    Hogar hogar1 = new Hogar(1, "Figura1", 10, 5, 10, "kitchen");
    Hogar hogar2 = new Hogar(2, "Figura2", 45, 10, 45, "kitchen");
    Hogar hogar3 = new Hogar(3, "Figura3", 100, 10, 100, "kitchen");
   
    /**
     * Default constructor for test class VipTest
     */
    public VipTest()
    {
    }


    @Test
    public void prueba_prepararPedido()
    {
        vip1.addProductToFav("F1", hogar1);
        vip1.addProductToFav("F2", hogar2);
        vip1.addProductToFav("F3", hogar3);
        assertEquals(true, vip1.prepararPedido());
    }

    @Test
    public void prueba_obtenerPrecio()
    {
        vip1.addListaCompra(hogar1);
        vip1.addListaCompra(hogar2);
        vip1.addListaCompra(hogar3);
        assertEquals(147.25, vip1.obtenerPrecio(), 0.1);
    }
}



