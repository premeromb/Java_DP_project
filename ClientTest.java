

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClientTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClientTest
{
    Client client1 = new Client(1, "Luis", 34, "Cáceres", null);
    Product hogar1 = new Hogar(1, "Figura1", 10, 5, 10, "kitchen");
    Product hogar2 = new Hogar(2, "Figura2", 45, 10, 45, "kitchen");
    Product hogar3 = new Hogar(3, "Figura3", 100, 10, 100, "kitchen");
   
    /**
     * Default constructor for test class ClientTest
     */
    public ClientTest()
    {
    }

    @Test
    public void prueba_addProductToFav()
    {
        assertEquals(false, client1.existeProdFav("Figura1"));
        client1.addProductToFav("Figura1", hogar2);
        assertEquals(true, client1.existeProdFav("Figura1"));
    }
    
    @Test
    public void prueba_addComment()
    {
        client1.addProductToFav("Figura2", hogar2);
        assertEquals(false, client1.addComment(hogar1, "Está bien", 3));
        assertEquals(true,client1.addComment(hogar2, "Está bien", 3));
        CommentedProduct commen = (CommentedProduct) hogar2;
        assertEquals(1, commen.getNumComents());
        
    }
    
    @Test
    public void prueba_giveLike()
    {
        
        assertEquals(true, client1.giveLike(hogar1));
        Likeable prod = (Likeable) hogar1;
        assertEquals(1, prod.getLikes());
    }
    
    @Test
    public void prueba_giveUnLike()
    {
        
        assertEquals(true, client1.giveLike(hogar1));
        Likeable prod = (Likeable) hogar1;
        assertEquals(true, client1.giveUnlike(hogar1));
        assertEquals(0, prod.getLikes());
        assertEquals(true, client1.giveLike(hogar1));
        assertEquals(true, client1.giveUnlike(hogar1));
        assertEquals(0, prod.getLikes());
    }
   
}



