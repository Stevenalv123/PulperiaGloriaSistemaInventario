import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    static LocalDate hoy = LocalDate.now();
    static DateTimeFormatter formatofecha = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy");

    public static void main(String[] args) throws Exception {
        System.out.print("Pulperia Gloria");
        System.out.println("                                                  Fecha: " + hoy.format(formatofecha));

    }
}
