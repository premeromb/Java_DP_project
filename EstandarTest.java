

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EstandarTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EstandarTest
{
    Estandar estandar1 = new Estandar(1, "Luis", 34, "Cáceres", null);
    Hogar hogar1 = new Hogar(1, "Figura1", 10, 5, 10, "kitchen");
    Hogar hogar2 = new Hogar(2, "Figura2", 45, 10, 45, "kitchen");
    Hogar hogar3 = new Hogar(3, "Figura3", 100, 10, 100, "kitchen");
     
    /**
     * Default constructor for test class EstandarTest
     */
    public EstandarTest()
    {
    }

     @Test
    public void prueba_PrepararPedido()
    {
        estandar1.addProductToFav("f1", hogar1);
        estandar1.addProductToFav("f2", hogar2);
        estandar1.addProductToFav("f3", hogar3);
        assertEquals(true, estandar1.prepararPedido());
        assertEquals(estandar1.getListaCompra(), estandar1.getListaCompra());
    }

    @Test
    public void prueba_ObtenerPrecio()
    {
        estandar1.addListaCompra(hogar1);
        estandar1.addListaCompra(hogar2);
        estandar1.addListaCompra(hogar3);
        assertEquals(7362.5, estandar1.obtenerPrecio(), 0.1);
    }
}
