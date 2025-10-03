public class ProductoConDescuento extends Producto {
    private final double porcentaje;

    public ProductoConDescuento(Producto base, double porcentaje) {
        super(base.getNombre() + " (desc.)", base.getPrecio(), base.getCantidad(), base.getCategoria());
        this.porcentaje = porcentaje;
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() * (1.0 - porcentaje);
    }

    public double getPorcentaje() {
        return porcentaje;
    }
}
