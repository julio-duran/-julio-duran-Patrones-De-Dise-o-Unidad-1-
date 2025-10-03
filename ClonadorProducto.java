public class ClonadorProducto {
    private ClonadorProducto() {}

    public static Producto clonarProducto(Producto original) {
        String nuevoNombre = original.getNombre() + " (copia)";
        // Nota: si original está decorado, getPrecio() ya refleja el descuento.
        Producto clon = new Producto(nuevoNombre, original.getPrecio(), original.getCantidad(), original.getCategoria());
        return clon;
    }
}
