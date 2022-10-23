package dev.passos.entity;

public class Ticket {
    // instance variables
    private int id;
    private int amount = 0;
    private String description = "";
    private boolean status = false;

    // constructor w/ parameters
    public Ticket(int amount, String description, boolean status){
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    // constructor w/ parameters
    public Ticket(int id, int amount, String description, boolean status){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
    }

    // default constructor
    public Ticket(){}

    // getters and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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
}
