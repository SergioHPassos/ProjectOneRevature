package dev.passos.DAO;

import dev.passos.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TicketDAOTests {

    // create ticket test
    @Test
    @Order(1)
    void create_ticket_test(){
        Ticket testingTicket = new Ticket(1, 1500, "for gas", Ticket.ApprovalType.PENDING.name(), 4, Ticket.TicketType.TRAVEL.name());
        Ticket savedTicket = TicketDAOPostgres.getTicketDAOPostgres().createTicket(testingTicket);
        Assertions.assertNotEquals(1, savedTicket.getId());

        // clean up database from test data
        TicketDAOPostgres.getTicketDAOPostgres().deleteTicket(savedTicket.getId());
    }

    // get ticket
    @Test
    @Order(2)
    void get_ticket_test(){
        // get random tickets id
        int id = TicketDAOPostgres.getTicketDAOPostgres().getAllPendingTickets().get(0).getId();

        // get ticket
        Ticket getTicket = TicketDAOPostgres.getTicketDAOPostgres().getTicket(id);

        // assert
        id++;
        Assertions.assertNotEquals(id, getTicket.getId());
    }

    // update ticket
    @Test
    @Order(3)
    void update_ticket_test(){
        // get random tickets id
        int id = TicketDAOPostgres.getTicketDAOPostgres().getAllPendingTickets().get(0).getId();

        // get ticket
        Ticket ticket = TicketDAOPostgres.getTicketDAOPostgres().getTicket(id);

        // update ticket
        double ogAmount = ticket.getAmount();
        ticket.setAmount((ogAmount+500)); // make change
        Ticket updatedTicket = TicketDAOPostgres.getTicketDAOPostgres().updateTicket(ticket);

        // assert
        Assertions.assertNotEquals(ogAmount, updatedTicket.getAmount());

        // clean up data
        ticket.setAmount(ogAmount);
        TicketDAOPostgres.getTicketDAOPostgres().updateTicket(ticket);
    }

    // delete ticket
    @Test
    @Order(4)
    void delete_ticket_test(){
        // create ticket
        Ticket testingTicket = new Ticket(1, 1500, "for gas", Ticket.ApprovalType.PENDING.name(), 4, Ticket.TicketType.TRAVEL.name());
        Ticket savedTicket = TicketDAOPostgres.getTicketDAOPostgres().createTicket(testingTicket);

        // delete ticket
        boolean wasDeleted = TicketDAOPostgres.getTicketDAOPostgres().deleteTicket(savedTicket.getId());

        // assert
        Assertions.assertEquals(true, wasDeleted);
    }

    // get all pending tickets, for manager
    @Test
    @Order(5)
    void get_all_pending_tickets_test(){
        // get all pending tickets
        ArrayList<Ticket> pendingTickets = TicketDAOPostgres.getTicketDAOPostgres().getAllPendingTickets();

        // assert
        Assertions.assertEquals(true, pendingTickets instanceof ArrayList ? true : false);
    }

    // get all pending tickets for employee
    @Test
    @Order(6)
    void get_pending_tickets_test(){
        // get all pending tickets
        ArrayList<Ticket> pendingTickets = TicketDAOPostgres.getTicketDAOPostgres().getPendingTickets(5);

        // assert
        Assertions.assertEquals(true, pendingTickets instanceof ArrayList ? true : false);
    }
}
