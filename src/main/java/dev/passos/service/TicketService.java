package dev.passos.service;

import dev.passos.entity.Ticket;
import dev.passos.interfaces.TicketCRUD;
import dev.passos.DAO.TicketDAOPostgres;

public class TicketService implements TicketCRUD {

    private static TicketService ticketService = null;

    public static TicketService getTicketService(){
        if (ticketService == null) {
            ticketService = new TicketService();
        }
        return ticketService;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public Ticket createTicket(Ticket ticket) {
        // verifications checks

        // create ticket in database
        Ticket registeredTicket = TicketDAOPostgres.getDB().createTicket(ticket);

        return registeredTicket;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ticket getTicket(int id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ticket updateTicket(int id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteTicket(int id) {
        return false;
    }
}
