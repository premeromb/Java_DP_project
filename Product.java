import java.util.HashMap;
/**
 * Implentación de los productos que gestiona la compañia.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */
public abstract class Product implements Comparable<Product>
{
    // An identifying number for this product
    private Integer id;
    // The name of this product
    private String name;
    // The quantity of this product in stock
    private Integer quantity;
    // The min quantity of this product in stock
    private Integer minStock;
    //The price of this product
    private Float prize;
     /**
     * Constructor por parámetros de la  clase Product
     * @param id The product's identifying number
     * @param name The product's name
     * @param quantity The size of this product
     * @param minStock the materias who is made
     * @param prize the color of this product 
     */
    public Product(Integer id, String name, Integer quantity, Integer minStock,
                    float prize)
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.minStock = minStock;
        if (prize < 0){
            throw new IllegalArgumentException("Precio Erroneo");
        }else{
            this.prize = prize;
        }
        
    }

    /**
     * Get Id
     * @return The product's id
     */
    public Integer getID()
    {
        return id;
    }

    /**
     * Get Name
     * @return The product's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get Quantity
     * @return The quantity in stock
     */
    public Integer getQuantity()
    {
        return quantity;
    }
    
    /**
     * Get MinStock
     * @return The minimun quantity in stock
     */
    public Integer getMinStock()
    {
        return minStock;
    }

    /**
     * Get Prize
     * @return The product's color
     */
    public Float getPrize()
    {
        return prize;
    }
    
    /**
     * Set Id
     * @param name The product's id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    /**
     * Set Name
     * @param name The product's name
     */
    public void setName(String name)
    {
        this.name = name;
    }
  
    /**
     * Set Quantity
     * @param name The product's quantity
     */
    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
    
    /**
     * Set MinStock
     * @param name The product's minStock
     */
    public void setMinStock(Integer minStock)
    {
        this.minStock = minStock;
    }
  
    /**
     * Set Prize
     * @param name The product's prize
     */
    public void setPrize(float prize)
    {
        this.prize = prize;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Vende una unidad de este producto
     * @return true si ha realizado el decremento, false si no
     */
    public boolean sellOne()
    {
        if(quantity > 0) {
            quantity--;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Decrementa el stock del producto según la cantidad indicada
     * @param amount cantidad a decrementar
     * @return true si ha realizado el decremento, false si no
     */
    public boolean decrementQuantity(int amount)
    {
        if(quantity > 0 && (quantity - amount) >= 0 ) {
            quantity -= amount;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Restock with the given amount of this product.
     * The current quantity is incremented by the given amount.
     * @param amount The number of new items added to the stock.
     *               This must be greater than zero.
     * @return true si ha realizado el incremento, false si no
     */
    public boolean increaseQuantity(int amount)
    {
        if(amount > 0) {
            quantity += amount;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Aumenta el stock para que esté por encima del mínimo fijado
     * @return true si ha realizado el incremento, false si no
     */
    public boolean aumentarStock()
    {
        if(quantity < minStock){
            increaseQuantity(minStock);
            return true;
        }else
            return false;
        
    }
            
    /**
     * Producto a String
     * @return El id, nombre y cantidad de stock
     */
    public String toString()
    {
        return id+ " " + name + " " 
                + quantity + " " + minStock + " " + prize;
    }
    
    /**
     * Metodo hashCode de Product 
     * @return int resultado
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37*result + id.hashCode();
        result = 37*result + name.hashCode();
        result = 37*result + quantity.hashCode();
        result = 37*result + minStock.hashCode();
        result = 37*result + prize.hashCode();
        return result;
    }
    
    /**
     * Método que compara dos productos
     * @param prod Producto con el que comparar
     */
    public boolean equals (Product prod)
    {
        if (this.id.equals(prod.id) && this.name.equals(prod.name) && 
            this.quantity.equals(prod.quantity)&& this.minStock.equals(prod.minStock) 
            && this.prize.equals(prod.prize))
            return true;
        else 
            return false;
    }
    
    /**
     * Método que compara dos productos por precio
     * @param prod Producto con el que comparar
     * return precio comparado
     */
    @Override
    public int compareTo(Product p)
    {
        return Float.compare(prize, p.getPrize());
    }
    
}
