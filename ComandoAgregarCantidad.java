public class ComandoAgregarCantidad implements Comando {
    private final Producto producto;
    private final int delta;
    public ComandoAgregarCantidad(Producto producto, int delta) {
        this.producto = producto;
        this.delta = delta;
    }
    @Override
    public boolean ejecutar() {
        producto.setCantidad(producto.getCantidad() + delta);
        return true;
    }
    @Override
    public void deshacer() {
        producto.setCantidad(producto.getCantidad() - delta);
    }
}
