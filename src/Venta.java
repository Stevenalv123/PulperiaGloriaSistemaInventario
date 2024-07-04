import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    public static int codigoBase = 10000;
    public int codigo;
    public List<Producto> productos;
    public LocalDate fecha;
    public double total;

    public Venta(int codigo, LocalDate fecha, double total) {
        this.codigo = generadorCodigo();
        this.productos = new ArrayList<>();
        this.fecha = fecha;
        this.total = total; 
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            this.productos.add(producto);
        }
    }

    public double calcularTotalVenta() {
        double totalVenta = 0;
        for (Producto p : productos) {
            totalVenta += p.precio; 
        }
        this.total = totalVenta; 
        return totalVenta;
    }

    public static int generadorCodigo() {
        return codigoBase++;
    }

    public String obtenerFechaFormateada() {
        DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.fecha.format(formatofecha);
    }
}
