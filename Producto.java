public class Producto extends ComponenteInventario {
    protected double precio;
    protected int cantidad;
    protected Categoria categoria;

    public Producto(String nombre, double precio, int cantidad, Categoria categoria) {
        super(nombre);
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }

    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = Math.max(0, cantidad); }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    public String descripcion() {
        String cat = (categoria != null) ? categoria.getNombre() : "sin categoría";
        return "Producto: " + nombre + " | precio=" + getPrecio() + " | cant=" + cantidad + " | cat=" + cat;
    }

    @Override
    public String toString() {
        return descripcion();
    }
}
