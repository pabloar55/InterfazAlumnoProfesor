import Persistencia.InicializarDatabase;
import Presentacion.Vistas.VentanaPrin;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
     VentanaPrin v = new VentanaPrin();
        InicializarDatabase d = new InicializarDatabase();
        d.cargarDatos();
    }
}