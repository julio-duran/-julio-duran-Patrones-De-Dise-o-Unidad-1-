import java.util.*;

public class Categoria extends ComponenteInventario {
    private final String descripcion;
    private final Map<String, String> atributos = new LinkedHashMap<>();
    private final List<ComponenteInventario> hijos = new ArrayList<>();

    public Categoria(String nombre, String descripcion) {
        super(nombre);
        this.descripcion = descripcion;
    }

    public void agregarAtributo(String clave, String valor) {
        atributos.put(clave, valor);
    }

    public void agregarHijo(ComponenteInventario hijo) {
        hijos.add(hijo);
        if (hijo instanceof Producto) {
            ((Producto) hijo).setCategoria(this);
        } else if (hijo instanceof ProductoConDescuento) {
            ((ProductoConDescuento) hijo).setCategoria(this);
        }
    }

    public List<ComponenteInventario> getHijos() {
        return Collections.unmodifiableList(hijos);
    }

    @Override
    public String descripcion() {
        return "Categoría: " + nombre + " (" + descripcion + "), atributos=" + atributos;
    }

    @Override
    public String toString() {
        return descripcion();
    }
}
