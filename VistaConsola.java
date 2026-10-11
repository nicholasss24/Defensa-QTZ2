import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private final Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n========== DEFENSA DE QTZ2 ==========");
        System.out.println("1. Listar todos los módulos");
        System.out.println("2. Buscar un módulo por ID");
        System.out.println("3. Buscar módulos por nombre");
        System.out.println("4. Ver catálogo ordenado por costo");
        System.out.println("5. Avanzar un ciclo");
        System.out.println("6. Ver resumen de comunicaciones");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    public String leerLinea() {
        return scanner.nextLine().trim();
    }

    public String pedirTexto(String indicacion) {
        System.out.print(indicacion);
        return leerLinea();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarModulo(Modulo modulo) {
        if (modulo == null) {
            System.out.println("No se encontró ningún módulo.");
        } else {
            System.out.println(modulo);
        }
    }

    public void mostrarModulos(List<Modulo> modulos) {
        if (modulos.isEmpty()) {
            System.out.println("No hay módulos para mostrar.");
            return;
        }

        for (Modulo modulo : modulos) {
            System.out.println(modulo);
        }
    }
}