package dev.passos.DAO;

import dev.passos.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class EmployeeDAOTests {

    // create ticket test
    @Test
    @Order(1)
    void create_employee_test(){
        Employee testingEmployee = new Employee(1, "General", "Finger", "ultrahardpassword", false, "testing@yesemail.com");
        Employee savedEmployee = EmployeeDAOPostgres.getEmployeeDAOPostgres().createEmployee(testingEmployee);

        // assert
        Assertions.assertNotEquals(1, savedEmployee.getId());

        // clean up database from test data
        EmployeeDAOPostgres.getEmployeeDAOPostgres().deleteEmployee(savedEmployee.getId());
    }

    // get ticket
    @Test
    @Order(2)
    void get_employee_test(){
        // get employee
        Employee employee = EmployeeDAOPostgres.getEmployeeDAOPostgres().getEmployee(1);

        // assert
        Assertions.assertNotEquals(9, employee.getId());
    }

    // update ticket
    @Test
    @Order(3)
    void update_employee_test(){
        // get employee
        Employee employee = EmployeeDAOPostgres.getEmployeeDAOPostgres().getEmployee(1);

        // update
        employee.setFirstName("Barnical");
        Employee updatedEmployee = EmployeeDAOPostgres.getEmployeeDAOPostgres().updateEmployee(employee);

        // assert
        Assertions.assertNotEquals("sergio", updatedEmployee.getFirstName().toLowerCase());

        // clean data
        employee.setFirstName("Sergio");
        EmployeeDAOPostgres.getEmployeeDAOPostgres().updateEmployee(employee);
    }

    // delete ticket
    @Test
    @Order(4)
    void delete_employee_test(){
        Employee testingEmployee = new Employee(1, "General", "Finger", "ultrahardpassword", false, "tester@yesemail.com");
        Employee savedEmployee = EmployeeDAOPostgres.getEmployeeDAOPostgres().createEmployee(testingEmployee);

        // delete ticket
        boolean wasDeleted = EmployeeDAOPostgres.getEmployeeDAOPostgres().deleteEmployee(savedEmployee.getId());

        // assert
        Assertions.assertEquals(true, wasDeleted);
    }

}

