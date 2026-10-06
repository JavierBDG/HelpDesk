import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorTicketsTest {

    @Test
    void unGestorNuevoEstaVacio() {
        GestorTickets gestor = new GestorTickets();

        assertTrue(gestor.getTickets().isEmpty());
    }

    @Test
    void crearTicketAsignaIdCorrectamente() {
        GestorTickets gestor = new GestorTickets();

        Ticket ticket = gestor.crearTicket("Falla el teclado");

        assertEquals(1, ticket.getId());
    }

    @Test
    void crearVariosTicketsAsignaIdsConsecutivos() {
        GestorTickets gestor = new GestorTickets();

        Ticket ticket1 = gestor.crearTicket("Falla el teclado");
        Ticket ticket2 = gestor.crearTicket("No funciona el ratón");
        Ticket ticket3 = gestor.crearTicket("Problema con la pantalla");

        assertEquals(1, ticket1.getId());
        assertEquals(2, ticket2.getId());
        assertEquals(3, ticket3.getId());
    }

    @Test
    void buscarTicketDevuelveElMismoObjeto() {
        GestorTickets gestor = new GestorTickets();

        Ticket ticket = gestor.crearTicket("Falla el teclado");
        Ticket encontrado = gestor.buscarTicket(ticket.getId());

        assertSame(ticket, encontrado);
    }

    @Test
    void buscarTicketInexistenteDevuelveNull() {
        GestorTickets gestor = new GestorTickets();

        Ticket encontrado = gestor.buscarTicket(99);

        assertNull(encontrado);
    }

    @Test
    void crearTicketConDescripcionInvalidaNoModificaElGestor() {
        GestorTickets gestor = new GestorTickets();

        assertThrows(
                IllegalArgumentException.class,
                () -> gestor.crearTicket("")
        );

        assertEquals(0, gestor.getTickets().size());
    }

    @Test
    void crearTicketInvalidoNoConsumeElSiguienteId() {
        GestorTickets gestor = new GestorTickets();

        assertThrows(
                IllegalArgumentException.class,
                () -> gestor.crearTicket("   ")
        );

        Ticket ticket = gestor.crearTicket("Falla el teclado");

        assertEquals(1, ticket.getId());
    }

    @Test
    void getTicketsDevuelveUnaCopiaDeLaLista() {
        GestorTickets gestor = new GestorTickets();

        gestor.crearTicket("Falla el teclado");

        ArrayList<Ticket> lista = gestor.getTickets();

        lista.clear();

        assertEquals(1, gestor.getTickets().size());
    }
}