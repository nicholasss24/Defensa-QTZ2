public class Controlador {
    private final Mision mision;
    private final VistaConsola vista;

    public Controlador(Mision mision, VistaConsola vista) {
        if (mision == null || vista == null) {
            throw new IllegalArgumentException(
                "El controlador necesita una misión y una vista."
            );
        }

        this.mision = mision;
        this.vista = vista;
    }

    public void iniciar() {
        String opcion;

        do {
            vista.mostrarMenu();
            opcion = vista.leerLinea();

            switch (opcion) {
                case "1":
                    vista.mostrarMensaje("\n=== MÓDULOS DE LA MISIÓN ===");
                    vista.mostrarModulos(mision.getModulos());
                    break;

                case "2":
                    String id = vista.pedirTexto("Escribe el ID del módulo: ");
                    vista.mostrarModulo(mision.buscarPorId(id));
                    break;

                case "3":
                    String nombre = vista.pedirTexto(
                        "Escribe el nombre del módulo: "
                    );
                    vista.mostrarModulos(mision.buscarPorNombre(nombre));
                    break;

                case "4":
                    vista.mostrarMensaje(
                        "\n=== CATÁLOGO ORDENADO POR COSTO ==="
                    );
                    vista.mostrarModulos(mision.obtenerCatalogoPorCosto());
                    break;

                case "5":
                    vista.mostrarMensaje(mision.avanzarCiclo());
                    break;

                case "6":
                    vista.mostrarMensaje(
                        mision.obtenerResumenComunicaciones()
                    );
                    break;

                case "0":
                    vista.mostrarMensaje("Simulador finalizado.");
                    break;

                default:
                    vista.mostrarMensaje(
                        "Opción no válida. Intenta de nuevo."
                    );
                    break;
            }
        } while (!opcion.equals("0"));
    }
}