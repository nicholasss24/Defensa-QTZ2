package src;

public class ModuloEnergia extends Modulo {
    private final int energiaPorCiclo;
    private int ciclosAcumulados;
    private int totalEnergiaGenerada;

    public ModuloEnergia(String id, String nombre, int salud,
                         boolean activo, int costoConstruccion,
                         int energiaPorCiclo) {
        super(id, nombre, salud, activo, costoConstruccion);

        if (energiaPorCiclo <= 0) {
            throw new IllegalArgumentException(
                "La energía por ciclo debe ser mayor que cero."
            );
        }

        this.energiaPorCiclo = energiaPorCiclo;
        this.ciclosAcumulados = 0;
        this.totalEnergiaGenerada = 0;
    }

    public int getEnergiaPorCiclo() {
        return energiaPorCiclo;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public int getTotalEnergiaGenerada() {
        return totalEnergiaGenerada;
    }

    @Override
    public String procesarCiclo(RecursosMision recursos) {
        if (!isActivo()) {
            return getNombre() + ": no generó energía porque está inactivo.";
        }

        int nuevosCiclos = Math.addExact(ciclosAcumulados, 1);
        int nuevoTotal = Math.addExact(
            totalEnergiaGenerada, energiaPorCiclo
        );

        recursos.agregarEnergia(energiaPorCiclo);
        ciclosAcumulados = nuevosCiclos;
        totalEnergiaGenerada = nuevoTotal;

        return getNombre() + ": generó " + energiaPorCiclo
             + " unidades de energía.";
    }

    @Override
    public String toString() {
        return "MÓDULO DE ENERGÍA | " + super.toString()
             + " | Generación por ciclo: " + energiaPorCiclo
             + " unidades | Ciclos operados: " + ciclosAcumulados
             + " | Total generado: " + totalEnergiaGenerada
             + " unidades";
    }
}