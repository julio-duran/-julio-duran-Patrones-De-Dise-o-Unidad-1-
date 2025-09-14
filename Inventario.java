import java.util.ArrayList;
import java.util.List;

/**
 * Clase Singleton para la gestión centralizada del inventario.
 * Mantiene listas de productos y categorías. Aplica patrón Singleton para única instancia.
 * 
 * @author Geronimo Lugo Oviedo, Néstor González Flórez, Julio Eduardo Durán
 * @version 2.0
 */
public class Inventario {
    private static Inventario instancia;
    private List<Producto> productos;
    private List<Categoria> categorias;

    /**
     * Constructor privado para Singleton.
     */
    private Inventario() {
        productos = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    /**
     * Obtiene la única instancia (thread-safe en Java 21+).
     * @return Instancia única del inventario.
     */
    public static Inventario getInstancia() {
        if (instancia == null) {
            synchronized (Inventario.class) {
                if (instancia == null) {
                    instancia = new Inventario();
                }
            }
        }
        return instancia;
    }

    /**
     * Agrega un producto al inventario.
     * @param producto Producto a agregar.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un producto por nombre (case-insensitive).
     * @param nombre Nombre del producto.
     * @return Producto encontrado o null si no existe.
     */
    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Retira cantidad de un producto, previniendo negativo.
     * @param nombre Nombre del producto.
     * @param cantidad Cantidad a retirar.
     */
    public void retirarProducto(String nombre, int cantidad) {
        Producto p = buscarProducto(nombre);
        if (p != null) {
            int nuevaCantidad = p.getCantidad() - cantidad;
            p.setCantidad(Math.max(nuevaCantidad, 0));
        }
    }

    /**
     * Agrega una categoría al registro.
     * @param categoria Categoría a agregar.
     */
    public void agregarCategoria(Categoria categoria) {
        if (buscarCategoria(categoria.getNombre()) == null) {
            categorias.add(categoria);
        } else {
            System.out.println("Categoría ya existe.");
        }
    }

    /**
     * Busca categoría por nombre (case-insensitive).
     * @param nombre Nombre de la categoría.
     * @return Categoría encontrada o null si no existe.
     */
    public Categoria buscarCategoria(String nombre) {
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Muestra el inventario completo.
     * Cumple con Caso de Uso 3: Consultar el estado del inventario.
     */
    public void mostrarInventario() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("------------------------");
            for (Producto p : productos) {
                System.out.println(p);
                System.out.println("------------------------");
            }
        }
    }
}