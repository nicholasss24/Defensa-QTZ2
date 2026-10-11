import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Mision {
    private final List<Modulo> modulos;
    private final RecursosMision recursos;
    private int numeroCiclo;

    public Mision(RecursosMision recursos) {
        if (recursos == null) {
            throw new IllegalArgumentException(
                "La misión necesita recursos iniciales."
            );
        }

        this.modulos = new ArrayList<>();
        this.recursos = recursos;
        this.numeroCiclo = 0;
    }

    public RecursosMision getRecursos() {
        return recursos;
    }

    public int getNumeroCiclo() {
        return numeroCiclo;
    }

    public List<Modulo> getModulos() {
        return Collections.unmodifiableList(modulos);
    }

    public void agregarModulo(Modulo modulo) {
        if (modulo == null) {
            throw new IllegalArgumentException(
                "No se puede agregar un módulo nulo."
            );
        }
        if (buscarPorId(modulo.getId()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un módulo con el ID " + modulo.getId()
            );
        }

        modulos.add(modulo);
    }

    public Modulo buscarPorId(String id) {
        if (id == null) {
            return null;
        }

        for (Modulo modulo : modulos) {
            if (modulo.getId().equalsIgnoreCase(id.trim())) {
                return modulo;
            }
        }
        return null;
    }

    public List<Modulo> buscarPorNombre(String nombre) {
        List<Modulo> encontrados = new ArrayList<>();

        if (nombre == null) {
            return encontrados;
        }

        for (Modulo modulo : modulos) {
            if (modulo.getNombre().equalsIgnoreCase(nombre.trim())) {
                encontrados.add(modulo);
            }
        }
        return encontrados;
    }

    public List<Modulo> obtenerCatalogoPorCosto() {
        List<Modulo> catalogo = new ArrayList<>(modulos);
        catalogo.sort(
            Comparator.comparingInt(Modulo::getCostoConstruccion)
                      .thenComparing(Modulo::getId)
        );
        return catalogo;
    }

    public String avanzarCiclo() {
        numeroCiclo++;
        StringBuilder resultado = new StringBuilder();

        resultado.append("\n=== CICLO ")
                 .append(numeroCiclo)
                 .append(" ===\n");

        for (Modulo modulo : modulos) {
            // El tipo real del módulo decide qué versión del método se ejecuta.
            resultado.append(modulo.procesarCiclo(recursos))
                     .append("\n");

            // Los recursos ya se actualizaron antes de pasar al siguiente.
            resultado.append("   ")
                     .append(recursos)
                     .append("\n");
        }

        resultado.append("Estado final: ")
                 .append(recursos)
                 .append("\n");

        return resultado.toString();
    }

    public String obtenerResumenComunicaciones() {
        int cantidadTierra = 0;
        int cantidadActivos = 0;
        int capacidadActiva = 0;
        int totalHistorico = 0;
        int mayorDescarga = -1;
        List<ModuloTierra> lideres = new ArrayList<>();

        for (Modulo modulo : modulos) {
            if (modulo instanceof ModuloTierra) {
                ModuloTierra tierra = (ModuloTierra) modulo;
                cantidadTierra++;

                if (tierra.isActivo()) {
                    cantidadActivos++;
                    capacidadActiva = Math.addExact(
                        capacidadActiva, tierra.getCapacidadDescarga()
                    );
                }

                totalHistorico = Math.addExact(
                    totalHistorico, tierra.getTotalDescargado()
                );

                if (tierra.getTotalDescargado() > mayorDescarga) {
                    mayorDescarga = tierra.getTotalDescargado();
                    lideres.clear();
                    lideres.add(tierra);
                } else if (tierra.getTotalDescargado() == mayorDescarga) {
                    lideres.add(tierra);
                }
            }
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("\n=== RESUMEN DE COMUNICACIONES ===\n")
               .append("Módulos de tierra: ").append(cantidadTierra).append("\n")
               .append("Módulos de tierra activos: ").append(cantidadActivos).append("\n")
               .append("Capacidad de descarga activa por ciclo: ")
               .append(capacidadActiva).append(" MB\n")
               .append("Total histórico descargado: ")
               .append(totalHistorico).append(" MB\n");

        if (lideres.isEmpty()) {
            resumen.append("No hay módulos de tierra.\n");
        } else {
            resumen.append("Módulo(s) con mayor descarga histórica (")
                   .append(mayorDescarga)
                   .append(" MB):\n");

            for (ModuloTierra tierra : lideres) {
                resumen.append("- Nombre: ").append(tierra.getNombre())
                       .append(" | ID: ").append(tierra.getId())
                       .append(" | Estación: ").append(tierra.getEstacion())
                       .append("\n");
            }
        }

        return resumen.toString();
    }
}