import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.util.ArrayList;
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
    //static ArrayList<Producto> listaProductos=new ArrayList<Producto>();
    //static ArrayList<Venta> listaVentas=new ArrayList<Venta>();
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
        // for (Producto p : listaProductos) {
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
        // for (Producto p : listaProductos) {
        //     System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.getCodigo(), p.getNombre(), p.getCantidad(), p.getPrecio(), p.getFechaVencimiento());
        // }
    }

    public static void ActualizarProducto(){
        System.out.println("Ingrese el codigo del producto: ");
        codigoProducto=sc.nextInt();
        // for (Producto produc : listaProductos) {
        //     if (codigoProducto==produc.codigoProducto) {
        //         boolean key=false;
        //         while (key) {
        //             System.out.println("¿Que va a actualizar del Producto: "+produc.nombreProducto+"?");
        //             System.out.println("1. Nombre de Producto");
        //             System.out.println("2. Costo del Producto");
        //             System.out.println("3. Precio del Producto");
        //             System.out.println("4. Fecha de Vencimiento");
        //             System.out.println("5. Regresar");
        //             System.out.print("Ingrese una opcion: ");
        //             int opcion=obtenerOpcionValida();
        //             switch (opcion) {
        //                 case 1:
        //                     System.out.print("Ingrese el nuevo nombre del producto: ");
        //                     nombreProducto=sc.nextLine();
        //                     break;
        //                 case 2:
        //                     System.out.println("Ingrese el nuevo costo del producto: ");
        //                     costoProducto=sc.nextDouble();
        //                     break;
        //                 case 3:
        //                     System.out.println("Ingrese el nuevo costo del producto: ");
        //                     precioProducto=sc.nextDouble();
        //                     break;
        //                 case 4:
        //                     System.out.println("Ingrese la nueva fecha de vencimiento: ");
        //                     fechaVenciProducto=sc.nextLine();
        //                     break;
        //                 case 5:
        //                     key=false;
        //                     break;
                    
        //                 default:
        //                 System.out.println("Opcion invalida....");
        //                     break;
        //             }
        //         }
        //     } else {
        //         System.out.println("No se puedo encontrar el producto....");
        //     }
        // }
    }

    public static void EliminarProducto(){
        System.out.println("Ingrese el codigo del producto: ");
        codigoProducto=sc.nextInt();
        // for (Producto produc : listaProductos) {
        //     if (codigoProducto==produc.codigoProducto) {
        //         System.out.println("¿Estas seguro que quieres eliminar este producto?");
        //         System.out.println("1. Si"); 
        //         System.out.println("2. No");
        //         System.out.print("Ingrese una opcion: ");
        //         int opcion=obtenerOpcionValida();
        //         switch (opcion) {
        //             case 1:
        //                 System.out.println("Producto eliminado correctamente...");
        //                 break;
        //             default:
        //             System.out.println("Opcion invalida......");
        //                 break;
        //         }
        //     } else {
        //         System.out.println("No se encontro el producto...");
        //     }
        // }
    }
    
    public static void BuscarProducto(){
        System.out.println("Ingrese el codigo del producto: ");
        codigoProducto=sc.nextInt();
        // for (Producto p : listaProductos) {
        //     if (codigoProducto==p.codigoProducto) {
        //         System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "Codigo", "Nombre", "Cantidad", "Precio", "Fecha de Vencimiento");
        //         System.out.println("---------------------------------------------------------------");
        //         System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.getCodigo(), p.getNombre(), p.getCantidad(), p.getPrecio(), p.getFechaVencimiento());
        //     } else {
        //         System.out.println("No se encontro el producto...");
        //     }
        // }
    }

    public static void RegistrarVenta(){
        System.out.println("Ingrese la cantidad de productos que se va a vender: ");
        int n=sc.nextInt();
        Venta[] producAvender=new Venta[n];
        System.out.println();
    }
}
