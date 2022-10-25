package dev.passos.driver;

import dev.passos.controller.EmployeeController;
import dev.passos.controller.TicketController;
import dev.passos.service.TicketService;
import io.javalin.Javalin;

public class Driver {
    // entry point
    public static void main(String[] args) {
        // instance Javalin server object
        Javalin app = Javalin.create();

        // controllers
        TicketController ticketController = new TicketController();
        EmployeeController employeeController = new EmployeeController();


        // ## tickets endpoints ##
        app.post("/createTicket", ticketController.createTicket);
        app.delete("/deleteTicket/{id}", ticketController.deleteTicket);
        app.put("/updateTicket",ticketController.updateTicket);
        app.get("/getTicket/{id}",ticketController.getTicket);

        // ## employees endpoints ##
        app.post("/createEmployee", employeeController.createEmployee);
        app.delete("/deleteEmployee/{id}", employeeController.deleteEmployee);
        app.put("/updateEmployee",employeeController.updateEmployee);
        app.get("/getEmployee/{id}",employeeController.getEmployee);



        // ## login ##


        // start server
        app.start();


    }
}