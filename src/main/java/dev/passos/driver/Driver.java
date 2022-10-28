package dev.passos.driver;

import com.google.gson.Gson;
import dev.passos.DAO.EmployeeDAOPostgres;
import dev.passos.DAO.TicketDAOPostgres;
import dev.passos.controller.EmployeeController;
import dev.passos.controller.TicketController;
import dev.passos.entity.Employee;
import dev.passos.entity.Ticket;
import dev.passos.service.TicketService;
import dev.passos.utility.AESEncryptionDecryption;
import dev.passos.utility.DBConn;
import io.javalin.Javalin;
import io.javalin.core.security.BasicAuthCredentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Driver {
    private static String secretKey = "^zyPr%#!BVk*Y8qx";
    // entry point
    public static void main(String[] args) {
        // instance Javalin server object
        Javalin app = Javalin.create();

        // controllers
        TicketController ticketController = new TicketController();
        EmployeeController employeeController = new EmployeeController();

        // auth object
        AESEncryptionDecryption auth = new AESEncryptionDecryption();
        auth.prepareSecreteKey(secretKey);

        // set status 200 default
        app.before(ctx -> {
            ctx.status(200);
        });

        app.before("/auth/*", ctx -> {
            try{
            // get token
            String encryptedCredentials = ctx.header("Authorization").split(" ")[1];

                // decrypt token
                try{
                    String[] decryptedCredentials = auth.decrypt(encryptedCredentials, secretKey).split(" "); // [0]email, [1]password

                    // verify data
                    try(Connection connection = DBConn.getConnection()){
                        // sql query
                        String sql = "SELECT * FROM \"companydata\".employees WHERE email=? AND password=?";

                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, decryptedCredentials[0]);
                        preparedStatement.setString(2, decryptedCredentials[1]);

                        // execute query command on DB
                        ResultSet resultSet = preparedStatement.executeQuery();

                        // user has been verified
                        if(resultSet.next()){
                            ctx.status(200);
                        } else{
                            // invalid authorization
                            ctx.status(403);
                        }


                        // clean up
                        resultSet.close();
                        connection.close();
                    }
                    catch (SQLException e) {
                        ctx.status(401);
                        e.printStackTrace();
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                    ctx.status(401);
                }
            }
            catch(Exception e){
                System.out.println(e);
                ctx.status(401);
            }
        });

        app.before("/auth/updateTicket", ctx -> {
            // get body from request
            String json = ctx.body();

            // deserializes json into target2
            Gson gson = new Gson();
            Ticket ticket = gson.fromJson(json, Ticket.class);

            // check if status change is requested, if so make sure it's a manager
            if(ticket.getStatus().equals(Ticket.ApprovalType.APPROVED)){
                try{
                    // get token
                    String encryptedCredentials = ctx.header("Authorization").split(" ")[1];
                    String[] decryptedCredentials = auth.decrypt(encryptedCredentials, secretKey).split(" "); // [0]email, [1]password

                    // get employee from DAO
                    Employee employee = EmployeeDAOPostgres.getEmployeeDAOPostgres().getEmployeeByEmail(decryptedCredentials[0]);

                    // check if manager
                    ctx.status(employee.isManager() ? 200  : 403); // forbidden

                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                ctx.status(200);
            }
        });
        app.before("/auth/getAllPendingTickets", ctx -> {
            try{
                // get token
                String encryptedCredentials = ctx.header("Authorization").split(" ")[1];
                String[] decryptedCredentials = auth.decrypt(encryptedCredentials, secretKey).split(" "); // [0]email, [1]password

                // get employee from DAO
                Employee employee = EmployeeDAOPostgres.getEmployeeDAOPostgres().getEmployeeByEmail(decryptedCredentials[0]);

                // check if manager
                ctx.status(employee.isManager() ? 200  : 403); // forbidden

            }
            catch(Exception e){
                e.printStackTrace();
            }
        });


        // ## tickets endpoints ##
        app.post("/auth/createTicket", ticketController.createTicket);
        app.delete("/auth/deleteTicket/{id}", ticketController.deleteTicket);
        app.put("/auth/updateTicket",ticketController.updateTicket);
        app.get("/auth/getTicket/{id}",ticketController.getTicket);
        app.get("/auth/getAllPendingTickets", ticketController.getAllPendingTickets); // every pending ticket
        app.get("/auth/getAllPendingTickets/{id}", ticketController.getPendingTickets); // all pending tickets from an employee id

        // ## employees endpoints ##
        app.post("/createEmployee", employeeController.createEmployee);
        app.delete("/auth/deleteEmployee/{id}", employeeController.deleteEmployee);
        app.put("/auth/updateEmployee",employeeController.updateEmployee);
        app.get("/auth/getEmployee/{id}",employeeController.getEmployee);



        // ## login ##
        app.post("/login", ctx -> {
            // decode the basic auth header
            BasicAuthCredentials credentials = ctx.basicAuthCredentials();

            // basic verification layer/service
            if(credentials.getUsername().length() == 0 || credentials.getPassword().length() == 0){
                // something went wrong
                ctx.status(400); // status code
                ctx.result("bad request, check your email and password");
            }

            // verify data
            Boolean isVerified = false;
            try(Connection connection = DBConn.getConnection()){
                // sql query
                String sql = "SELECT * FROM \"companydata\".employees WHERE email=? AND password=?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, credentials.getUsername());
                preparedStatement.setString(2, credentials.getPassword());

                // execute query command on DB
                ResultSet resultSet = preparedStatement.executeQuery();

                // user has been verified
                if(resultSet.next()){
                    isVerified = true;
                }

                // clean up
                resultSet.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // encrypt data
            String toEncrypt = credentials.getUsername()+" "+credentials.getPassword();
            ctx.header("Bearer", auth.encrypt(toEncrypt, secretKey));
            ctx.status(200);
            ctx.result("login successful");
        });


        // start server
        app.start();


    }
}