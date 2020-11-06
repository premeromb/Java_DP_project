
/**
 * Demonstrate the StockManager class.
 * The demonstration becomes properly functional as
 * the StockManager class is completed.
 * 
 * @author Grupo MartinRomero 
 * @author [Rodrigo Martin, Pablo Romero]
 * @version 2018.12.14
 */
public class StockDemo
{
    // The stock manager.
    private StockManager sww;
    

    /**
     * Create a StockManager and populate it with a few
     * sample products.
     */
    public StockDemo()
    {
        sww = StockManager.getInstance();

    }
    
    public void cargarDatos(){
        Product Ocio1 = new Ocio(1, "Nintendo Switch", 4, 2, 300);
        Product Ocio2 = new Ocio(2, "Xbox One", 5, 2, 220);
        Product Ocio3 = new Ocio(3, "Samsung Galaxy Tablet", 50, 8, 180);
        Product Ocio4 = new Ocio(4, "Wireless Headphone", 60, 5, 32);
        Product Ocio5 = new Ocio(5, "Kindle", 40, 10, 80);
        Product Ocio6 = new Ocio(6, "Amazon Echo", 10, 3, 100);

        Product Home1 = new Hogar(7, "Lamp", 5, 2, 17, "livingroom");
        Product Home2 = new Hogar(8, "Barbecue", 5, 1, 80, "garden");
        Product Home3 = new Hogar(9, "Towel", 10, 5, 9, "bathroom");
        Product Home4 = new Hogar(10, "Coffe Machine", 15, 10, 55, "kitchen");
        Product Home5 = new Hogar(11, "Nordic Filling", 2, 1, 60, "bedroom");
        Product Home6 = new Hogar(12, "Table", 5, 1, 100, "livingroom");
        Product Home7 = new Hogar(13, "Curtains", 2, 2, (float)40.5, "bedroom");
        Product Home8 = new Hogar(14, "Carpet", 3, 2, 80, "livingroom");

        Product Alimento1 = new Alimentacion(15, "Soft drink", 50, 30, (float)1.5, "January");
        Product Alimento2 = new Alimentacion(16, "Cookies", 100, 50, 2, "February");
        Product Alimento3 = new Alimentacion(17, "Rice", 80, 40, (float)1.5, "December");
        Product Alimento4 = new Alimentacion(18, "Bread", 50, 25, (float)1.2, "December");
        Product Alimento5 = new Alimentacion(19, "Coffee", 150, 100, 1, "November");
        Product Alimento6 = new Alimentacion(20, "Milk", 100, 50, (float)0.6, "December");

        Client client1 = new Estandar(1, "Rafa Nadal", 32, "Manacor", sww);
        Client client2 = new Vip(2, "Carolina Marin", 25, "Huelva", sww);
        Client client3 = new Estandar(3, "Jose Manuel Calderon", 37, "Villanueva de la Serena", sww);
        Client client4 = new Estandar(4, "Laia palau", 39, "Barcelona", sww);
        Client client5 = new Vip(5, "Javier Guerra", 35, "Segovia", sww);
        Client client6 = new Vip(6, "Ana Peleteiro", 22, "Riveira", sww);

        sww.addProductStock(Ocio1);
        sww.addProductStock(Ocio2);
        sww.addProductStock(Ocio3);
        sww.addProductStock(Ocio4);
        sww.addProductStock(Ocio5);
        sww.addProductStock(Ocio6);

        sww.addProductStock(Home1);
        sww.addProductStock(Home2);
        sww.addProductStock(Home3);
        sww.addProductStock(Home4);
        sww.addProductStock(Home5);
        sww.addProductStock(Home6);
        sww.addProductStock(Home7);
        sww.addProductStock(Home8);

        sww.addProductStock(Alimento1);
        sww.addProductStock(Alimento2);
        sww.addProductStock(Alimento3);
        sww.addProductStock(Alimento4);
        sww.addProductStock(Alimento5);
        sww.addProductStock(Alimento6);

        sww.addClient(client1);
        sww.addClient(client2);
        sww.addClient(client3);
        sww.addClient(client4);
        sww.addClient(client5);
        sww.addClient(client6);

        client1.addProductToFav("rafanint",Ocio1);
        client1.addProductToFav("rafaxbox",Ocio2);
        client1.addProductToFav("rafalamp",Home1);
        client1.addProductToFav("rafabarbe",Home2);
        client1.addProductToFav("rafatowel",Home3);
        client1.addProductToFav("rafamilk",Alimento6);

        client2.addProductToFav("caronint",Ocio1);
        client2.addProductToFav("carolamp",Home1);
        client2.addProductToFav("carobarbe",Home2);
        client2.addProductToFav("caromilk",Alimento6);

        client3.addProductToFav("josenint",Ocio1);
        client3.addProductToFav("josebarbe",Home2);

        client4.addProductToFav("laianint",Ocio1);

        client5.addProductToFav("javibarbe",Home2);

        client6.addProductToFav("anaxbox",Ocio2);
        client6.addProductToFav("anabarbe",Home2);
        client6.addProductToFav("anatowel",Home3);
        client6.addProductToFav("anabread",Alimento4);
        client6.addProductToFav("anacoffee",Alimento5);
        client6.addProductToFav("anamilk",Alimento6);

    }

    public void accionCLienteEstandar(int turno, Estandar cliente){
        sww.escribir("<for each turn>");
        sww.escribir("(turn:<"+turno+">)");
        Estandar cEstandar = (Estandar) cliente;
        cEstandar.prepararPedido();
        cEstandar.realizarPedido();
        sww.escribir("<in StockManager for each client who makes an order>");
    }

    public void accionCLienteVip(int turno, Vip cliente)
    {
        sww.escribir("<for each turn>");
        sww.escribir("(turn:<"+turno+">)");
        Vip cVip = (Vip) cliente;
        cVip.prepararPedido();
        cVip.realizarPedido();
        sww.escribir("<in StockManager for each client who makes an order>");
    }

    public void simulacion()
    {
        int turno=1;
        int j=0;
        //carga los datos
        cargarDatos();
        //empieza la simulacion
        while (j<sww.getListaClientes().size() && turno <= 10){
           
            if(sww.getListaClientes().get(j) instanceof Vip){
                Vip cVip = (Vip) sww.getListaClientes().get(j);
                accionCLienteVip(turno,cVip); 
            }
            else if(sww.getListaClientes().get(j) instanceof Estandar){
                Estandar cEstandar = (Estandar) sww.getListaClientes().get(j);
                accionCLienteEstandar(turno,cEstandar);
            }           
            j++;
            turno++;
            if (j == sww.getListaClientes().size()){
                j=0;
            }
        }
        sww.escribir("<at the end of simulation>");
        sww.obtenerProductosVendidosEnStock();
        sww.obtenerProductoMasVendido();
        sww.obtenerProductoMasComentado();
        sww.obtenerClienteMasGasto();     
        System.out.println("===========   FIN DE LA SIMULACIÓN   ===========");
        System.out.println("Fichero de salida <registro.log> creado");
        
    }

    
}
