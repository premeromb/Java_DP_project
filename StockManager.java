import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.HashSet;
import java.io.*;


/**
 * Control del stock en un negocio
 * El stock está compuesto por 0 o más productos
 * Empresa dedicada a venta de objetos realizados por impresora 3D
 * 
 * @autor Grupo MartinRomero
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */
public class StockManager
{
    //Lista de Productos en stock
    private HashSet<Product> stock;
    private ArrayList <Client> listaClientes;
    private static StockManager instance = null;
    private ArrayList<Pedido> listaPedidos;
    private BufferedWriter bw;
    /**
     * Constructor de StockManager
     */
    private StockManager()
    {   
        listaClientes = new ArrayList<Client>();
        listaPedidos = new ArrayList<Pedido>();
        stock = new HashSet<Product>();
        
        try
        { 
            bw = new BufferedWriter(new FileWriter("registro.log"));
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
    }
    
    /**
     * Set stock
     * @param stock HashSet
     */
    public void setStock(HashSet<Product> stock)
    {
        this.stock = stock;
    }
    
    /**
     * Set listaClientes
     * @param listaClientes ArrayList
     */
    public void setListaClientes(ArrayList <Client> listaClientes)
    {
        this.listaClientes = listaClientes;
    }

    /**
     * Get Stock
     * return HashSet stock
     */
    public HashSet<Product> getStock()
    {
        return this.stock;
    }

    /**
     * Get listaClientes
     * return ArrayList listaClientes
     */
    public ArrayList<Client> getListaClientes()
    {
        return this.listaClientes;
    }
    
    /**
     * Get StockManager instance
     * return StockManager instance
     */
    public static StockManager getInstance()
    {
        if (instance == null)
            instance= new StockManager();
        return instance;
    }
    
    //////////////////////////////////////////////////////////////////////////
    
    /**
     * Obtiene y muestra una estructura con los productos vendidos en stock
     * @return pVendidos HashMap de Product
     */
    public HashMap <Product, Integer> obtenerProductosVendidosEnStock(){
        
        HashMap <Product, Integer>  pVendidos = new HashMap<>(); 
        int acumVenta = 0;
        for (Pedido ped: listaPedidos){ 
             for (Product p: ped.getListaProductos()){ 
                 if (existProduct(p)){
                     if (pVendidos.containsKey(p)){
                         acumVenta = pVendidos.get(p) + ped.getCantidad();
                         pVendidos.put(p,acumVenta);
                     }else{
                         pVendidos.put(p,ped.getCantidad());
                     }
                 }
             }
        }
        escribir("<for each product sold>");
         for (HashMap.Entry <Product, Integer> entry : pVendidos.entrySet())  {
             escribir("(soldProduct:<" + entry.toString() + ">)");
           if(entry instanceof CommentedProduct){
               CommentedProduct commen = (CommentedProduct) entry;
               escribir("<for each comment  included in  product>");
               for (Comment c: commen.getListaComentOrdenada()){                       
                   escribir("(comment:<" + c.toString() + ">)");
               }
           }
        } 
        
        return pVendidos;
    }
    
    /**
     * Obtiene el producto más vendido que se encutre en stock
     * @return productoTop Prodcuto más vendido
     */
    public Product obtenerProductoMasVendido(){
        
         HashMap <Product, Integer>  aux = new HashMap<>();
         aux = obtenerProductosVendidosEnStock();
         Product productoTop = null;
         int topVenta = 0;
         for (HashMap.Entry <Product, Integer> entry : aux.entrySet())  {
             if (entry.getValue() > topVenta){
                 productoTop = entry.getKey(); 
                 topVenta = entry.getValue();
             }
        } 
         
         escribir("(mostSoldProduct:<"+productoTop.toString()+" "+ topVenta + ">)");
        return productoTop;   
    }
    
    /**
     * Obtiene y muestra el producto más comentado, solo se comprueban los 
     * comentarios de los productos que pertenezcan a un pedido tal como 
     * indican las pautas (punto 4).
     * @return producto más comentado
     */
    public Product obtenerProductoMasComentado(){
        
        HashMap <Product, Integer>  aux = new HashMap<>();
        aux = obtenerProductosVendidosEnStock();
        Product productoTopComent = null;
        int topComent = 0;
        for (HashMap.Entry <Product, Integer> entry : aux.entrySet())  {
            if(entry.getKey() instanceof CommentedProduct){
                    CommentedProduct commen = (CommentedProduct) entry.getKey();
                    if (commen.getNumComents() > topComent){
                        productoTopComent = commen;
                        topComent = commen.getNumComents();
                    }
            }           
        }      
        escribir("(mostCommentedProduct:<"+productoTopComent.toString()+">)");
        return productoTopComent; 
        
    }
    
    /**
     * Obtiene y muestra el cliente que más dinero ha gastado 
     * @return clienteTop Cliente obtenido
     */
    public Client obtenerClienteMasGasto(){
        Client clienteTop = null;
        float topGasto = 0;
        for (Client cliente: listaClientes){ 
            if (cliente.getDineroGastado() > topGasto){
                topGasto = cliente.getDineroGastado();
                clienteTop = cliente;
            }
        }
        escribir("(clientWhoSpentMore:<" + clienteTop.toString() + topGasto + ">)");
        return clienteTop;
    }
   
    /**
     * Añade un producto a la lista e productos en stock 
     * @param item Producto a añadir
     * @return boolean true si se ha completado la operación
     */
    public boolean addProductStock(Product item)
    {
        //Mejora para id automatico en la siguiente entrega, evitar repeticiones y que sea < 0
        if (!existProduct(item)) {
            if (item.getPrize() < 0){
                item.setPrize(40);
                System.out.println("Error al introducir precio, este será puesto por defecto a 40€.");
            }
            if (item.getQuantity() < 0){
                item.setQuantity(5);
                System.out.println("Error al introducir Stock, este será puesto por defecto a 5 unidades.");
            }
            if (item.getMinStock() < 0){
                item.setMinStock(1);
                System.out.println("Error al introducir Stock, este será puesto por defecto a 1 unidad.");
            } 
                //añadimos el item.  
                stock.add(item);
                return true;
        }else
            return false;
        
    }
    
    /**
     * Borra, si existe, un elemento de la lista de stock
     * @param item Producto a borrar
     * @return boolean true si el elemento ha sido borrado
     */
    public boolean deleteProductStock(Product item)
    {
        if(existProduct(item)){
            stock.remove(item);
              return true;
        }else
        return false;    
    }
    
    /**
     * Repone el stock de un producto
     * @param prod Producto a modificar
     * @return boolean true si se ha realizado la operación
     */
    private boolean reponerStock(Product prod)
    {
        return prod.aumentarStock();
    }
    
    /**
     * Añade nuevo cliente a listaClientes
     * @param cliente Cliente a añadir
     * @return boolean true si se añade a la estructura
     */
    public boolean addClient(Client cliente)
    {
        if (!existClient(cliente)) {
             listaClientes.add(cliente);
             return true;
        }else
        return false;
    }
    
    /**
     * Borra, si existe, un cliente
     * @param item Cliente a borrar
     * @return boolean true si el elemento ha sido borrado
     */
    public void deleteClient(Client cliente)
    {
        try
        {
          listaClientes.remove(cliente);
        }
        catch (Exception e)
        {   
            System.out.println(e);
        }
    }
    
    /**
     * Almacena un pedido en la lista de Pedidos completados
     * @param idClient Identificador del cliente
     * @param productos Lista de productos vendidos
     * @param cant Cantidad vendida de cada producto
     */
     public void anotarPedido(int idClient, ArrayList <Product> productos, int cant)
    {
        Pedido pedido = new Pedido(findClient(idClient), productos, cant);
        listaPedidos.add(pedido);
    }
        
    /**
     * Recibe y gestiona el pedido de un producto
     * Si stock tras pedido menor que el mínimo, lo aumentamos haciendo un
     * "pedido" al proveedor del producto con la cantidad mínima del producto.
     * @param id Identificador del producto.
     * @param amount Cantidad solicitada
     * @return boolean true si se ha completado la compra
     */
    public boolean delivery(int id, int amount)
    {
        Product prod = findProduct(id);
        
        if (findProduct(id).decrementQuantity(amount)){//hay stock del producto
            return true; 
        }
        else{ //no hay stock del producto
             return false;
        } 
    }
   
    /**
     * Recibe un pedido de un cliente con el producto a comprar y la cantidad
     * que  quiere comprar
     * @param idClient Identificador del cliente
     * @param p Producto a comprar
     * @param cant cantidad del prodcuto a comprar
     * @return boolean true si se ha completado la compra
     */
    public boolean deliveryOne(int idClient, Product p, int cant)
    {
       boolean realizado = false;
       boolean haRepuesto = false;
       ArrayList<Product> listaAux= new ArrayList<Product>();
       
       escribir("(client:<" + findClient(idClient).toString() + ">)");
       escribir("<for each product included in client order>");
       escribir("(product:<" + p.toString() + cant + ">)");
       
       if(p instanceof CommentedProduct){
           CommentedProduct commen = (CommentedProduct) p;
           escribir("<for each comment  included in  product>");
           for (Comment c: commen.getListaComentOrdenada()){                       
               escribir("(comment:<" + c.toString() + ">)");
           }
       }
       
       if (delivery(p.getID(), cant)){
           listaAux.add(p);
           if(reponerStock(p)){//comprueba si el stock del producto se ha quedado por debajo de stock min
              haRepuesto=true;
           } 
           realizado= true;
       }
       
       if (realizado == true){
            anotarPedido(idClient, listaAux, cant);
            if(haRepuesto == true)
            {
                escribir("(the order is done and these products need to be replenished)");                    
                escribir("(product:<" + p.toString() + ">)");
            }
            else
            {
                escribir("(the order is done)");
            }
       }
       
       return realizado;
    }
    
    /**
     * Recoge un pedido de un clinte con una lista de productos
     * de cada uno de ellos se compra una unidad
     * @param idClient Identificador del cliente
     * @param listaCompra ArrayList de Productos
     * @return boolean true si se ha vendido almenos una unidad
     */
    public boolean deliveryFav(int idClient, ArrayList <Product> listaCompra)
    {
       boolean realizado = false;
       boolean haRepuesto = false;
       ArrayList<Product> pRepuestos= new ArrayList<Product>();
       int cantidad = 1;
       escribir("(client:<" + findClient(idClient).toString() + ">)");
       escribir("<for each product included in client order>");
       ArrayList<Product> listaAux= new ArrayList<Product>();
       for (Product p: listaCompra){ 
               escribir("(product:<" + p.toString() + cantidad + ">)");
               if(p instanceof CommentedProduct){
                   CommentedProduct commen = (CommentedProduct) p;
                   escribir("<for each comment  included in  product>");
                   for (Comment c: commen.getListaComentOrdenada()){                       
                       escribir("(comment:<" + c.toString() + ">)");
                   }
                }
                
                if(delivery(p.getID(), cantidad)){
                     listaAux.add(p);
                     if(reponerStock(p)){//comprueba si el stock del producto se ha quedado por debajo de stock min
                         haRepuesto=true;
                         pRepuestos.add(p);
                        } 
                     if (realizado == false){
                         realizado = true;
                     }            
                }
        }
        
       if (realizado == true){
            anotarPedido(idClient, listaAux, 1);
            if(haRepuesto == true)
            {
                escribir("(the order is done and these products need to be replenished)");
                for (Product c:pRepuestos){                       
                       escribir("(product:<" + c.toString() + ">)");
                   }
            }
            else
            {
                escribir("(the order is done)");
            }
       }
       
       return realizado;
    }
    
    /**
     * Try to find a product in the stock with the given id.
     * @param id Identificador del producto
     * @return The product, or null if there is none
     *         with a matching ID.
     */
    public Product findProduct(int id)
    {
        Iterator <Product> it = stock.iterator();
        while (it.hasNext()){
            Product p = it.next();
            
            if(id == p.getID()){
                return p;
            }
        }
        return null;
    }
    
    /**
     * Locate a product with the given ID, and return how
     * many of this item are in stock. If the ID does not
     * match any product, return zero.
     * @param id The ID of the product.
     * @return The quantity of the given product in stock.
     */
    public int numberInStock(int id)
    {
        int cantidad = findProduct(id).getQuantity();
        return cantidad;
    }
    
    /**
     * Prueba si el producto existe en la lista de stock
     * @param prod Producto a buscar
     */
    public boolean existProduct(Product prod)
    {
        Iterator <Product> it = stock.iterator();
        while (it.hasNext()){
            Product p = it.next();
            
            if(p.equals(prod)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Print details of all the products.
     */
    public void printProductDetails()
    {   
        System.out.println();
        System.out.println("==== LISTADO DE PRODUCTOS EN STOCK ====");
        for (Product p: stock){
            System.out.println(p.toString());            
        }
        System.out.println("=======================================");
        System.out.println();
    } 
    
    /**
     * Prueba si el cliente existe en la lista de clientes
     * @param client Cliente a buscar
     * @return boolean true si existe
     */
    public boolean existClient(Client cliente)
    {
        Iterator <Client> it = listaClientes.iterator();
        while (it.hasNext()){
            Client c = it.next();
            
            if(c.equals(cliente)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Prueba si el cliente existe en la lista de clientes
     * @param id Identificador del cliente
     * @return boolean true si existe
     */
    public boolean existClient(int id)
    {
        Client c = new Client();
        Iterator <Client> it = listaClientes.iterator();
        while (it.hasNext()){
            c = it.next();
            
            if(c.getId() == id){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Busca un cliente por su identificador, si existe lo devuelve 
     * @param id Identificador de cliente
     * @return Cliente
     */
    public Client findClient(int id)
    {
        Client c = new Client();
        Iterator <Client> it = listaClientes.iterator();
        while (it.hasNext()){
            c = it.next();
            
            if(c.getId() == id){
                return c;
            }
        }
        return c;
    }
    
    
    //PARA REALIZAR EL LOG///
    
    public void escribir(String mensaje) {
        try
        {
            escribeFichero(bw, mensaje);
            bw.newLine();            
            //Guardamos los cambios del fichero
            bw.flush();
        }catch(IOException e){
            System.out.println("Error E/S: "+e);
        }
    }
    public void escribeFichero(BufferedWriter bw, String mensaje) throws IOException{
        //Escribimos en el fichero
        bw.write(mensaje);
    }
    
}
