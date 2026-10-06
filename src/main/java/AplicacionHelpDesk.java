import java.io.IOException;
import java.util.Scanner;

public class AplicacionHelpDesk {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        GestorTickets gestor = new GestorTickets();
        ArchivoTickets archivo = new ArchivoTickets("tickets.txt");

        try {
            gestor.cargarTickets(archivo.cargar());
            System.out.println("Incidencias cargadas correctamente.");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("ERROR al cargar las incidencias: " + e.getMessage());
            System.out.println("No se puede iniciar la aplicación.");
            teclado.close();
            return;
        }

        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(teclado);

            switch (opcion) {
                case 1:
                    crearTicket(teclado, gestor);
                    break;

                case 2:
                    listarTickets(gestor);
                    break;

                case 3:
                    buscarTicket(teclado, gestor);
                    break;

                case 4:
                    cerrarTicket(teclado, gestor);
                    break;

                case 5:
                    mostrarEstadisticas(gestor);
                    break;

                case 6:
                    guardarTickets(gestor, archivo);
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Cerrando HelpDesk...");
                    System.out.println("Recuerda guardar antes de salir.");
                    break;

                default:
                    System.out.println();
                    System.out.println("ERROR: opción no válida.");
                    break;
            }

        } while (opcion != 0);

        teclado.close();
    }

    public static void mostrarMenu() {
        System.out.println();
        System.out.println("=== HELPDESK DEL CENTRO ===");
        System.out.println("1. Crear incidencia");
        System.out.println("2. Listar incidencias");
        System.out.println("3. Buscar incidencia por identificador");
        System.out.println("4. Cerrar incidencia");
        System.out.println("5. Mostrar estadísticas");
        System.out.println("6. Guardar incidencias");
        System.out.println("0. Salir");
    }

    public static int leerOpcion(Scanner teclado) {
        while (true) {
            System.out.print("Seleccione una opción: ");

            String entrada = teclado.nextLine();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: debes introducir un número.");
            }
        }
    }

    public static void crearTicket(
            Scanner teclado,
            GestorTickets gestor) {

        System.out.println();
        System.out.println("=== CREAR INCIDENCIA ===");
        System.out.print("Descripción: ");

        String descripcion = teclado.nextLine();

        try {
            Ticket ticket = gestor.crearTicket(descripcion);

            System.out.println("Incidencia creada correctamente.");
            System.out.println("ID asignado: " + ticket.getId());

        } catch (IllegalArgumentException e) {

            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void listarTickets(GestorTickets gestor) {

        System.out.println();
        System.out.println("=== LISTA DE INCIDENCIAS ===");

        if (gestor.getTickets().isEmpty()) {
            System.out.println("No hay incidencias registradas.");
            return;
        }

        for (Ticket ticket : gestor.getTickets()) {
            System.out.println();
            System.out.println(ticket.resumen());
        }
    }

    public static void buscarTicket(
            Scanner teclado,
            GestorTickets gestor) {

        System.out.println();
        System.out.println("=== BUSCAR INCIDENCIA ===");

        int id = leerId(teclado);

        Ticket ticket = gestor.buscarTicket(id);

        if (ticket == null) {
            System.out.println("No existe una incidencia con ese ID.");
        } else {
            System.out.println();
            System.out.println(ticket.resumen());
        }
    }

    public static void cerrarTicket(
            Scanner teclado,
            GestorTickets gestor) {

        System.out.println();
        System.out.println("=== CERRAR INCIDENCIA ===");

        int id = leerId(teclado);

        Ticket ticket = gestor.buscarTicket(id);

        if (ticket == null) {
            System.out.println("No existe una incidencia con ese ID.");

        } else if (ticket.estaCerrado()) {
            System.out.println("La incidencia ya está cerrada.");

        } else {
            ticket.cerrar();
            System.out.println("Incidencia cerrada correctamente.");
        }
    }

    public static void mostrarEstadisticas(GestorTickets gestor) {
        System.out.println();
        System.out.println("=== ESTADÍSTICAS ===");

        int total = gestor.getTotalTickets();
        int abiertas = gestor.getTicketsAbiertos();
        int cerradas = gestor.getTicketsCerrados();

        System.out.println("Total de incidencias: " + total);
        System.out.println("Incidencias abiertas: " + abiertas);
        System.out.println("Incidencias cerradas: " + cerradas);
    }

    public static void guardarTickets(
            GestorTickets gestor,
            ArchivoTickets archivo) {

        try {
            archivo.guardar(gestor.getTickets());

            System.out.println();
            System.out.println("Incidencias guardadas correctamente.");

        } catch (IOException e) {

            System.out.println();
            System.out.println("ERROR al guardar las incidencias: "
                    + e.getMessage());
        }
    }

    public static int leerId(Scanner teclado) {

        while (true) {

            System.out.print("ID de la incidencia: ");

            String entrada = teclado.nextLine();

            try {

                int id = Integer.parseInt(entrada);

                if (id <= 0) {
                    System.out.println(
                            "ERROR: el identificador debe ser mayor que 0.");
                } else {
                    return id;
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "ERROR: debes introducir un número entero válido.");
            }
        }
    }
}