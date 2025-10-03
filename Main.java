import utils.EntradaUsuario;

public class Main {
    /**
     * Método principal que inicia el bucle del menú.
     */
    public static void main(String[] args) {
        Inventario inventario = Inventario.getInstancia();
        GestorComandos gestorComandos = new GestorComandos(); // Para Command

        // Registrar observador (Observer)
        Observador observadorConsola = new ObservadorConsola();
        inventario.registrarObservador(observadorConsola);

        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear categoría");
            System.out.println("2. Crear producto");
            System.out.println("3. Clonar producto");
            System.out.println("4. Consultar inventario");
            System.out.println("5. Actualizar inventario");
            System.out.println("6. Salir");
            System.out.println("7. Deshacer última operación");

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
                    actualizarInventario(inventario, gestorComandos);
                    break;
                case 6:
                    System.out.println("¡Hasta luego!");
                    return;
                case 7:
                    gestorComandos.deshacerUltimo();
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }

    /**
     * Crea una nueva categoría con atributos personalizados y la persiste en el inventario.
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

        // Opción para agregar a una categoría padre (Composite)
        String nombrePadre = EntradaUsuario.leerTexto("¿Agregar a una categoría padre? (deje vacío si no): ");
        if (!nombrePadre.trim().isEmpty()) {
            ComponenteInventario padre = inventario.buscarComponente(nombrePadre);
            if (padre instanceof Categoria) {
                ((Categoria) padre).agregarHijo(categoria);
                System.out.println("Categoría agregada como subcategoría.");
            } else {
                System.out.println("Categoría padre no encontrada o no es una categoría.");
                return;
            }
        } else {
            inventario.agregarComponente(categoria);
        }
        System.out.println("Categoría creada y persistida:\n" + categoria);
    }

    /**
     * Crea un nuevo producto usando Factory Method, asignado a una categoría existente.
     */
    private static void crearProducto(Inventario inventario) {
        String nombreCategoria = EntradaUsuario.leerTexto("Ingrese el nombre de la categoría: ");
        if (nombreCategoria.trim().isEmpty()) {
            System.out.println("El nombre de la categoría no puede estar vacío.");
            return;
        }
        ComponenteInventario componente = inventario.buscarComponente(nombreCategoria);
        Categoria categoria;
        if (componente instanceof Categoria) {
            categoria = (Categoria) componente;
        } else {
            System.out.println("Categoría no encontrada. Creando temporal...");
            categoria = new Categoria(nombreCategoria, "Categoría temporal creada automáticamente.");
            inventario.agregarComponente(categoria);
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

        // Factory
        Producto producto = ProductoFactory.crearProducto(nombre, precio, cantidad, categoria);

        // Decorator opcional
        String aplicarDescuento = EntradaUsuario.leerTexto("¿Aplicar descuento? (s/n): ");
        if (aplicarDescuento.equalsIgnoreCase("s")) {
            double porcentaje = EntradaUsuario.leerDecimal("Porcentaje de descuento (0-1): ");
            if (porcentaje < 0 || porcentaje > 1) {
                System.out.println("Porcentaje inválido.");
            } else {
                producto = new ProductoConDescuento(producto, porcentaje);
            }
        }

        categoria.agregarHijo(producto); // Composite
        Inventario.getInstancia().notificarObservadores("Producto agregado: " + producto.getNombre());
        System.out.println("Producto creado:\n" + producto);
    }

    /**
     * Clona un producto existente usando Prototype y Clonador.
     */
    private static void clonarProducto(Inventario inventario) {
        String nombreOriginal = EntradaUsuario.leerTexto("Nombre del producto a clonar: ");
        if (nombreOriginal.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        ComponenteInventario componente = inventario.buscarComponente(nombreOriginal);
        if (componente instanceof Producto) {
            Producto original = (Producto) componente;

            // Prototype
            Producto copia = ClonadorProducto.clonarProducto(original);

            // Decorator opcional
            String aplicarDescuento = EntradaUsuario.leerTexto("¿Aplicar descuento al clon? (s/n): ");
            if (aplicarDescuento.equalsIgnoreCase("s")) {
                double porcentaje = EntradaUsuario.leerDecimal("Porcentaje de descuento (0-1): ");
                if (porcentaje < 0 || porcentaje > 1) {
                    System.out.println("Porcentaje inválido.");
                } else {
                    copia = new ProductoConDescuento(copia, porcentaje);
                }
            }

            // Agregar a la misma categoría
            Categoria categoria = original.getCategoria();
            if (categoria != null) {
                categoria.agregarHijo(copia);
                inventario.notificarObservadores("Producto clonado: " + copia.getNombre());
                System.out.println("Producto clonado:\n" + copia);
            } else {
                System.out.println("Categoría no encontrada para el original.");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    /**
     * Actualiza la cantidad de un producto (añadir o retirar) usando Command.
     */
    private static void actualizarInventario(Inventario inventario, GestorComandos gestorComandos) {
        String nombre = EntradaUsuario.leerTexto("Nombre del producto: ");
        if (nombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }
        ComponenteInventario componente = inventario.buscarComponente(nombre);
        if (componente instanceof Producto) {
            Producto producto = (Producto) componente;
            int cantidad = EntradaUsuario.leerNumero("Cantidad a añadir (+) o retirar (-): ");

            Comando comando;
            if (cantidad >= 0) {
                comando = new ComandoAgregarCantidad(producto, cantidad);
            } else {
                comando = new ComandoRetirarCantidad(producto, -cantidad);
            }

            if (gestorComandos.ejecutarComando(comando)) {
                inventario.notificarObservadores("Inventario actualizado para: " + producto.getNombre());
                System.out.println("Inventario actualizado:\n" + producto);
            } else {
                System.out.println("No se pudo ejecutar el comando (verifique existencias).");
            }
        } else {
            System.out.println("Producto no encontrado.");
        }
    }
}
