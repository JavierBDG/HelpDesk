import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void unTicketNuevoEstaAbierto() {
        Ticket ticket = new Ticket(1, "Falla el teclado");

        assertFalse(ticket.estaCerrado());
    }

    @Test
    void unTicketPuedeCerrarse() {
        Ticket ticket = new Ticket(1, "Falla el teclado");

        ticket.cerrar();

        assertTrue(ticket.estaCerrado());
    }

    @Test
    void cerrarUnTicketDosVecesSigueManteniendoloCerrado() {
        Ticket ticket = new Ticket(1, "Falla el teclado");

        ticket.cerrar();
        ticket.cerrar();

        assertTrue(ticket.estaCerrado());
    }

    @Test
    void unTicketConIdNegativoNoEsValido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Ticket(-1, "Falla el teclado")
        );
    }

    @Test
    void unTicketConIdCeroNoEsValido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Ticket(0, "Falla el teclado")
        );
    }

    @Test
    void unTicketConDescripcionVaciaNoEsValido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Ticket(1, "")
        );
    }

    @Test
    void unTicketConDescripcionSoloEspaciosNoEsValido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Ticket(1, "   ")
        );
    }

    @Test
    void unTicketConDescripcionNullNoEsValido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Ticket(1, null)
        );
    }
}