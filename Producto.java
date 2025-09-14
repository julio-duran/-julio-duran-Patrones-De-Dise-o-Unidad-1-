import java.util.HashMap;
import java.util.Map;

/**
 * Representa un producto con atributos, precio y cantidad.
 * Incluye método Prototype para clonación.
 * 
 * @author Geronimo Lugo Oviedo, Néstor González Flórez, Julio Eduardo Durán
 * @version 2.0
 */
public class Producto {
    private String nombre;
    private double precio;
    private int cantidad;
    private Categoria categoria;
    private Map<String, String> atributosEspecificos;

    /**
     * Constructor de producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param cantidad Cantidad en inventario.
     * @param categoria Categoría asociada.
     */
    public Producto(String nombre, double precio, int cantidad, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.atributosEspecificos = new HashMap<>();
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public Categoria getCategoria() { return categoria; }
    public Map<String, String> getAtributosEspecificos() { return atributosEspecificos; }

    /**
     * Agrega un atributo específico al producto.
     * @param clave Nombre del atributo.
     * @param valor Valor del atributo.
     */
    public void agregarAtributo(String clave, String valor) {
        atributosEspecificos.put(clave, valor);
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método Prototype: Crea una copia superficial del producto.
     * @return Copia del producto.
     */
    public Producto clonar() {
        Producto copia = new Producto(this.nombre, this.precio, this.cantidad, this.categoria);
        copia.atributosEspecificos.putAll(this.atributosEspecificos);
        return copia;
    }

    @Override
    public String toString() {
        return "Producto: " + nombre +
               "\nPrecio: $" + precio +
               "\nCantidad: " + cantidad +
               "\nCategoría: " + categoria.getNombre() +
               "\nAtributos específicos: " + atributosEspecificos;
    }
}