import java.util.ArrayList;

public class Venta {
    private ArrayList<VentaDetalle> historialVentas;

    public Venta() {
        this.historialVentas = new ArrayList<>();
        
    }

    public void agregarVenta(Producto producto, int cantidad) {
        VentaDetalle venta = new VentaDetalle(producto, cantidad);
        this.historialVentas.add(venta);
    }

    public ArrayList<VentaDetalle> consultarHistorial() {
        return this.historialVentas;
    }

    public double totalVentasDia() {
        double total = 0;
        for (VentaDetalle venta : this.historialVentas) {
            total += venta.getTotal();
        }
        return total;
    }
}

