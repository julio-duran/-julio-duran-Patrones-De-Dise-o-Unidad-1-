import java.util.ArrayDeque;
import java.util.Deque;

public class GestorComandos {
    private final Deque<Comando> pila = new ArrayDeque<>();

    public boolean ejecutarComando(Comando c) {
        boolean ok = c.ejecutar();
        if (ok) pila.push(c);
        return ok;
    }

    public void deshacerUltimo() {
        if (pila.isEmpty()) {
            System.out.println("No hay operaciones para deshacer.");
            return;
        }
        Comando c = pila.pop();
        c.deshacer();
        System.out.println("Operación deshecha.");
    }
}
