import java.time.LocalDate;

public class Producto {
    public static int codigoBase=1000;
    public int codigo;
    public String nombre;
    private double costo; 
    public double precio;
    public LocalDate fechaVencimiento;
    public int cantidadStock;

    public Producto(int codigo, String nombre, double costo, double precio, LocalDate fechaVencimiento, int cantidadStock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.costo = costo;
        this.precio = precio;
        this.fechaVencimiento = fechaVencimiento;
        this.cantidadStock = cantidadStock;
    }

    public double getCosto (){
       return costo;
    }
    public void setCosto (double costo){
        this.costo = costo;
    }

    
    public static double calcularPrecio(double costo) {
        double porcentaje = 0.35;
        double porcentajeCosto = costo * porcentaje;
        double precio = costo + porcentajeCosto;
        return precio;
    }

    public static int generadorCodigo() {
        codigoBase++;
        return codigoBase;
    }




   
}
