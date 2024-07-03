public class VentaDetalle {
    private Producto producto;
    private int cantidad;
    private double total;

    public VentaDetalle(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.total =total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.total = total;
    }

    public double getTotal() { 
        return total;
    }
}

