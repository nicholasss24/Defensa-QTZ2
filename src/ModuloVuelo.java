public class ModuloVuelo extends Modulo {
    private final String tipoInstrumento;
    private final int datosPorCiclo;
    private final int consumoEnergia;
    private int ciclosAcumulados;
    private int totalDatosRecolectados;

    public ModuloVuelo(String id, String nombre, int salud,
                       boolean activo, int costoConstruccion,
                       String tipoInstrumento, int datosPorCiclo,
                       int consumoEnergia) {
        super(id, nombre, salud, activo, costoConstruccion);

        if (tipoInstrumento == null
                || (!tipoInstrumento.equalsIgnoreCase("Cámara")
                    && !tipoInstrumento.equalsIgnoreCase("Sensor"))) {
            throw new IllegalArgumentException(
                "El instrumento debe ser Cámara o Sensor."
            );
        }
        if (datosPorCiclo <= 0 || consumoEnergia <= 0) {
            throw new IllegalArgumentException(
                "Los datos y el consumo por ciclo deben ser mayores que cero."
            );
        }

        this.tipoInstrumento = tipoInstrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.consumoEnergia = consumoEnergia;
        this.ciclosAcumulados = 0;
        this.totalDatosRecolectados = 0;
    }

    public String getTipoInstrumento() {
        return tipoInstrumento;
    }

    public int getDatosPorCiclo() {
        return datosPorCiclo;
    }

    public int getConsumoEnergia() {
        return consumoEnergia;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public int getTotalDatosRecolectados() {
        return totalDatosRecolectados;
    }

    @Override
    public String procesarCiclo(RecursosMision recursos) {
        if (!isActivo()) {
            return getNombre() + ": no recolectó datos porque está inactivo.";
        }

        if (!recursos.tieneEnergia(consumoEnergia)) {
            return getNombre()
                 + ": no recolectó datos por energía insuficiente."
                 + " Necesita " + consumoEnergia + " unidades.";
        }

        int nuevosCiclos = Math.addExact(ciclosAcumulados, 1);
        int nuevoTotal = Math.addExact(
            totalDatosRecolectados, datosPorCiclo
        );

        recursos.recolectarDatos(datosPorCiclo);
        recursos.consumirEnergia(consumoEnergia);

        ciclosAcumulados = nuevosCiclos;
        totalDatosRecolectados = nuevoTotal;

        return getNombre() + ": consumió " + consumoEnergia
             + " unidades de energía y recolectó "
             + datosPorCiclo + " MB.";
    }

    @Override
    public String toString() {
        return "MÓDULO DE VUELO | " + super.toString()
             + " | Instrumento: " + tipoInstrumento
             + " | Datos por ciclo: " + datosPorCiclo + " MB"
             + " | Consumo por ciclo: " + consumoEnergia + " unidades"
             + " | Ciclos operados: " + ciclosAcumulados
             + " | Total recolectado: " + totalDatosRecolectados + " MB";
    }
}