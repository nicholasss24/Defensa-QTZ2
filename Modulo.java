public abstract class Modulo {
    private final String id;
    private final String nombre;
    private int salud;
    private boolean activo;
    private final int costoConstruccion;

    public Modulo(String id, String nombre, int salud,
                  boolean activo, int costoConstruccion) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID es obligatorio.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (salud < 0 || salud > 100) {
            throw new IllegalArgumentException(
                "La salud debe estar entre 0 y 100."
            );
        }
        if (costoConstruccion < 0) {
            throw new IllegalArgumentException(
                "El costo de construcción no puede ser negativo."
            );
        }

        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.activo = activo && salud > 0;
        this.costoConstruccion = costoConstruccion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public boolean isActivo() {
        return activo;
    }

    public int getCostoConstruccion() {
        return costoConstruccion;
    }

    public void setSalud(int salud) {
        if (salud < 0 || salud > 100) {
            throw new IllegalArgumentException(
                "La salud debe estar entre 0 y 100."
            );
        }

        this.salud = salud;

        if (salud == 0) {
            this.activo = false;
        }
    }

    public void setActivo(boolean activo) {
        if (activo && salud == 0) {
            throw new IllegalStateException(
                "No se puede activar un módulo sin salud."
            );
        }
        this.activo = activo;
    }

    // Cada subclase define cómo utiliza o produce recursos.
    public abstract String procesarCiclo(RecursosMision recursos);

    @Override
    public String toString() {
        return "ID: " + id
             + " | Nombre: " + nombre
             + " | Salud: " + salud + "%"
             + " | Activo: " + (activo ? "Sí" : "No")
             + " | Costo: Q" + costoConstruccion;
    }
}