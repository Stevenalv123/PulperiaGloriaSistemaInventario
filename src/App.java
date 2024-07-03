import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {
    //#region
    static String nombreProducto, fechaVenciProducto;
    static int codigoProducto, stockProducto;
    static double costoProducto, precioProducto;
    static Scanner sc = new Scanner(System.in);
    static LocalDate hoy = LocalDate.now();
    static DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static ArrayList<Producto> listaProductos=new ArrayList<Producto>();
    static ArrayList<Venta> listaVentas=new ArrayList<Venta>();
    //#endregion
    public static void main(String[] args) throws Exception {
        System.out.println("");
        System.out.print("PULPERIA GLORIA");
        System.out.println("                                                  Fecha: " + hoy.format(formatofecha));
        MenuPrincipal();
    }

    public static void MenuPrincipal(){
        boolean key=true;
        while (key) {
            System.out.println("");
            System.out.println("<--------Menu Principal------->");
            System.out.println("1. Inventario");
            System.out.println("2. Ventas");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opcion: ");
            int opcion=obtenerOpcionValida();
            switch (opcion) {
                case 1:
                    MenuInventario();
                    break;
                case 2:
                    MenuVentas();
                    break;
                case 3:
                    System.out.println("Saliendo.....");
                    key=false;
                    break;
            
                default:
                    System.out.println("Opcion invalida....");
                    break;
            }
        }
    }

    public static void MenuInventario() {
        boolean key = true;
        while (key) {
            System.out.println("");
            System.out.println("<-----------SISTEMA DE INVENTARIO------------>");
            System.out.println("1. Mostrar Inventario");
            System.out.println("2. Agregar Productos");
            System.out.println("3. Fechas de vencimiento");
            System.out.println("4. Actualizar Productos");
            System.out.println("5. Eliminar Productos");
            System.out.println("6. Buscar producto");
            System.out.println("7. Regresar");
            System.out.print("Ingrese una opcion: ");
            int opt=obtenerOpcionValida();
           
                switch (opt) {
                    case 1:
                        MostrarProductos();
                        break;
                    case 2:
                        AgregarProducto();
                        break;
                    case 3:
                        FechaVencimiento();
                        break;
                    case 4:
                        ActualizarProducto();
                        break;
                    case 5:
                        EliminarProducto();
                        break;
                    case 6:
                        BuscarProducto();
                        break;
                    case 7:
                        key=false;
                        break;
                    default:
                        System.out.println("Ingrese una opción válida...");
                        break;
            }
        }
    }

    public static void MenuVentas(){
        boolean key=true;
        while (key) {
            System.out.println("");
            System.out.println("<---------SISTEMA DE VENTAS---------->");
            System.out.println("1. Ver registro de ventas");
            System.out.println("2. Registrar nueva venta");
            System.out.println("3. Regresar");
            System.out.print("Ingrese una opcion: ");
            int opt=obtenerOpcionValida();
            switch (opt) {
                case 1:
                    
                    break;
                case 2:

                    break;
                case 3:
                    key=false;
                    break;
            
                default:
                System.out.println("Opcion invalida.....");
                    break;
            }
        }
    }
    
    public static int obtenerOpcionValida() {
        while (true) {
            try {
                int opcion = sc.nextInt();
                sc.nextLine(); 
                return opcion;
            } catch (Exception e) {
                System.out.print("Opción inválida. Por favor ingrese un número: ");
                sc.nextLine(); 
            }
        }
    }

    public static void MostrarProductos(){
        System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "Codigo", "Nombre", "Cantidad", "Precio", "Fecha de Vencimiento");
        System.out.println("---------------------------------------------------------------");
        for (Producto p : listaProductos) {
            System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.codigo, p.nombre, p.cantidadStock, p.precio, p.fechaVencimiento);
        }
    }

    public static void AgregarProducto(){
        System.out.print("Ingrese el nombre del producto: ");
        nombreProducto=sc.nextLine();
        System.out.print("Cantidad de productos: ");
        stockProducto=sc.nextInt();
        System.out.print("Costo del producto: ");
        costoProducto=sc.nextDouble();
        sc.nextLine();
        LocalDate fechaVencimiento = null;
        while (fechaVencimiento == null) {
            System.out.print("Ingrese la fecha de vencimiento (dd/MM/yyyy): ");
            fechaVenciProducto = sc.nextLine();
            try {
                fechaVencimiento = LocalDate.parse(fechaVenciProducto, inputFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, intente de nuevo.");
            }
        }
        codigoProducto=Producto.generadorCodigo();
        precioProducto=Producto.calcularPrecio(costoProducto);
        Producto producto=new Producto(codigoProducto, nombreProducto, costoProducto, precioProducto, fechaVencimiento, stockProducto);
        listaProductos.add(producto);
        System.out.println("Producto agregado correctamente....");
    }

    public static void FechaVencimiento() {
    // Crear un comparador para ordenar los productos por fecha de vencimiento más cercana a hoy
    Comparator<Producto> comparadorFechaVencimiento = new Comparator<Producto>() {
        @Override
        public int compare(Producto p1, Producto p2) {
            LocalDate fechaVencimiento1 = p1.fechaVencimiento;
            LocalDate fechaVencimiento2 = p2.fechaVencimiento;
            // Comparar las fechas de vencimiento con respecto a la fecha actual
            return fechaVencimiento1.compareTo(fechaVencimiento2);
        }
    };

    // Ordenar la lista de productos utilizando el comparador
    Collections.sort(listaProductos, comparadorFechaVencimiento);

    // Mostrar los productos ordenados por fecha de vencimiento
    System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "Codigo", "Nombre", "Cantidad", "Precio", "Fecha de Vencimiento");
    System.out.println("---------------------------------------------------------------");
    for (Producto p : listaProductos) {
        System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.codigo, p.nombre, p.cantidadStock, p.precio, p.fechaVencimiento.format(inputFormatter));
    }
}


    public static void ActualizarProducto(){
        System.out.print("Ingrese el codigo del producto: ");
        codigoProducto=sc.nextInt();
        for (Producto produc : listaProductos) {
             if (codigoProducto==produc.codigo) {
                 boolean key=true;
                 while (key) {
                     System.out.println("¿Que va a actualizar del Producto: "+produc.nombre+"?");
                     System.out.println("1. Nombre de Producto");
                     System.out.println("2. Costo del Producto");
                     System.out.println("3. Fecha de Vencimiento");
                     System.out.println("4. Regresar");
                     System.out.print("Ingrese una opcion: ");
                     int opcion=obtenerOpcionValida();
                     switch (opcion) {
                         case 1:
                             System.out.print("Ingrese el nuevo nombre del producto: ");
                             nombreProducto=sc.nextLine();
                             produc.nombre=nombreProducto;
                            break;
                        case 2:
                            System.out.println("Ingrese el nuevo costo del producto: ");
                            costoProducto=sc.nextDouble();
                            produc.setCosto(costoProducto);
                            Producto.calcularPrecio(costoProducto);
                            break;
                        case 3:
                            System.out.println("Ingrese la nueva fecha de vencimiento: ");
                            fechaVenciProducto=sc.nextLine();
                            produc.fechaVencimiento=LocalDate.parse(fechaVenciProducto, inputFormatter);
                            break;
                        case 4:
                            key=false;
                            break;
                    
                        default:
                        System.out.println("Opcion invalida....");
                            break;
                    }
                }
            } else {
                System.out.println("No se puedo encontrar el producto....");
            }
        }
    }

    public static void EliminarProducto() {
        System.out.print("Ingrese el codigo del producto: ");
        int codigoProducto = sc.nextInt();
        boolean productoEncontrado = false;
    
        // Recorremos la lista para encontrar el producto
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto produc = listaProductos.get(i);
            if (codigoProducto == produc.codigo) {
                productoEncontrado = true;
                System.out.println("¿Estás seguro que quieres eliminar este producto?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                System.out.print("Ingrese una opción: ");
                int opcion = obtenerOpcionValida();
    
                switch (opcion) {
                    case 1:
                        listaProductos.remove(i); // Eliminamos el producto de la lista
                        System.out.println("Producto eliminado correctamente...");
                        break;
                    case 2:
                        System.out.println("No se eliminó el producto.");
                        break;
                    default:
                        System.out.println("Opción inválida...");
                        break;
                }
                break; // Salimos del bucle una vez que encontramos y procesamos el producto
            }
        }
    
        if (!productoEncontrado) {
            System.out.println("No se encontró el producto...");
        }
    }
    
    
    public static void BuscarProducto() {
        System.out.print("Ingrese el codigo del producto: ");
        int codigoProducto = sc.nextInt();
        boolean productoEncontrado = false;
    
        for (Producto p : listaProductos) {
            if (codigoProducto == p.codigo) {
                System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "Codigo", "Nombre", "Cantidad", "Precio", "Fecha de Vencimiento");
                System.out.println("---------------------------------------------------------------");
                System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.codigo, p.nombre, p.cantidadStock, p.precio, p.fechaVencimiento);
                productoEncontrado = true;
                break;
            }
        }
    
        if (!productoEncontrado) {
            System.out.println("No se encontró el producto...");
        }
    }
    
    
}
