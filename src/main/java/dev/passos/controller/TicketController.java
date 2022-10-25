package dev.passos.controller;

import com.google.gson.Gson;
import dev.passos.entity.Ticket;
import dev.passos.service.EmployeeService;
import dev.passos.service.TicketService;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class TicketController {
    public Handler createTicket = (ctx) ->{
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // get body from request
            String json = ctx.body();

            // deserializes json into target2
            Gson gson = new Gson();
            Ticket ticket = gson.fromJson(json, Ticket.class);

            // pass to the service later for verification and then DB creation
            Ticket registeredTicket = TicketService.getTicketService().createTicket(ticket);

            // convert ticket object into a JSON object for the response
            String ticketJson = gson.toJson(registeredTicket);
            ctx.status(200); //This is a status code that will tell us how things went
            ctx.result(ticketJson);
        }
    };

    public Handler deleteTicket = (ctx) ->{
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // get id from request
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean result = TicketService.getTicketService().deleteTicket(id);

            // process result
            if(result){
                ctx.status(200); // no content
            }
            else{
                ctx.status(400); // bad request
                ctx.result("Could not process your delete request");
            }
        }
    };

    public Handler updateTicket = (ctx) ->{
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // get body from request
            String json = ctx.body();

            // deserializes json into target2
            Gson gson = new Gson();
            Ticket ticket = gson.fromJson(json, Ticket.class);

            // pass to the service later for verification and then DB creation
            Ticket registeredTicket = TicketService.getTicketService().updateTicket(ticket);

            // convert ticket object into a JSON object for the response
            String ticketJson = gson.toJson(registeredTicket);
            ctx.status(200); //This is a status code that will tell us how things went
            ctx.result(ticketJson);
        }
    };

    public Handler getTicket = (ctx) ->{
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // grab id
            int id = Integer.parseInt(ctx.pathParam("id"));

            // retrieve ticket
            try{
                Ticket ticket = TicketService.getTicketService().getTicket(id);

                // return found ticket into json
                Gson gson = new Gson();
                String json = gson.toJson(ticket);
                ctx.status(200);
                ctx.result(json);

            }catch(Exception e){
                ctx.status(400); // bad request
                ctx.result("Could not process your update request");
            }
        }
    };

    public Handler getAllPendingTickets = (ctx) -> {
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // call the DAO directly
            try{
                // get all pending tickets from the tickets table
                ArrayList<Ticket> allPendingTickets = TicketService.getTicketService().getAllPendingTickets();

                // transform data for the web
                Gson gson = new Gson();
                String json = gson.toJson(allPendingTickets);

                // response
                ctx.status(200);
                ctx.result(json);

            } catch (Exception e) {
                // error
                ctx.status(400); // bad request
                ctx.result("bad request, service might be in maintenance");
            }
        }
    };

    public Handler getPendingTickets = (ctx) -> {
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // grab employee id
            int id = Integer.parseInt(ctx.pathParam("id"));

            // retrieve ticket
            try{
                ArrayList<Ticket> employeePendingTickets = TicketService.getTicketService().getPendingTickets(id);

                // return found ticket into json
                Gson gson = new Gson();
                String json = gson.toJson(employeePendingTickets);

                // response
                ctx.status(200);
                ctx.result(json);
            }
            catch(RuntimeException e){
                ctx.status(400); // bad request
                ctx.result("bad request, double check your id");
            }
        }
    };
}
