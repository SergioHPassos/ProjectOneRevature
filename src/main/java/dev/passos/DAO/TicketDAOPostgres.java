package dev.passos.DAO;

import dev.passos.entity.Ticket;
import dev.passos.interfaces.TicketCRUD;
import dev.passos.utility.DBConn;

import java.sql.*;
import java.util.ArrayList;

public class TicketDAOPostgres implements TicketCRUD {
    private static TicketDAOPostgres ticketDAOPostgres = null;

    public static TicketDAOPostgres getTicketDAOPostgres(){
        if(ticketDAOPostgres == null){
            ticketDAOPostgres = new TicketDAOPostgres();
        }
        return ticketDAOPostgres;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public Ticket createTicket(Ticket ticket) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "INSERT INTO \"companydata\".tickets VALUES(default, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setString(3, ticket.getStatus().name().toUpperCase());
            preparedStatement.setInt(4, ticket.getEmployee_id());
            preparedStatement.setString(5, ticket.getTicketType().name());

            // execute query command on DB
            preparedStatement.execute();

            // return newly created ticket record
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            // move head
            resultSet.next();

            // get auto generated id, and assign to ticket
            int generatedKey = resultSet.getInt("id");
            ticket.setId(generatedKey);

            //
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ticket getTicket(int id) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "SELECT * FROM \"companydata\".tickets WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // execute query command on DB
            ResultSet resultSet = preparedStatement.executeQuery();

            // move head
            resultSet.next();

            // create ticket object with data
            Ticket lookupTicket = new Ticket(
                    resultSet.getInt("id"),
                    resultSet.getDouble("amount"),
                    resultSet.getString("description"),
                    resultSet.getString("status"),
                    resultSet.getInt("employee_id"),
                    resultSet.getString("tickettype")
            );

            //
            return lookupTicket;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public Ticket updateTicket(Ticket ticket) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "UPDATE \"companydata\".tickets SET amount=?, description=?, status=?, tickettype=? WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setString(3, ticket.getStatus().name().toUpperCase());
            preparedStatement.setString(4, ticket.getTicketType().name());
            preparedStatement.setInt(5, ticket.getId());

            // execute query command on DB
            preparedStatement.executeUpdate();

            //
            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteTicket(int id) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "DELETE FROM \"companydata\".tickets WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // execute query command on DB
            preparedStatement.execute();

            //
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Ticket> getAllPendingTickets() {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "SELECT * FROM \"companydata\".tickets WHERE status='PENDING'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // execute query command on DB
            ResultSet resultSet = preparedStatement.executeQuery();


            // create ticket object with data
            ArrayList<Ticket> pendingTickets = new ArrayList<Ticket>();
            while(resultSet.next()){
                // create ticket
                Ticket localTicket = new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("employee_id"),
                        resultSet.getString("tickettype")
                );

                // add ticket to arraylist
                pendingTickets.add(localTicket);
            }

            //
            return pendingTickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ArrayList<Ticket> getPendingTickets(int id) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "SELECT * FROM \"companydata\".tickets WHERE employee_id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // execute query command on DB
            ResultSet resultSet = preparedStatement.executeQuery();


            // create ticket object with data
            ArrayList<Ticket> pendingTickets = new ArrayList<Ticket>();
            while(resultSet.next()){
                // create ticket
                Ticket localTicket = new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("description"),
                        resultSet.getString("status"),
                        resultSet.getInt("employee_id"),
                        resultSet.getString("tickettype")
                );

                // add ticket to arraylist
                pendingTickets.add(localTicket);
            }

            //
            return pendingTickets;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}