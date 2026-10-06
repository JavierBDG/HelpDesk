import java.util.ArrayList;

public class GestorTickets {

    private ArrayList<Ticket> tickets;
    private int siguienteId;

    public GestorTickets() {
        tickets = new ArrayList<>();
        siguienteId = 1;
    }

    public Ticket crearTicket(String descripcion) {
        Ticket ticket = new Ticket(siguienteId, descripcion);
        tickets.add(ticket);
        siguienteId++;
        return ticket;
    }

    public Ticket buscarTicket(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        return null;
    }

    public ArrayList<Ticket> getTickets() {
        return new ArrayList<>(tickets);
    }

    public void cargarTickets(ArrayList<Ticket> ticketsCargados) {

        int mayorId = 0;

        // Primero comprobamos todos los datos
        for (int i = 0; i < ticketsCargados.size(); i++) {

            Ticket ticketActual = ticketsCargados.get(i);

            if (ticketActual.getId() > mayorId) {
                mayorId = ticketActual.getId();
            }

            for (int j = i + 1; j < ticketsCargados.size(); j++) {

                Ticket otroTicket = ticketsCargados.get(j);

                if (ticketActual.getId() == otroTicket.getId()) {
                    throw new IllegalArgumentException(
                            "No puede haber identificadores repetidos."
                    );
                }
            }
        }

        // Solo modificamos el gestor después de validar todo
        tickets.clear();
        tickets.addAll(ticketsCargados);

        siguienteId = mayorId + 1;
    }

    public int getTotalTickets() {
        return tickets.size();
    }

    public int getTicketsCerrados() {

        int cerrados = 0;

        for (Ticket ticket : tickets) {
            if (ticket.estaCerrado()) {
                cerrados++;
            }
        }

        return cerrados;
    }

    public int getTicketsAbiertos() {
        return getTotalTickets() - getTicketsCerrados();
    }
}