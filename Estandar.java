import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

/**
 * Implentación de la clase Estandar.
 * 
 * @author Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */
public class Estandar extends Client
{
   
   /**
     * Constructor parametrizado de Client
     * @param id Identificador
     * @param nombre Nombre completo
     * @param edad Edad en años
     * @param localidad Ciudad 
     * @param manager StockManager asociado
     */
    public Estandar(int id, String nombre, int edad, String localidad, StockManager manager)
    {
        super(id, nombre, edad, localidad, manager);
        
    }

    /**
     * Prepara pedidos de los dos productos mas caros de sus favoritos
     * @return true si se ha preparado correctamente, false en caso contrario
     */  
    public boolean prepararPedido()
    {
       boolean preparado = false;
        
       ArrayList<Product> auxOrder 
            = new ArrayList<Product>(getFavoritos().values());
       Collections.sort(auxOrder);     
           
        if (auxOrder.size() > 0){
            addListaCompra(auxOrder.get(auxOrder.size()-1));
            if(auxOrder.size() > 1)
                addListaCompra(auxOrder.get(auxOrder.size()-2)); 
            preparado = true;
        }
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
             precio += (p.getPrize()*50); 
        }
        
        return precio;
    }
    
    /**
     * Pedidos de 50 unidades de los dos productos mas caros de sus favoritos, en caso de 
     * no haber stock de alguno de ellos, se realizará de los que haya disponibles.
     * @return true si almenos se ha ordenado un pedido de la list de favoritos
     */  
    public boolean realizarPedido()
    {  
        boolean pedido = false;
        int sizeList = getListaCompra().size();
        realizarComentario();        
        if (sizeList > 0){ 
            for (int i = 1; i <= sizeList; i++){
                getStockManager().deliveryOne(getId(),getListaCompra().get(sizeList-i), 50);
            }
            pedido = true;
        }
        setDineroGastado(getDineroGastado()+ obtenerPrecio());
        getListaCompra().clear();
        return pedido;
    }
    
    /**
     * Método que obtiene la puntuacion del comentario aplicando la siguiente fórmula:
     * (longitud de la cadena del nombre de producto % 5) + 1
     * @param nombre producto
     * @return int la puntuacion.
     */  
    public int puntuarComentario (String producto)
    {
        String cadena = producto;   
        int puntuacion= (cadena.length()%5)+1;       
        return puntuacion;
    }
    
    /**
     * Método que obtiene el comentario segun la puntuacion
     * @param puntuación,
     * @return String el comentario segun la puntuacion.
     */  
    public String obtenerComentario(int puntuacion)
    {
        String comment="";
        int op = puntuacion;
      switch (op) {
          case 1:
               comment="Bad product";
               break;
          case 2:
               comment="Not very good product";
               break;
          case 3:
               comment="Good product";
               break;
          case 4:
               comment="Very good product";
               break;
          case 5:
               comment="Excellent product";
               break;
        }
        return comment;
    }
        
    /**
     * Método que realiza un comentario sobre un producto comentable y le añade like o dislike si es preciso.
     * @return true si se realiza el comentario.
     */  
    public boolean realizarComentario()
    {   
        boolean realizado = false;
        int sizeList = getListaCompra().size();
        //si solo hay dos productos hacerlo mejor con bucle?? posible mejora
        if (sizeList > 0){
            
            Product prod1= getListaCompra().get(sizeList-1); 
            String comment1 = obtenerComentario(puntuarComentario(getListaCompra().get(sizeList-1).getName()));
            int puntuacion1 = puntuarComentario(getListaCompra().get(sizeList-1).getName());
            addComment(prod1, comment1, puntuacion1);
            
            if (puntuacion1 <= 2){
                   giveUnlike(prod1);
                }else if (puntuacion1 >=4)
                     giveLike(prod1);
            
            if(sizeList > 1){
                
                Product prod2 = getListaCompra().get(sizeList-2); 
                String comment2 = obtenerComentario(puntuarComentario(getListaCompra().get(sizeList-2).getName()));
                int puntuacion2 = puntuarComentario(getListaCompra().get(sizeList-2).getName());
                addComment(prod2, comment2, puntuacion2);
                
                if (puntuacion2 <= 2){
                   giveUnlike(prod2);
                }else if (puntuacion2 >=4)
                     giveLike(prod2);
                     
                realizado = true;
            }
        }
        return realizado;
    } 
    
    /**
     * Metodo hashCode de Estandar
     * @return int resultado
     */
    @Override
    public int hashCode()
    {
        int result = 17;
        result = 37*result + super.hashCode();
        return result;
    }
}
