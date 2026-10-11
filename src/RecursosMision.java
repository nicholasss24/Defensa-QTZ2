public class RecursosMision {
    private int energiaDisponible;
    private int datosEnOrbita;
    private int totalDescargado;

    public RecursosMision(int energiaInicial, int datosInicialesEnOrbita,
                          int totalInicialDescargado) {
        if (energiaInicial < 0 || datosInicialesEnOrbita < 0
                || totalInicialDescargado < 0) {
            throw new IllegalArgumentException(
                "Los recursos iniciales no pueden ser negativos."
            );
        }

        this.energiaDisponible = energiaInicial;
        this.datosEnOrbita = datosInicialesEnOrbita;
        this.totalDescargado = totalInicialDescargado;
    }

    public int getEnergiaDisponible() {
        return energiaDisponible;
    }

    public int getDatosEnOrbita() {
        return datosEnOrbita;
    }

    public int getTotalDescargado() {
        return totalDescargado;
    }

    public boolean tieneEnergia(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException(
                "La cantidad de energía no puede ser negativa."
            );
        }
        return energiaDisponible >= cantidad;
    }

    public void agregarEnergia(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException(
                "La energía generada no puede ser negativa."
            );
        }
        energiaDisponible = Math.addExact(energiaDisponible, cantidad);
    }

    public boolean consumirEnergia(int cantidad) {
        if (!tieneEnergia(cantidad)) {
            return false;
        }

        energiaDisponible -= cantidad;
        return true;
    }

    public void recolectarDatos(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException(
                "Los datos recolectados no pueden ser negativos."
            );
        }
        datosEnOrbita = Math.addExact(datosEnOrbita, cantidad);
    }

    public int descargarDatos(int capacidad) {
        if (capacidad < 0) {
            throw new IllegalArgumentException(
                "La capacidad de descarga no puede ser negativa."
            );
        }

        int cantidadDescargada = Math.min(capacidad, datosEnOrbita);
        int nuevoTotal = Math.addExact(totalDescargado, cantidadDescargada);

        datosEnOrbita -= cantidadDescargada;
        totalDescargado = nuevoTotal;

        return cantidadDescargada;
    }

    @Override
    public String toString() {
        return "Energía disponible: " + energiaDisponible
             + " unidades | Datos en órbita: " + datosEnOrbita
             + " MB | Total descargado: " + totalDescargado + " MB";
    }
}