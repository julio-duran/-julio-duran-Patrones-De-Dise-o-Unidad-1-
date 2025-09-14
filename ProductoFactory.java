import utils.EntradaUsuario;

/**
 * Factory Method para la creación de productos.
 * Encapsula la lógica de creación, incluyendo asignación de atributos de la categoría.
 * Aplica patrón Factory Method.
 * 
 * @author Geronimo Lugo Oviedo, Néstor González Flórez, Julio Eduardo Durán
 * @version 2.0
 */
public class ProductoFactory {

    /**
     * Crea un producto y solicita valores para atributos de la categoría.
     * 
     * @param nombre Nombre del producto.
     * @param precio Precio.
     * @param cantidad Cantidad inicial.
     * @param categoria Categoría asociada.
     * @return Producto creado.
     */
    public static Producto crearProducto(String nombre, double precio, int cantidad, Categoria categoria) {
        Producto producto = new Producto(nombre, precio, cantidad, categoria);

        // Asigna atributos específicos vía input
        for (java.util.Map.Entry<String, String> atributo : categoria.getAtributos().entrySet()) {
            String valor = EntradaUsuario.leerTexto("Ingrese valor para '" + atributo.getKey() + "': ");
            producto.agregarAtributo(atributo.getKey(), valor);
        }

        return producto;
    }
}