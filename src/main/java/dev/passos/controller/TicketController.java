package dev.passos.controller;

import com.google.gson.Gson;
import dev.passos.driver.Driver;
import dev.passos.entity.Ticket;
import dev.passos.service.TicketService;
import io.javalin.http.Handler;

public class TicketController {
    public Handler createTicket = (ctx) ->{
        // get body from request
        String json = ctx.body();

        // deserializes json into target2
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);

        // pass to the service later for verification and then DB creation
        Ticket registeredTicket = TicketService.getTicketService().createTicket(ticket);

        // convert ticket object into a JSON object for the response
        String ticketJson = gson.toJson(registeredTicket);
        ctx.status(201); //This is a status code that will tell us how things went
        ctx.result(ticketJson);
    };

    public Handler deleteTicket = (ctx) ->{
        // get id from request
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean result = TicketService.getTicketService().deleteTicket(id);

        // process result
        if(result){
            ctx.status(204);
        }
        else{
            ctx.status(400);
            ctx.result("Could not process your delete request");
        }
    };

    public Handler updateTicket = (ctx) ->{
        // get body from request
        String json = ctx.body();

        // deserializes json into target2
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(json, Ticket.class);

        // pass to the service later for verification and then DB creation
        Ticket registeredTicket = TicketService.getTicketService().updateTicket(ticket);

        // convert ticket object into a JSON object for the response
        String ticketJson = gson.toJson(registeredTicket);
        ctx.status(201); //This is a status code that will tell us how things went
        ctx.result(ticketJson);
    };

    public Handler getTicket = (ctx) ->{
        // grab id
        int id = Integer.parseInt(ctx.pathParam("id"));

        // retrieve ticket
        Ticket ticket = TicketService.getTicketService().getTicket(id);

        // return found ticket into json
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        ctx.result(json);
    };
}
