package dev.passos.entity;

import java.util.Objects;

public class Ticket {
    // instance variables
    private int id;
    private double amount = 0;
    private String description = "";
    private boolean status = false;
    private int employee_id;

    private TicketType ticketType = TicketType.TRAVEL;
    public static enum TicketType {
        TRAVEL,
        LODGING,
        FOOD,
        OTHER
    }

    // constructor w/ parameters
    public Ticket(double amount, String description, boolean status, int employee_id, String ticketType){
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.employee_id = employee_id;
        this.ticketType = TicketType.valueOf(ticketType.toUpperCase());

    }

    // constructor w/ parameters
    public Ticket(int id, double amount, String description, boolean status, int employee_id, String ticketType){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.employee_id = employee_id;
        this.ticketType = TicketType.valueOf(ticketType.toUpperCase());
    }

    // default constructor
    public Ticket(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && amount == ticket.amount && status == ticket.status && employee_id == ticket.employee_id && Objects.equals(description, ticket.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, status, employee_id);
    }

    // getters and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
}
