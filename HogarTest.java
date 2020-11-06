

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HogarTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HogarTest
{
    Hogar hogar1 = new Hogar(32, "FiguraMesa", 45, 10, 450, "livingroom");
        
    /**
     * Default constructor for test class HogarTest
     */
    public HogarTest()
    {
    }

    @Test
    public void prueba_isCommand()
    {
        Hogar hogar1 = new Hogar(32, "FiguraMesa", 45, 10, 450, "livingroom");
        assertEquals(true, hogar1.isCommand("livingroom"));
        assertEquals(false, hogar1.isCommand("living"));
    }
}

