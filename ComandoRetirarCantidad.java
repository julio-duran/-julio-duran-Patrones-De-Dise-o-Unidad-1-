public class ComandoRetirarCantidad implements Comando {
    private final Producto producto;
    private final int delta;
    public ComandoRetirarCantidad(Producto producto, int delta) {
        this.producto = producto;
        this.delta = delta;
    }
    @Override
    public boolean ejecutar() {
        if (producto.getCantidad() < delta) {
            return false; // evitar negativo
        }
        producto.setCantidad(producto.getCantidad() - delta);
        return true;
    }
    @Override
    public void deshacer() {
        producto.setCantidad(producto.getCantidad() + delta);
    }
}
