/**
 * Implentación de la clase Ocio.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public class Ocio extends CommentedProduct
{
   
    private Integer incremento;

    /**
     * Constructor for objects of class Ocio
     */
    public Ocio(Integer id, String name, Integer quantity, Integer minStock, 
                        float prize)
     {
        super(id,name, quantity, minStock, prize);
        incremento = 20;
        super.setPrize((float)(prize + (prize * incremento / 100)));
    }
    
    /**
     * Set incremento
     * @param incremento El incremento de este producto
     */
    public void setIncremento( int incremento)
    {
        this.incremento = incremento;
    }
    
    /**
     * Get incremento
     * @return El incremento del producto
     */
    public int getIncremento()
    {
        return incremento;
    }
    
     /**
     * Producto a String
     * @return El id, nombre y cantidad de stock
     */
    @Override
    public String toString()
    {
        return getID() + " " + getName() + " " 
                + getQuantity() + " " + getMinStock() + " " + getPrize() + " " + getNumComents();
    } 
    
    /* Metodo hashCode de Ocio
     * @return int resultado
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37*result + super.hashCode();
        result = 37*result + incremento.hashCode();
        
     return result;
    }
    
}

    
