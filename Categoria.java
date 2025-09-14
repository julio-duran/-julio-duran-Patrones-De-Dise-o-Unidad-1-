import java.util.HashMap;
import java.util.Map;

/**
 * Representa una categoría de productos con atributos personalizados.
 * 
 * @author Geronimo Lugo Oviedo, Néstor González Flórez, Julio Eduardo Durán
 * @version 2.0
 */
public class Categoria {
    private String nombre;
    private String descripcion;
    private Map<String, String> atributos;

    /**
     * Constructor de categoría.
     * @param nombre Nombre de la categoría.
     * @param descripcion Descripción de la categoría.
     */
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.atributos = new HashMap<>();
    }

    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Map<String, String> getAtributos() { return atributos; }

    /**
     * Agrega un atributo a la categoría.
     * @param clave Nombre del atributo.
     * @param valor Valor del atributo.
     */
    public void agregarAtributo(String clave, String valor) {
        atributos.put(clave, valor);
    }

    @Override
    public String toString() {
        return "Categoría: " + nombre + 
               "\nDescripción: " + descripcion + 
               "\nAtributos: " + atributos;
    }
}