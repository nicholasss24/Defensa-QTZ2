public class Main {
    public static void main(String[] args) {
        // Energía inicial: 10 unidades. Todavía no hay datos en órbita
        // ni descargas históricas.
        RecursosMision recursos = new RecursosMision(10, 0, 0);
        Mision mision = new Mision(recursos);

        // El orden de esta lista determina el orden de participación.
        mision.agregarModulo(new ModuloEnergia(
            "E01", "Panel Aurora", 100, true, 1200, 20
        ));

        mision.agregarModulo(new ModuloVuelo(
            "V01", "Cámara Quetzal", 90, true, 3000,
            "Cámara", 15, 8
        ));

        mision.agregarModulo(new ModuloTierra(
            "T01", "Antena Central", 100, true, 2500,
            "Campus Central", 10, 5
        ));

        mision.agregarModulo(new ModuloVuelo(
            "V02", "Sensor Solar", 85, true, 2200,
            "Sensor", 20, 12
        ));

        mision.agregarModulo(new ModuloEnergia(
            "E02", "Panel Horizonte", 95, true, 1500, 10
        ));

        mision.agregarModulo(new ModuloTierra(
            "T02", "Antena Norte", 80, true, 2100,
            "Estación Norte", 15, 7
        ));

        mision.agregarModulo(new ModuloEnergia(
            "E03", "Panel Reserva", 70, false, 900, 8
        ));

        mision.agregarModulo(new ModuloVuelo(
            "V03", "Cámara Auxiliar", 75, false, 1800,
            "Cámara", 12, 6
        ));

        mision.agregarModulo(new ModuloVuelo(
            "V04", "Sensor Magnético", 100, true, 2400,
            "Sensor", 9, 4
        ));

        mision.agregarModulo(new ModuloTierra(
            "T03", "Antena Sur", 60, false, 1700,
            "Estación Sur", 8, 3
        ));

        // Simula actividad previa para iniciar con históricos coherentes.
        mision.avanzarCiclo();

        VistaConsola vista = new VistaConsola();
        Controlador controlador = new Controlador(mision, vista);
        controlador.iniciar();
    }
}