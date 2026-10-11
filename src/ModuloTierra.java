public class ModuloTierra extends Modulo {
    private final String estacion;
    private final int capacidadDescarga;
    private final int energiaPorDescarga;
    private int totalDescargado;

    public ModuloTierra(String id, String nombre, int salud,
                        boolean activo, int costoConstruccion,
                        String estacion, int capacidadDescarga,
                        int energiaPorDescarga) {
        super(id, nombre, salud, activo, costoConstruccion);

        if (estacion == null || estacion.isBlank()) {
            throw new IllegalArgumentException(
                "El nombre de la estación es obligatorio."
            );
        }
        if (capacidadDescarga <= 0 || energiaPorDescarga <= 0) {
            throw new IllegalArgumentException(
                "La capacidad y la energía por descarga deben ser mayores que cero."
            );
        }

        this.estacion = estacion;
        this.capacidadDescarga = capacidadDescarga;
        this.energiaPorDescarga = energiaPorDescarga;
        this.totalDescargado = 0;
    }

    public String getEstacion() {
        return estacion;
    }

    public int getCapacidadDescarga() {
        return capacidadDescarga;
    }

    public int getEnergiaPorDescarga() {
        return energiaPorDescarga;
    }

    public int getTotalDescargado() {
        return totalDescargado;
    }

    @Override
    public String procesarCiclo(RecursosMision recursos) {
        if (!isActivo()) {
            return getNombre() + ": no descargó datos porque está inactivo.";
        }

        if (recursos.getDatosEnOrbita() == 0) {
            return getNombre()
                 + ": no descargó datos porque no hay datos en órbita.";
        }

        if (!recursos.tieneEnergia(energiaPorDescarga)) {
            return getNombre()
                 + ": no descargó datos por energía insuficiente."
                 + " Necesita " + energiaPorDescarga + " unidades.";
        }

        int cantidadPosible = Math.min(
            capacidadDescarga, recursos.getDatosEnOrbita()
        );
        int nuevoTotal = Math.addExact(totalDescargado, cantidadPosible);

        int cantidadDescargada = recursos.descargarDatos(capacidadDescarga);
        recursos.consumirEnergia(energiaPorDescarga);
        totalDescargado = nuevoTotal;

        return getNombre() + ": consumió " + energiaPorDescarga
             + " unidades de energía y descargó "
             + cantidadDescargada + " MB.";
    }

    @Override
    public String toString() {
        return "MÓDULO DE TIERRA | " + super.toString()
             + " | Estación: " + estacion
             + " | Capacidad por descarga: " + capacidadDescarga + " MB"
             + " | Energía por descarga: " + energiaPorDescarga + " unidades"
             + " | Total descargado: " + totalDescargado + " MB";
    }
}