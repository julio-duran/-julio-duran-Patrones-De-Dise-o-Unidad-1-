public class ProductoFactory {
    private ProductoFactory() {}

    public static Producto crearProducto(String nombre, double precio, int cantidad, Categoria categoria) {
        return new Producto(nombre, precio, cantidad, categoria);
    }
}
