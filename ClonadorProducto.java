import utils.EntradaUsuario;

/**
 * Clase para encapsular la clonación de productos usando Prototype.
 * Aplica patrón Prototype vía método clonar() de Producto.
 * 
 * @author Geronimo Lugo Oviedo, Néstor González Flórez, Julio Eduardo Durán
 * @version 2.0
 */
public class ClonadorProducto {

    /**
     * Clona un producto y solicita nuevo nombre.
     * 
     * @param original Producto a clonar.
     * @return Copia clonada.
     */
    public static Producto clonarProducto(Producto original) {
        Producto copia = original.clonar();
        String nuevoNombre;
        do {
            nuevoNombre = EntradaUsuario.leerTexto("Nuevo nombre para el producto clonado: ");
            if (nuevoNombre.trim().isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
            }
        } while (nuevoNombre.trim().isEmpty());
        copia.setNombre(nuevoNombre);
        return copia;
    }
}