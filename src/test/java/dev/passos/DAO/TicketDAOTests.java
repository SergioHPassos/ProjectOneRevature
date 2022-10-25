package dev.passos.DAO;

import dev.passos.entity.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketDAOTests {

    // create ticket test
    @Test
    void create_ticket_test(){
        Ticket testingTicket = new Ticket(1, 1500, "for gas", false, 4);
        Ticket savedTicket = TicketDAOPostgres.getTicketDAOPostgres().createTicket(testingTicket);
        Assertions.assertNotEquals(1, savedTicket.getId());

        // clean up database from test data
        TicketDAOPostgres.getTicketDAOPostgres().deleteTicket(savedTicket.getId());
    }

    // get ticket
    void get_ticket_test(){

    }

    // update ticket
    void update_ticket_test(){}

    // delete ticket
    void delete_ticket_test(){
        //get testing data
//        Ticket testingTicket = TicketDAOPostgres.getTicketDAOPostgres().findTicket(1500, "for gas");
    }
}
