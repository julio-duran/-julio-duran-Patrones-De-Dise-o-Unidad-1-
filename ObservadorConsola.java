public class ObservadorConsola implements Observador {
    @Override
    public void actualizar(String mensaje) {
        System.out.println("[OBSERVADOR] " + mensaje);
    }
}
