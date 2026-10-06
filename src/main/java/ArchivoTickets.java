import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ArchivoTickets {

    private Path ruta;

    public ArchivoTickets(String nombreArchivo) {
        ruta = Path.of(nombreArchivo);
    }

    public void guardar(List<Ticket> tickets) throws IOException {

        try (BufferedWriter escritor = Files.newBufferedWriter(
                ruta,
                StandardCharsets.UTF_8)) {

            for (Ticket ticket : tickets) {

                escritor.write(
                        ticket.getId()
                                + ";"
                                + ticket.estaCerrado()
                                + ";"
                                + ticket.getDescripcion()
                );

                escritor.newLine();
            }
        }
    }

    public ArrayList<Ticket> cargar() throws IOException {

        ArrayList<Ticket> tickets = new ArrayList<>();

        if (!Files.exists(ruta)) {
            return tickets;
        }

        try (BufferedReader lector = Files.newBufferedReader(
                ruta,
                StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] partes = linea.split(";", 3);

                if (partes.length != 3) {
                    throw new IOException("Formato de ticket no válido.");
                }

                int id;

                try {
                    id = Integer.parseInt(partes[0]);
                } catch (NumberFormatException e) {
                    throw new IOException(
                            "Identificador no válido: " + partes[0]);
                }

                boolean cerrado;

                if (partes[1].equals("true")) {
                    cerrado = true;
                } else if (partes[1].equals("false")) {
                    cerrado = false;
                } else {
                    throw new IOException(
                            "Estado no válido: " + partes[1]);
                }

                Ticket ticket = new Ticket(id, partes[2]);

                if (cerrado) {
                    ticket.cerrar();
                }

                tickets.add(ticket);
            }
        }

        return tickets;
    }
}