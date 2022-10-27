package dev.passos.controller;

import com.google.gson.Gson;
import dev.passos.entity.Employee;
import dev.passos.entity.Ticket;
import dev.passos.service.EmployeeService;
import io.javalin.http.Handler;

public class EmployeeController {
    public Handler createEmployee = (ctx) ->{
        // get body from request
        String json = ctx.body();

        // deserializes json into target
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json, Employee.class);

        // pass to the service later for verification and then DB creation
        try{
            Employee registeredEmployee = EmployeeService.getEmployeeService().createEmployee(employee);

            // email already being used
            if(registeredEmployee == null){
                ctx.status(400);
                ctx.result("email is already being used");
            }else{
                // convert ticket object into a JSON object for the response
                String employeeJson = gson.toJson(registeredEmployee);
                ctx.status(201); //This is a status code that will tell us how things went
                ctx.result(employeeJson);
            }
        } catch(Exception e){
            ctx.status(400); //This is a status code that will tell us how things went
            ctx.result("bad request, email is already taken");
        }
    };

    public Handler deleteEmployee = (ctx) ->{
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // get id from request
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean result = EmployeeService.getEmployeeService().deleteEmployee(id);

            // process result
            if(result){
                ctx.status(204);
                ctx.result("employee account was deleted");
            }
            else{
                ctx.status(400);
                ctx.result("Could not process your delete request");
            }
        }
    };

    public Handler updateEmployee = (ctx) ->{
        // unauthorized
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // get body from request
            String json = ctx.body();

            // deserializes json into target2
            Gson gson = new Gson();
            Employee employee = gson.fromJson(json, Employee.class);

            // pass to the service later for verification and then DB creation
            Employee registeredEmployee = EmployeeService.getEmployeeService().updateEmployee(employee);

            // convert ticket object into a JSON object for the response
            String employeeJson = gson.toJson(registeredEmployee);
            ctx.status(201); //This is a status code that will tell us how things went
            ctx.result(employeeJson);
        }
    };

    public Handler getEmployee = (ctx) ->{
        if(ctx.status() == 401){
            ctx.result("check email and password");
        }
        else{
            // grab id
            int id = Integer.parseInt(ctx.pathParam("id"));

            // retrieve ticket
            Employee employee = EmployeeService.getEmployeeService().getEmployee(id);

            // return found ticket into json
            Gson gson = new Gson();
            String json = gson.toJson(employee);
            ctx.status(201);
            ctx.result(json);
        }
    };
}
