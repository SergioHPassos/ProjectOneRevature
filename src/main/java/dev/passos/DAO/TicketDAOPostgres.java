package dev.passos.DAO;

import dev.passos.entity.Ticket;
import dev.passos.interfaces.TicketCRUD;
import dev.passos.utility.DBConn;

import java.sql.Connection;
import java.sql.SQLException;

public class TicketDAOPostgres implements TicketCRUD {

    private static TicketDAOPostgres db = null;

    public static TicketDAOPostgres getDB(){
        if(db == null){
            db = new TicketDAOPostgres();
        }
        return db;
    }

    /**
     * @param ticket
     * @return
     */
    @Override
    public Ticket createTicket(Ticket ticket) {
        try(Connection connection = DBConn.getConnection()){
            String sql = "insert into Ticket value(default, ?, ?, default, )";
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
    public Ticket getTicket(int id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Ticket updateTicket(int id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteTicket(int id) {
        return false;
    }
}