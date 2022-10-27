package dev.passos.entity;

import java.util.Objects;

public class Ticket {
    // instance variables
    private int id;
    private double amount = 0;
    private String description = "";
    private ApprovalType status = ApprovalType.PENDING;
    private int employee_id;

    private TicketType tickettype = TicketType.TRAVEL;
    public static enum TicketType {
        TRAVEL,
        LODGING,
        FOOD,
        OTHER
    }
    public static enum ApprovalType {
        PENDING,
        APPROVED,
        DENIED
    }

    // constructor w/ parameters
    public Ticket(double amount, String description, String status, int employee_id, String tickettype){
        this.amount = amount;
        this.description = description;
        this.status = ApprovalType.valueOf(status.toUpperCase());
        this.employee_id = employee_id;
        this.tickettype = TicketType.valueOf(tickettype.toUpperCase());

    }

    // constructor w/ parameters
    public Ticket(int id, double amount, String description, String status, int employee_id, String tickettype){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = ApprovalType.valueOf(status.toUpperCase());
        this.employee_id = employee_id;
        this.tickettype = TicketType.valueOf(tickettype.toUpperCase());
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
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApprovalType getStatus() {
        return this.status;
    }

    public void setStatus(ApprovalType status) {
        this.status = status;
    }

    public int getEmployee_id() {
        return this.employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public TicketType getTicketType() {
        return this.tickettype;
    }

    public void setTicketType(TicketType tickettype) {
        this.tickettype = tickettype;
    }
}
