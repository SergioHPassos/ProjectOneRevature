package dev.passos.entity;

import java.util.Objects;

public class Employee {
    // instance variables
    private int id;
    private String firstName = "";
    private String lastName = "";
    private String password = "";
    private boolean isManager = false;
    private String email = "";

    public Employee(String firstName, String lastName, String password, boolean isManager, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isManager = isManager;
        this.email = email;
    }

    public Employee(int id, String firstName, String lastName, String password, boolean isManager, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isManager = isManager;
        this.email = email;
    }

    public Employee() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && isManager == employee.isManager && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(password, employee.password) && Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, password, isManager, email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
