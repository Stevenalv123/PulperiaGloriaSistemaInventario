import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class App {
    //#region
    static Scanner sc = new Scanner(System.in);
    static LocalDate hoy = LocalDate.now();
    static DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy");
    static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static List<Producto> listaProductos = new ArrayList<>();
    static List<Venta> listaVentas = new ArrayList<>();
    static int codigoVentaBase = 10000;
    static double totalVenta;
    //#endregion

    public static void main(String[] args) {
        cargarProductosDesdeArchivo();
        cargarVentasDesdeArchivo();
        System.out.println("");
        System.out.print("PULPERIA GLORIA");
        System.out.println("                                                  Fecha: " + hoy.format(formatofecha));
        MenuPrincipal();
    }

    public static void MenuPrincipal() {
        boolean key = true;
        while (key) {
            System.out.println("");
            System.out.println("<--------Menu Principal------->");
            System.out.println("1. Inventario");
            System.out.println("2. Ventas");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opcion: ");
            int opcion = obtenerOpcionValida();
            switch (opcion) {
                case 1:
                    MenuInventario();
                    break;
                case 2:
                    MenuVentas();
                    break;
                case 3:
                    System.out.println("Saliendo.....");
                    key = false;
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
            int opt = obtenerOpcionValida();

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
                    key = false;
                    break;
                default:
                    System.out.println("Ingrese una opción válida...");
                    break;
            }
        }
    }

    public static void MenuVentas() {
        boolean key = true;
        while (key) {
            System.out.println("");
            System.out.println("<---------SISTEMA DE VENTAS---------->");
            System.out.println("1. Ver registro de ventas");
            System.out.println("2. Registrar nueva venta");
            System.out.println("3. Regresar");
            System.out.print("Ingrese una opcion: ");
            int opt = obtenerOpcionValida();
            switch (opt) {
                case 1:
                    // Mostrar registro de ventas
                    mostrarRegistroVentas();
                    break;
                case 2:
                    RegistrarVenta();
                    break;
                case 3:
                    key = false;
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
                sc.nextLine(); // Consume el salto de línea
                return opcion;
            } catch (Exception e) {
                System.out.print("Opción inválida. Por favor ingrese un número: ");
                sc.nextLine(); // Limpia el buffer de entrada
            }
        }
    }

    public static void MostrarProductos() {
        System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "Codigo", "Nombre", "Cantidad", "Precio",
                "Fecha de Vencimiento");
        System.out.println("---------------------------------------------------------------");
        for (Producto p : listaProductos) {
            System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.codigo, p.nombre, p.cantidadStock, p.precio,
                    p.fechaVencimiento.format(inputFormatter));
        }
    }

    public static void AgregarProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombreProducto = sc.nextLine();
        System.out.print("Cantidad de productos: ");
        int stockProducto = sc.nextInt();
        System.out.print("Costo del producto: ");
        double costoProducto = sc.nextDouble();
        sc.nextLine(); // Consume el salto de línea

        LocalDate fechaVencimiento = null;
        while (fechaVencimiento == null) {
            System.out.print("Ingrese la fecha de vencimiento (dd/MM/yyyy): ");
            String fechaVenciProducto = sc.nextLine();
            try {
                fechaVencimiento = LocalDate.parse(fechaVenciProducto, inputFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, intente de nuevo.");
            }
        }

        int codigoProducto = Producto.generadorCodigo();
        double precioProducto = Producto.calcularPrecio(costoProducto);
        Producto producto = new Producto(codigoProducto, nombreProducto, costoProducto, precioProducto,
                fechaVencimiento, stockProducto);
        listaProductos.add(producto);
        guardarProductosEnArchivo();
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
    
    public static void ActualizarProducto() {
        System.out.print("Ingrese el codigo del producto: ");
        int codigoProducto = sc.nextInt();
        sc.nextLine(); // Consume el salto de línea

        boolean productoEncontrado = false;
        for (Producto produc : listaProductos) {
            if (codigoProducto == produc.codigo) {
                boolean key = true;
                while (key) {
                    System.out.println("¿Qué desea actualizar del Producto: " + produc.nombre + "?");
                    System.out.println("1. Nombre de Producto");
                    System.out.println("2. Costo del Producto");
                    System.out.println("3. Fecha de Vencimiento");
                    System.out.println("4. Regresar");
                    System.out.print("Ingrese una opcion: ");
                    int opcion = obtenerOpcionValida();
                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el nuevo nombre del producto: ");
                            String nombreNuevo = sc.nextLine();
                            produc.nombre = nombreNuevo;
                            System.out.println("Nombre actualizado correctamente.");
                            break;
                        case 2:
                            System.out.print("Ingrese el nuevo costo del producto: ");
                            double costoNuevo = sc.nextDouble();
                            produc.setCosto(costoNuevo);
                            produc.precio = Producto.calcularPrecio(costoNuevo);
                            System.out.println("Costo y precio actualizados correctamente.");
                            break;
                        case 3:
                            System.out.print("Ingrese la nueva fecha de vencimiento (dd/MM/yyyy): ");
                            String fechaVenciProducto = sc.nextLine();
                            LocalDate nuevaFechaVencimiento = null;
                            while (nuevaFechaVencimiento == null) {
                                try {
                                    nuevaFechaVencimiento = LocalDate.parse(fechaVenciProducto, inputFormatter);
                                    produc.fechaVencimiento = nuevaFechaVencimiento;
                                    System.out.println("Fecha de vencimiento actualizada correctamente.");
                                } catch (DateTimeParseException e) {
                                    System.out.println("Formato de fecha inválido. Por favor, intente de nuevo.");
                                    fechaVenciProducto = sc.nextLine();
                                }
                            }
                            break;
                        case 4:
                            key = false;
                            break;
                        default:
                            System.out.println("Opcion invalida....");
                            break;
                    }
                }
                productoEncontrado = true;
                break;
            }
        }
        if (!productoEncontrado) {
            System.out.println("No se encontró el producto con código " + codigoProducto);
        }
        guardarProductosEnArchivo();
    }

    public static void EliminarProducto() {
        System.out.print("Ingrese el codigo del producto: ");
        int codigoProducto = sc.nextInt();
        sc.nextLine(); // Consume el salto de línea

        boolean productoEncontrado = false;
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto produc = listaProductos.get(i);
            if (codigoProducto == produc.codigo) {
                productoEncontrado = true;
                System.out.println("¿Está seguro que desea eliminar este producto?");
                System.out.println("1. Sí");
                System.out.println("2. No");
                System.out.print("Ingrese una opción: ");
                int opcion = obtenerOpcionValida();

                switch (opcion) {
                    case 1:
                        listaProductos.remove(i);
                        System.out.println("Producto eliminado correctamente...");
                        break;
                    case 2:
                        System.out.println("No se eliminó el producto.");
                        break;
                    default:
                        System.out.println("Opción inválida...");
                        break;
                }
                break; 
            }
        }

        if (!productoEncontrado) {
            System.out.println("No se encontró el producto con código " + codigoProducto);
        }
        guardarProductosEnArchivo();
    }

    public static void BuscarProducto() {
        System.out.print("Ingrese el codigo del producto: ");
        int codigoProducto = sc.nextInt();
        sc.nextLine(); 

        boolean productoEncontrado = false;
        for (Producto p : listaProductos) {
            if (codigoProducto == p.codigo) {
                System.out.printf("%-10s %-20s %-10s %-10s %-20s%n", "Codigo", "Nombre", "Cantidad", "Precio",
                        "Fecha de Vencimiento");
                System.out.println("---------------------------------------------------------------");
                System.out.printf("%-10s %-20s %-10d %-10.2f %-20s%n", p.codigo, p.nombre, p.cantidadStock, p.precio,
                        p.fechaVencimiento.format(inputFormatter));
                productoEncontrado = true;
                break; // Termina el ciclo for
            }
        }

        if (!productoEncontrado) {
            System.out.println("No se encontró el producto con código " + codigoProducto);
        }
    }

    public static void RegistrarVenta() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos disponibles para vender.");
            return;
        }
    
        double totalVenta = 0.0; // Inicializar el total de la venta
    
        Venta nuevaVenta = new Venta(codigoVentaBase++, hoy, totalVenta); // Pasar el total inicial como 0.0
        boolean key = true;
        while (key) {
            System.out.print("Ingrese el codigo del producto a vender: ");
            int codigoProducto = sc.nextInt();
            sc.nextLine();
    
            boolean productoEncontrado = false;
            for (Producto producto : listaProductos) {
                if (codigoProducto == producto.codigo) {
                    productoEncontrado = true;
                    System.out.print("Ingrese la cantidad de productos a vender: ");
                    int cantidad = sc.nextInt();
                    sc.nextLine();
    
                    // Verificar si hay suficiente stock para la venta
                    if (cantidad > producto.cantidadStock) {
                        System.out.println("No hay suficiente stock para vender esa cantidad.");
                        break; // Salir del bucle for
                    }
    
                    // Agregar el producto a la venta
                    nuevaVenta.agregarProducto(producto, cantidad);
                    System.out.println("Producto agregado a la venta correctamente.");
    
                    // Actualizar el stock del producto
                    producto.cantidadStock -= cantidad;
                    System.out.println("Stock actualizado correctamente.");
    
                    break; // Salir del bucle for
                }
            }
    
            if (!productoEncontrado) {
                System.out.println("No se encontró el producto con código " + codigoProducto);
            }
    
            System.out.println("¿Desea añadir más productos a la venta?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Ingrese una opción: ");
            int opcion = obtenerOpcionValida();
    
            switch (opcion) {
                case 1:
                    break;
                case 2:
                    key = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    
        totalVenta = nuevaVenta.calcularTotalVenta(); // Calcular el total de la venta
    
        listaVentas.add(nuevaVenta);
        guardarVentasEnArchivo();
        guardarProductosEnArchivo(); // Guardar los productos actualizados en el archivo
        System.out.println("Venta registrada correctamente.");
    }

    public static void mostrarRegistroVentas() {    
        System.out.printf("%-10s %-40s %-10s%n", "Codigo", "Fecha", "Total");
        System.out.println("-----------------------------------------------------------------------");
        for (Venta venta : listaVentas) {
            System.out.printf("%-10s %-40s %-10.2f%n", venta.codigo, venta.obtenerFechaFormateada(), venta.total);
        }
    }
    
    public static void guardarProductosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productos.txt"))) {
            for (Producto p : listaProductos) {
                writer.write(p.codigo + "," + p.nombre + "," + p.getCosto() + "," + p.precio + "," + p.fechaVencimiento + "," + p.cantidadStock);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cargarProductosDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("productos.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int codigo = Integer.parseInt(parts[0]);
                String nombre = parts[1];
                double costo = Double.parseDouble(parts[2]);
                double precio = Double.parseDouble(parts[3]);
                LocalDate fechaVencimiento = LocalDate.parse(parts[4]);
                int cantidadStock = Integer.parseInt(parts[5]);
                Producto producto = new Producto(codigo, nombre, costo, precio, fechaVencimiento, cantidadStock);
                listaProductos.add(producto);
            }
        } catch (Exception e) {
        }
    }

    public static void guardarVentasEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ventas.txt"))) {
            for (Venta v : listaVentas) {
                writer.write(v.codigo+","+v.fecha+","+v.total);
                writer.newLine();
            }
        } catch (IOException e) {
        }
    }

    public static void cargarVentasDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("ventas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) { // Verificar que haya suficientes partes
                    int codigo = Integer.parseInt(parts[0].trim());
                    LocalDate fecha = LocalDate.parse(parts[1].trim()); // Utilizar el formato por defecto ISO_LOCAL_DATE
                    double total = Double.parseDouble(parts[2]);
                    Venta venta = new Venta(codigo, fecha, total);
                    listaVentas.add(venta);
                } else {
                    System.out.println("Error en el formato de la línea: " + line);
                }
            }
        } catch (Exception e) {
            
        }
    }
    
    
}
