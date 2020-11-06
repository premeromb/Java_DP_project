

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CommentTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CommentTest
{
    Comment c = new Comment("Pedro", "Es muy bueno", 5);
    
    /**
     * Default constructor for test class CommentTest
     */
    public CommentTest()
    {
    }

    /**
     * Prueba los métodos getVoteCount(), upvote() y downvote()
     */
    @Test
    public void prueba_votos()
    {
        assertEquals(0, c.getVoteCount());
        c.upvote();
        assertEquals(1, c.getVoteCount());
        c.downvote();
        assertEquals(0, c.getVoteCount());
    }
}

