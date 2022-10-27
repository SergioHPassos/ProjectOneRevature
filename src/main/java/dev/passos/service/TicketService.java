package dev.passos.service;

import dev.passos.entity.Ticket;
import dev.passos.interfaces.TicketCRUD;
import dev.passos.DAO.TicketDAOPostgres;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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
    public Ticket createTicket(@NotNull Ticket ticket) {
        // verifications checks
        try{
            if(ticket.getAmount() == 0){
                throw new RuntimeException("Ticket amount should be greater than 0");
            } else if(ticket.getDescription().length() == 0){
                throw new RuntimeException("Ticket should always have a description");
            } else if(!(ticket.getStatus() instanceof Ticket.ApprovalType)){
                // not standard status
                throw new RuntimeException("status has to be pending, approved, or denied");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

        // create ticket in database
        Ticket registeredTicket = TicketDAOPostgres.getTicketDAOPostgres().createTicket(ticket);

        return registeredTicket;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ticket getTicket(int id) {
        // verifications checks
        if(id < 0){
            throw new RuntimeException("Ticket id should be greater than 0");
        }

        // look up ticket by id
        Ticket lookupTicket = TicketDAOPostgres.getTicketDAOPostgres().getTicket(id);

        return lookupTicket;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public Ticket updateTicket(Ticket ticket) {
        // verifications checks
        if(ticket.getAmount() == 0){
            throw new RuntimeException("Ticket amount should be greater than 0");
        } else if(ticket.getDescription().length() == 0){
            throw new RuntimeException("Ticket should always have a description");
        } else if(!(ticket.getStatus() instanceof Ticket.ApprovalType)){
            // not standard status
            throw new RuntimeException("status has to be pending, approved, or denied");
        }

        // if already approved, then make no changes
        if(TicketDAOPostgres.getTicketDAOPostgres().getTicket(ticket.getId()).getStatus().equals(Ticket.ApprovalType.APPROVED)){
            return null;
        }

        // look up ticket by id
        Ticket updatedTicket = TicketDAOPostgres.getTicketDAOPostgres().updateTicket(ticket);

        return updatedTicket;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteTicket(int id) {
        // verifications checks
        if(id < 0){
            throw new RuntimeException("Ticket id should be greater than 0");
        }

        // look up ticket by id
        boolean isDeleted = TicketDAOPostgres.getTicketDAOPostgres().deleteTicket(id);

        return isDeleted;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Ticket> getAllPendingTickets() {
        // access DAO
        ArrayList<Ticket> allPendingTickets = TicketDAOPostgres.getTicketDAOPostgres().getAllPendingTickets();

        // return
        return allPendingTickets;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ArrayList<Ticket> getPendingTickets(int id) {
        // verification checks
        if(id < 0){
            throw new RuntimeException("Ticket id should be greater than 0");
        }

        // access DAO
        ArrayList<Ticket> pendingTickets = TicketDAOPostgres.getTicketDAOPostgres().getPendingTickets(id);

        // return
        return pendingTickets;
    }
}