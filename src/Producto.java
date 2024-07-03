import java.util.Date;
import java.util.Random;

public class Producto {
    public int codigo;
    public String nombre;
    private double costo; 
    public double precio;
    public Date fechaVencimiento;
    public int cantidadStock;

    public Producto(int codigo, String nombre, double costo, double precio, Date fechaVencimiento, int cantidadStock) {
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

    
    public double calcularPrecio() {
        double porcentaje = 0.35;
        double porcentajeCosto = costo * porcentaje;
        double precio = costo + porcentajeCosto;
        return precio;
    }

    public int generadorCodigo() {
    
        Random codigorandom = new Random();
        return codigorandom.nextInt();
    }




   
}
