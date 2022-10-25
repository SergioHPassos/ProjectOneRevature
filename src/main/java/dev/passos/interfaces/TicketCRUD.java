package dev.passos.interfaces;

import dev.passos.entity.Ticket;

import java.util.ArrayList;

public interface TicketCRUD {
    Ticket createTicket(Ticket ticket);
    Ticket getTicket(int id);
    Ticket updateTicket(Ticket ticket);
    boolean deleteTicket(int id);
    ArrayList<Ticket> getAllPendingTickets();
    ArrayList<Ticket> getPendingTickets(int id);
}
