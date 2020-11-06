import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Implentación de la clase abstracta CommentedProduct.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public abstract class CommentedProduct extends Product
{
    //Coleción de comentarios de clientes
    private HashMap <Integer, Comment> comClient;
    
    /**
     * Constructor for objects of class CommentedProduct
     */
    public CommentedProduct(Integer id, String name, Integer quantity,
                        Integer minStock, float prize)
    {
        super(id,name, quantity, minStock, prize); 
        comClient = new HashMap<>();
    }
    
    /**
     * Retorna numero de comentarios
     * @return int numero de comentarios
     */
    public int getNumComents(){     
        return comClient.size();
    }
    
    /**
     * Añadir comentario sobre el producto
     * @param nombreC nombre del cliente
     * @param com Comentario a añadir
     * @return true si ha añadido el comentario, false si no
     */
    public boolean addComment(int id,Comment com)
    {
        if (!comClient.containsKey(id)){
            comClient.put(id, com);
            return true; 
        }
        return false;
               
    }
        
    /**
     * Listar los comentarios del producto
     */
    public void listComment()
    {
     for (HashMap.Entry <Integer, Comment> entry : comClient.entrySet())  {
          System.out.println(entry.getValue().toString());   
      }    
    }
    
    /**
     * Return the full text and details of the comment, including 
     * the comment text, author and rating.
     */
    public String getFullDetailsOrdenados()
    {
        String details = "";
        ArrayList<Comment> auxOrder = new ArrayList<Comment>(comClient.values());     
        
       Collections.sort(auxOrder);
       if (auxOrder.size() != 0){
        for (Comment c: auxOrder){ 
            details = details + c.toString() + "\n";

        }
       }else {
        details = "==== No tiene comentarios ====";
       }
       return details;
    }
    
    /**
     * Return the full text and details of the comment, including 
     * the comment text, author and rating.
     */
    public ArrayList<Comment> getListaComentOrdenada()
    {
        
       ArrayList<Comment> auxOrder = new ArrayList<Comment>(comClient.values());     
        
       Collections.sort(auxOrder);

       return auxOrder;
    }
    
    /**
     * Producto a String
     * @return El Producto , nombre, cantidad de stock y precio
     */
    @Override
    public String toString()
    {
        return "Producto: " + getID() + ": " + getName() + " cantidad de stock: " 
                + getQuantity() + " StockMin: " + getMinStock() + " precio: " + getPrize() + "\n Lista comentarios: \n" + getFullDetailsOrdenados();
    } 
    
    /**
     * Metodo hashCode de CommentedProduct
     * @return int resultado
     */
    @Override
    public int hashCode()
    {
       int result = 17;
       result = 37*result + super.hashCode();
       result = 37*result + comClient.hashCode();
       return result;
    } 
}
