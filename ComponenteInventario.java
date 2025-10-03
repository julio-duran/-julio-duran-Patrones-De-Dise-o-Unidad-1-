import java.util.Objects;

public abstract class ComponenteInventario {
    protected final String nombre;

    protected ComponenteInventario(String nombre) {
        this.nombre = Objects.requireNonNull(nombre, "nombre");
    }

    public String getNombre() {
        return nombre;
    }

    /** Representación amigable para inventario */
    public abstract String descripcion();
}
