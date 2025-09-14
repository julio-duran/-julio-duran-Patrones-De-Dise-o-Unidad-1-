/**
 * Clase principal del sistema de gestión de inventarios.
 * Proporciona un menú interactivo en consola para las operaciones principales.
 * Integra patrones: Singleton (Inventario), Factory Method (ProductoFactory), Prototype (clonación).
 * 
 * @author Geronimo Lugo Oviedo, Néstor González Flórez, Julio Eduardo Durán
 * @version 2.0 - Nueva versión alineada a requerimientos (13/09/2025)
 */
import utils.EntradaUsuario;

public class Main {
    /**
     * Método principal que inicia el bucle del menú.
     */
    public static void main(String[] args) {
        Inventario inventario = Inventario.getInstancia();

        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear categoría");
            System.out.println("2. Crear producto");
            System.out.println("3. Clonar producto");
            System.out.println("4. Consultar inventario");
            System.out.println("5. Actualizar inventario");
            System.out.println("6. Salir");

            int opcion = EntradaUsuario.leerNumero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearCategoria(inventario);
                    break;
                case 2:
                    crearProducto(inventario);
                    break;
                case 3:
                    clonarProducto(inventario);
                    break;
                case 4:
                    inventario.mostrarInventario();
                    break;
                case 5:
                    actualizarInventario(inventario);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    /**
     * Crea una nueva categoría con atributos personalizados y la persiste en el inventario.
     * Cumple con Caso de Uso 1: Crear una categoría de productos.
     * 
     * @param inventario La instancia Singleton del inventario.
     */
    private static void crearCategoria(Inventario inventario) {
        String nombre = EntradaUsuario.leerTexto("Nombre de la categoría: ");
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        String descripcion = EntradaUsuario.leerTexto("Descripción: ");
        Categoria categoria = new Categoria(nombre, descripcion);

        int cantidad = EntradaUsuario.leerNumero("¿Cuántos atributos desea agregar?: ");
        if (cantidad < 0) {
            System.out.println("La cantidad de atributos no puede ser negativa.");
            return;
        }
        for (int i = 0; i < cantidad; i++) {
            String clave = EntradaUsuario.leerTexto("Nombre del atributo: ");
            if (clave.trim().isEmpty()) {
                System.out.println("El nombre del atributo no puede estar vacío.");
                continue;
            }
            String valor = EntradaUsuario.leerTexto("Descripción del atributo: ");
            categoria.agregarAtributo(clave, valor);
        }

        inventario.agregarCategoria(categoria);
        System.out.println("Categoría creada y persistida:\n" + categoria);
    }

    /**
     * Crea un nuevo producto usando Factory Method, asignado a una categoría existente.
     * Si la categoría no existe, crea una temporal (recomendación: use opción 1 primero).
     * Cumple con Caso de Uso: Gestión de Productos.
     * 
     * @param inventario La instancia Singleton del inventario.
     */
    private static void crearProducto(Inventario inventario) {
        String nombreCategoria = EntradaUsuario.leerTexto("Ingrese el nombre de la categoría: ");
        if (nombreCategoria.trim().isEmpty()) {
            System.out.println("El nombre de la categoría no puede estar vacío.");
            return;
        }
        Categoria categoria = inventario.buscarCategoria(nombreCategoria);

        if (categoria == null) {
            System.out.println("Categoría no encontrada. Creando temporal...");
            categoria = new Categoria(nombreCategoria, "Categoría temporal creada automáticamente.");
        }

        String nombre = EntradaUsuario.leerTexto("Nombre del producto: ");
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre del producto no puede estar vacío.");
            return;
        }
        double precio = EntradaUsuario.leerDecimal("Precio: ");
        if (precio < 0) {
            System.out.println("El precio no puede ser negativo.");
            return;
        }
        int cantidad = EntradaUsuario.leerNumero("Cantidad en inventario: ");
        if (cantidad < 0) {
            System.out.println("La cantidad no puede ser negativa.");
            return;
        }

        // Uso de Factory Method para creación encapsulada
        Producto producto = ProductoFactory.crearProducto(nombre, precio, cantidad, categoria);

        inventario.agregarProducto(producto);
        System.out.println("Producto creado:\n" + producto);
    }

    /**
     * Clona un producto existente usando Prototype y Clonador.
     * Cumple con Caso de Uso 2: Clonar un producto existente.
     * 
     * @param inventario La instancia Singleton del inventario.
     */
    private static void clonarProducto(Inventario inventario) {
        String nombreOriginal = EntradaUsuario.leerTexto("Nombre del producto a clonar: ");
        if (nombreOriginal.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Producto original = inventario.buscarProducto(nombreOriginal);

        if (original != null) {
            // Uso de Prototype vía Clonador
            Producto copia = ClonadorProducto.clonarProducto(original);
            inventario.agregarProducto(copia);
            System.out.println("Producto clonado:\n" + copia);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /**
     * Actualiza la cantidad de un producto (añadir o retirar).
     * Previene stock negativo. Cumple con Caso de Uso 4: Registrar salida de productos.
     * 
     * @param inventario La instancia Singleton del inventario.
     */
    private static void actualizarInventario(Inventario inventario) {
        String nombre = EntradaUsuario.leerTexto("Nombre del producto: ");
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        Producto producto = inventario.buscarProducto(nombre);

        if (producto != null) {
            int cantidad = EntradaUsuario.leerNumero("Cantidad a añadir (+) o retirar (-): ");
            if (cantidad >= 0) {
                producto.setCantidad(producto.getCantidad() + cantidad);
            } else {
                inventario.retirarProducto(nombre, -cantidad);
            }
            System.out.println("Inventario actualizado:\n" + producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}