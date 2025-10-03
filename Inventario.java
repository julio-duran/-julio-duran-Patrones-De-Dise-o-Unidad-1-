import java.util.*;

public class Inventario {
    private static final Inventario INSTANCIA = new Inventario();

    private final List<ComponenteInventario> raices = new ArrayList<>();
    private final List<Observador> observadores = new ArrayList<>();

    private Inventario() {}

    public static Inventario getInstancia() {
        return INSTANCIA;
    }

    public void agregarComponente(ComponenteInventario c) {
        raices.add(c);
    }

    public void registrarObservador(Observador o) {
        observadores.add(o);
    }

    public void notificarObservadores(String mensaje) {
        for (Observador o : observadores) {
            o.actualizar(mensaje);
        }
    }

    /** Búsqueda por nombre en todo el árbol (categorías y productos) */
    public ComponenteInventario buscarComponente(String nombre) {
        for (ComponenteInventario c : raices) {
            ComponenteInventario res = buscarRec(c, nombre);
            if (res != null) return res;
        }
        return null;
    }

    private ComponenteInventario buscarRec(ComponenteInventario actual, String nombre) {
        if (actual.getNombre().equalsIgnoreCase(nombre)) return actual;
        if (actual instanceof Categoria) {
            for (ComponenteInventario hijo : ((Categoria) actual).getHijos()) {
                ComponenteInventario res = buscarRec(hijo, nombre);
                if (res != null) return res;
            }
        }
        return null;
    }

    public void mostrarInventario() {
        if (raices.isEmpty()) {
            System.out.println("(Inventario vacío)");
            return;
        }
        for (ComponenteInventario c : raices) {
            imprimirRec(c, 0);
        }
    }

    private void imprimirRec(ComponenteInventario c, int nivel) {
        String indent = "  ".repeat(nivel);
        System.out.println(indent + "- " + c.descripcion());
        if (c instanceof Categoria) {
            for (ComponenteInventario h : ((Categoria) c).getHijos()) {
                imprimirRec(h, nivel + 1);
            }
        }
    }
}
