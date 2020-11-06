/**
 * Implentación de la clase Hogar.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public class Hogar extends CommentedProduct implements Likeable
{
    
    private Integer descuento;
    private String homePart;
    private Integer likes;   
    
    private static final String[] validCommands = {
        "livingroom" , "kitchen", "bedroom", "bathroom", "garden"
    };
    
    /**
     * Constructor for objects of class Hogar
     */
    public Hogar(Integer id, String name, Integer quantity, Integer minStock, 
                        float prize, String homePart)
     {
        
        super(id,name, quantity, minStock, prize);
        descuento = 5; 
        if (!isCommand(homePart)){ 
            throw new IllegalArgumentException("HomePart Erroneo");
        }
        else
        {
          this.homePart = homePart; 
        }
        likes = 0;
        super.setPrize((float)(prize - (prize*descuento/100)));
    }
    
    /**
     * Set Descuento
     * @param descuento El descuento de este producto
     */
    public void setDescuento( int descuento)
    {
        this.descuento = descuento;
    }
    
    /**
     * Set homePart
     * @param homePart El homePart de este producto
     */
    public void setHomePart( String homePart)
    {
        this.homePart = homePart;
    }
    
    /**
     * Set Likes
     * @param likes El número de likes de este producto
     */
    public void setLikes( int likes)
    {
        this.likes = likes;
    }
    
    /**
     * Get Descuento
     * @return El descuento del producto
     */
    public int getDescuento()
    {
        return descuento;
    }
    
    /**
     * Get Name
     * @return Atributo homePart del producto
     */
    public String getHomePart()
    {
        return homePart;
    }
    
    /**
     * Get Likes
     * Retorna el número de likes de este post
     * @return número de likes
     */
    public int getLikes()
    {
        return likes;
    }
    
    //////////////////////////////////////////////////////////////////////////
    
    /**
    * Check whether a given String is a valid command word. 
    * @return true if a given string is a valid command,
    * false if it isn't.
    */
    public boolean isCommand(String aString)
    {
        int i = 0;
        while( i < validCommands.length) {
            if(validCommands[i].equals(aString)){
                return true;
            }
            i++;
        }
        return false;
    }
    
    /**
     * Guarda un un nuevo "like"
     */
    @Override 
    public void like()
    {
        likes++;
    }
    
    /** 
     * Guarda que un usuario ha eliminado su voto .
     */
    @Override
    public void unlike()
    {
        if (likes> 0) likes--;
    }
    
    /**
     * Metodo hashCode de Hogar
     * @return int resultado
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37*result + super.hashCode();
        result = 37*result + descuento.hashCode();
        result = 37*result + homePart.hashCode();
        result = 37*result + likes.hashCode();
     return result;
    }
    
     /**
     * Producto a String
     * @return El id, nombre y cantidad de stock
     */
    @Override
    public String toString()
    {
        return getID() + " " + getName() + " " 
                + getQuantity() + " " + getMinStock() + " " + getPrize() + " " + getHomePart() + " " + getLikes() + " " + getNumComents();
    } 
}
