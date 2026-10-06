public class Ticket {

    private int id;
    private String descripcion;
    private boolean cerrado;

    public Ticket(int id, String descripcion) {

        if (id <= 0) {
            throw new IllegalArgumentException("El identificador debe ser mayor que 0.");
        }

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía.");
        }

        this.id = id;
        this.descripcion = descripcion.trim();
        this.cerrado = false;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void cerrar() {
        cerrado = true;
    }

    public boolean estaCerrado() {
        return cerrado;
    }

    public String resumen() {

        String estado;

        if (cerrado) {
            estado = "CERRADO";
        } else {
            estado = "ABIERTO";
        }

        return "ID: " + id
                + "\nDescripción: " + descripcion
                + "\nEstado: " + estado;
    }
}