public interface Comando {
    /** @return true si se ejecutó; false si no fue posible */
    boolean ejecutar();
    void deshacer();
}
