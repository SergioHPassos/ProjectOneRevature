package dev.passos.interfaces;

import dev.passos.entity.Ticket;

public interface TicketCRUD {
    Ticket createTicket(Ticket ticket);
    Ticket getTicket(int id);
    Ticket updateTicket(Ticket ticket);
    boolean deleteTicket(int id);
}
