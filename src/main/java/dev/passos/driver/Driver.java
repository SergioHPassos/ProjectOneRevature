package dev.passos.driver;

import dev.passos.controller.TicketController;
import dev.passos.service.TicketService;
import io.javalin.Javalin;

public class Driver {
    public static void main(String[] args) {
        // instance Javalin server object
        Javalin app = Javalin.create();

        // controllers
        TicketController ticketController = new TicketController();

        // ## tickets endpoints ##
        app.post("/createTicket", ticketController.createTicket);
        app.delete("/deleteTicket", ticketController.deleteTicket);
        app.put("/updateTicket",ticketController.updateTicket);
        app.get("/getTicket",ticketController.getTicket);

        // ## employees endpoints ##

        // start server
        app.start();


    }
}