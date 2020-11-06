import java.util.ArrayList;

/**
 * Implentación de la clase Pedido.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public class Pedido
{ 
    private Client client;
    private ArrayList<Product> listaProductos;
    private Integer cantidad;

    /**
     * Constructor for objects of class Pedido
     */
    public Pedido(Client c, ArrayList<Product> listaP, int cantidad)
    {
        this.client = c;
        listaProductos = new ArrayList<Product>();
        this.cantidad = cantidad;
        listaProductos = listaP;
             
    }
    
    /**
     * Get client
     * @return client
     */
    public Client getClient (){     
        return client;
    }
    
    /**
     * Get listaProductos
     * @return listaProductos
     */
    public ArrayList<Product> getListaProductos(){     
        return listaProductos;
    }
    
    /**
     * Get cantidad
     * @return cantidad
     */
    public int getCantidad(){     
        return cantidad;
    }

    /**
     * Return the full text and details of the comment, including 
     * the client, listapedido and cantidad.
     */
    public String toString()
    {
        return "Cliente: " + client.toString() + "  \nLista de pedido: " + listaProductos.toString() +"\n" + "Cantidad: " + cantidad;
        
    }
    
    /**
    * Hashcodetechnique taken from
    * Effective Java by Joshua Bloch.
    */
    @Override
    public int hashCode()
    {
        int result= 17;
        result= 37 * result+ client.hashCode();
        result= 37 * result+ listaProductos.hashCode();  
        result= 37 * result+ cantidad.hashCode(); 
        return result;
    }
  
}
