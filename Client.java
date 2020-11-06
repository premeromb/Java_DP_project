import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Recoge los datos del cliente
 * 
 * @author Grupo MartinRomero 
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */

public class Client
{
    //Identificador
    private Integer id;
    //Nombre del Cliente
    private String nombre;
    //Edad del cliente
    private Integer edad;
    //Ciudad de residencia
    private String localidad;
    //StockManager asociado
    private StockManager manager;
    //Colección de productos favoritos
    private HashMap <String, Product>  productFav;
    //Lista de productos a comprar
    private ArrayList<Product> listaCompra;
    //Acumulador de dinero gastado
    private Float dineroGastado;

      
    /**
     * Constructor por defecto de Client
     */
    public Client()
    {
        this.id = 0;
        this.nombre = " ";
        this.edad = 0;
        this.localidad = " ";
        this.manager = null; 
        this.dineroGastado = (float) 0;
        productFav = new HashMap<>();
        listaCompra = new ArrayList<Product>();
    }
    
    /**
     * Constructor parametrizado de Client
     * @param id Identificador
     * @param nombre Nombre completo
     * @param edad Edad en años
     * @param localidad Ciudad 
     * @param manager StockManager asociado
     */
    public Client(Integer id, String nombre, Integer edad, String localidad, StockManager manager)
    {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.localidad = localidad;
        this.manager = manager; 
        this.dineroGastado = (float) 0;
        productFav = new HashMap<>();
        listaCompra = new ArrayList<Product>();
    }
    
    /**
     * Get id
     * @return Identificador
     */
    public int getId(){
        return id;
    }
    
    /**
     * Get nombre
     * @return Nombre
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Get edad
     * @return Edad
     */
    public float getDineroGastado(){
        return dineroGastado;
    } 
    
    /**
     * Get edad
     * @return Edad
     */
    public int getEdad(){
        return edad;
    }
    
    /**
     * Get localidad
     * @return Localidad
     */
    public String getLocalidad(){
        return localidad;
    }
    
    /**
     * Get StockManager
     * @return StockManager
     */
    public StockManager getStockManager(){
        return manager;
    }
    
    /**
     * Get productFav
     * @return HashMap
     */
    public HashMap getFavoritos(){
         return productFav;
    }
    /**
     * Get listaCompra
     * @return ArrayList<Product> listaCompra
     */
    public ArrayList<Product> getListaCompra(){
        return listaCompra;
    }
    
    
    /**
     * Set id
     * @param _id Identificador
     */
    public void setId(int _id){
        id = _id;
    }
    
    /**
     * Set nombre
     * @param _nombre Nombre
     */
    public void setNombre(String _nombre){
        nombre = _nombre;
    }
    
    /**
     * Set nombre
     * @param _nombre Nombre
     */
    public void setDineroGastado(float _dineroGastado){
        dineroGastado = _dineroGastado;
    }
    
    /**
     * Set edad
     * @param _edad Edad
     */
    public void setEdad(int _edad){
        edad = _edad;
    }
    
    /**
     * Set localidad
     * @param _localidad Localidad
     */
    public void setLocalidad(String _localidad){
        localidad = _localidad;
    }
    
    /**
     * Set StockManager
     * @param _manager StockManager
     */
    public void setStockManager(StockManager _manager){
        manager = _manager;
    }
    
    /**
     * Set productFav
     * @param _productFav HashMap
     */
    public void setHashMap(HashMap _productFav){
        productFav = _productFav;
    }
    
