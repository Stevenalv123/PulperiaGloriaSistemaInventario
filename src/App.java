import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    //#region
    static String nombreProducto, fechaVenciProducto;
    static int stockProducto;
    static double costoProducto, precioProducto;
    static Scanner sc = new Scanner(System.in);
    static LocalDate hoy = LocalDate.now();
    static DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //static ArrayList<Producto> productos=new ArrayList<Producto>();
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
                        // Lógica para fechas de vencimiento
                        System.out.println("Mostrando fechas de vencimiento...");
                        break;
                    case 4:
                        // Lógica para actualizar productos
                        System.out.println("Actualizando productos...");
                        break;
                    case 5:
                        // Lógica para eliminar productos
                        System.out.println("Eliminando productos...");
                        break;
                    case 6:
                        // Lógica para buscar producto
                        System.out.println("Buscando producto...");
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
        // for (Producto p : inventario) {
        //     System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.getCodigo(), p.getNombre(), p.getCantidad(), p.getPrecio(), p.getFechaVencimiento());
        // }
    }

    public static void AgregarProducto(){
        System.out.print("Ingrese el nombre del producto: ");
        nombreProducto=sc.nextLine();
        System.out.print("Cantidad de productos: ");
        stockProducto=sc.nextInt();
        System.out.print("Costo del producto: ");
        costoProducto=sc.nextDouble();
        System.out.print("Precio de venta: ");
        precioProducto=sc.nextDouble();
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
    }

    public static void FechaVencimiento(){
        System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "Codigo", "Nombre", "Cantidad", "Precio", "Fecha de Vencimiento");
        System.out.println("---------------------------------------------------------------");
        // for (Producto p : inventario) {
        //     System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.getCodigo(), p.getNombre(), p.getCantidad(), p.getPrecio(), p.getFechaVencimiento());
        // }
    }

}
