package dev.passos.interfaces;

import dev.passos.entity.Ticket;

public interface TicketCRUD {
    Ticket createTicket(Ticket ticket);
    Ticket getTicket(int id);
    Ticket updateTicket(int id);
    boolean deleteTicket(int id);
}
