
/**
 * Implentación de la clase Alimentacion.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public class Alimentacion extends Product implements Likeable
{
    private Integer descuento;
    private String expirationMoth;
    private Integer likes;

    /**
     * Constructor for objects of class Alimentacion
     */
    public Alimentacion(Integer id, String name, Integer quantity, Integer minStock, 
                        float prize, String expirationMoth)
     {
        super(id,name, quantity, minStock, prize);
        descuento = 10;
        this.expirationMoth = expirationMoth;
        likes = 0;
        super.setPrize((float)(prize - (prize*descuento/100)));        
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
     * Set Descuento
     * @param descuento El descuento de este producto
     */
    public void setDescuento( int descuento)
    {
        this.descuento = descuento;
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
     * Set getExpirationMoth
     * @param getExpirationMoth 
     */
    public void setExpirationMoth( String expirationMoth)
    {
        this.expirationMoth = expirationMoth;
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
     * Get expirationMoth
     * @return El descuento del producto
     */
    public String getExpirationMoth()
    {
        return expirationMoth;
    }
    
    /**
     * Retorna el número de likes de este post
     * @return número de likes
     */
    public int getLikes()
    {
        return likes;
    }
    
    /**
     * Metodo hashCode de Alimentacion
     * @return int resultado
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37*result + super.hashCode();
        result = 37*result + descuento.hashCode();
        result = 37*result + expirationMoth.hashCode();
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
                + getQuantity() + " " + getMinStock() + " " + getPrize() + " " + getExpirationMoth() + " " + getLikes();
    } 
   
}