    /**
     * Set listaCompra
     * @param _productFav HashMap
     */
    public void setListaCompra(HashMap _productFav){
        listaCompra.addAll(_productFav.values());
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
        
    /**
     * Añade un producto a lista de favoritos
     * @param nombre Nombre con el que el cliente lo identifica
     * @param producto Puntero al producto
     */
    public void addProductToFav(String nombre, Product producto)
    {
        productFav.put(nombre, producto);
       
    }
    
    /**
     * Añade un producto a lista de compra
     * @param producto Puntero al producto
     */
    public void addListaCompra(Product producto)
    {
        listaCompra.add(producto);   
    }
    
    /**
     * Elimina un producto de lista de favoritos
     * @param nombre Nombre con el que el cliente lo identifica
     * @return true si se ha eliminado el producto de favoritos, false si no
     */
    public boolean deleteProductFav(String nombre)
    {
        if (existeProdFav(nombre)){
            productFav.remove(nombre);
            return true;
        }else 
            return false;
    }
    
    /**
     * Pedido del producto favorito indicado
     * @param nombre Nombre con el que el cliente identifica al producto
     * @param cantidad Número de productos a pedir
     * @return true si se ha pedido el producto, false si no
     */
    public boolean hacerPedido(String nombre, int cantidad)
    {
        //Comprueba que el Producto existe en lista de favoritos
        if(existeProdFav(nombre)){
            return manager.delivery(productFav.get(nombre).getID() , cantidad);
        }else 
            return false;
    }
    
    /**
     * Añade comentario al producto indicado
     * @param nomProd Producto
     * @param text Comentario a almacenar
     * @param rating calificación de 1 a 5 que se le asigna
     * @return true si se ha añadido el comentario del producto, false si no
     */
    public boolean addComment(Product nomProd, String text, int rating)
    {
        
        if(!ratingInvalid(rating)){
                Comment com = new Comment(this.nombre,text, rating);
                if(nomProd instanceof CommentedProduct){
                    CommentedProduct commen = (CommentedProduct) nomProd;
                    commen.addComment(this.id, com);
                }
                return true;
        }else
            return false;
        }
        
    /**
     * Añade like al producto indicado
     * @param Product p producto a likear
     * @return true si se ha añadido el like, false si no se ha añadido
     */    
    public boolean giveLike(Product p)
    {
        boolean likeable = false;
        if(p instanceof Likeable){
                    Likeable prod = (Likeable) p;
                    prod.like();
                    likeable = true;
                }
        return likeable;
    }
    
    /**
     * Añade dislike al producto indicado
     * @param Product p producto a likear
     * @return true si se ha añadido el dislike, false si no se ha añadido
     */  
    public boolean giveUnlike(Product p)
    {
        boolean likeable = false;
        if(p instanceof Likeable){
                    Likeable prod = (Likeable) p;
                    prod.unlike();
                    likeable = true;
                }
        return likeable;
    }
        
    /**
     * Check whether the given rating is invalid. Return true if it is invalid.
     * Valid ratings are in the range [1..5].
     * return true si el rango es invalido.
     */
    private boolean ratingInvalid(int rating)
    {
        return rating < 1 || rating > 5;
    }
    
    /**
     * Comprueba si existe el producto favorito
     * * @param nomProduc Nombre del producto
     */
    public boolean existeProdFav(String nomProd)
    {
        if( productFav.get(nomProd) != null)
            return true;
        else 
            return false;
            
    }
    
    /**
     * Transforma en un array todos los productos que tiene en fav el cliente en su hashmap.
     * return ArrayList<Product> prodFav 
     */
    public ArrayList<Product> favToArray()
    {
       ArrayList<Product> prodFav = new ArrayList<Product>(productFav.values()); 
       
       return prodFav;
    }
    
    /**
     * Producto a String
     * @return El id, nombre, edad, localidad y dinero gastado total
     */
    public String toString()
    {
        return id + " " + nombre + " " 
                + edad + " " + localidad;
    }
    
    /**
     * Método que compara dos clientes
     * @param prod Producto con el que comparar
     */
    public boolean equals (Client cliente)
    {
        if ( this.id == cliente.getId() && this.nombre.equals(cliente.getNombre()) && 
             this.edad == cliente.getEdad() && this.localidad.equals(cliente.getLocalidad()))
            return true;
        else 
            return false;
    }
    
    /**
    * Hashcodetechnique taken from
    * Effective Java by Joshua Bloch.
    */
    @Override
    public int hashCode()
    {
        int result= 17;
        result= 37 * result+ id.hashCode();
        result= 37 * result+ nombre.hashCode();
        result= 37 * result+ edad.hashCode();
        result= 37 * result+ localidad.hashCode();
        return result;
    }
    
}
