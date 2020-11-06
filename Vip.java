import java.util.ArrayList;

/**
 * Implentación de la clase Vip.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public class Vip extends Client
{
    
    /**
     * Constructor parametrizado de Client
     * @param id Identificador
     * @param nombre Nombre completo
     * @param edad Edad en años
     * @param localidad Ciudad 
     * @param manager StockManager asociado
     */
    public Vip(Integer id, String nombre, Integer edad, String localidad, StockManager manager)
    {
        super(id, nombre, edad, localidad, manager);
        
    }
    
    /**
     * Prepara pedido de los productos favoritos
     * @return true si se ha preparado correctamente, false en caso contrario
     */  
    public boolean prepararPedido()
    {
        boolean preparado = false;
        setListaCompra(getFavoritos());
        preparado = true;
        return preparado;
    }
    
    /**
     * obtener el precio total del pedido
     * @return precio total del pedido
     */  
    public float obtenerPrecio()
    {
        float precio = 0;
        for (Product p: getListaCompra()){ 
             precio += p.getPrize(); 
        }
        
        return precio;
    }
    
    /**
     * Pedido de una unidad de todos los productos en lista de favoritos, en caso de 
     * no haber stock de alguno de ellos, se realizará de los que haya disponibles.
     * @return true si almenos se ha ordenado un pedido de la list de favoritos
     */  
    public boolean realizarPedido()
    {
      boolean realizado = false;
      setDineroGastado(getDineroGastado()+ obtenerPrecio());
      realizarComentario();
      realizado = getStockManager().deliveryFav(getId(), getListaCompra());  
      getListaCompra().clear();
      return realizado;
    }
    
    /**
     * Método que realiza un comentario sobre un producto comentable de la lista de fav.
     * @return true si se realiza el comentario.
     */  
    public boolean realizarComentario()
    {
        boolean realizado = false;
        int puntuacion = 4;
        String comentario = "I really like this product";
        
        for (Product p: getListaCompra()){ 
             addComment(p, comentario, puntuacion);
             giveLike(p);
        }
        
        return realizado;
    } 
    
    /**
     * Metodo hashCode de Vip
     * @return int resultado
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37*result + super.hashCode();
        return result;
    }
    
    /**
     * Producto a String
     * @return El id, nombre, edad, localidad y dinero gastado total
     */
    @Override
    public String toString()
    {
        return super.toString();
    }
    
}