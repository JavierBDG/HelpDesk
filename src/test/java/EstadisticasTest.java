import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    @Test
    void unGestorVacioTieneTodasLasEstadisticasA0() {
        GestorTickets gestor = new GestorTickets();

        assertEquals(0, gestor.getTotalTickets());
        assertEquals(0, gestor.getTicketsAbiertos());
        assertEquals(0, gestor.getTicketsCerrados());
    }

    @Test
    void dosTicketsNuevosSonDosAbiertos() {
        GestorTickets gestor = new GestorTickets();

        gestor.crearTicket("Falla el teclado");
        gestor.crearTicket("No funciona el ratón");

        assertEquals(2, gestor.getTotalTickets());
        assertEquals(2, gestor.getTicketsAbiertos());
        assertEquals(0, gestor.getTicketsCerrados());
    }

    @Test
    void dosTicketsConUnoCerradoDanUnaAbiertaYUnaCerrada() {
        GestorTickets gestor = new GestorTickets();

        Ticket ticket1 = gestor.crearTicket("Falla el teclado");
        gestor.crearTicket("No funciona el ratón");

        ticket1.cerrar();

        assertEquals(2, gestor.getTotalTickets());
        assertEquals(1, gestor.getTicketsAbiertos());
        assertEquals(1, gestor.getTicketsCerrados());
    }
}