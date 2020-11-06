
/**
 * Implentación de la interfaz Likeable.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public interface Likeable
{
   /**
   * Record one more 'Like' indication from a user.
   */
   public void like();
   
   /**
   * Record that a user has withdrawn his/her 'Like' vote.
   */
   public void unlike();
   
   /**
   * Return the number of likes of this class.
   * @return The class' number of likes.
   */
   public int getLikes();
}
