package dev.passos.service;

import dev.passos.DAO.EmployeeDAOPostgres;
import dev.passos.DAO.TicketDAOPostgres;
import dev.passos.entity.Employee;
import dev.passos.entity.Ticket;
import dev.passos.interfaces.EmployeeCRUD;
import org.jetbrains.annotations.NotNull;

public class EmployeeService implements EmployeeCRUD {

    private static EmployeeService employeeService = null;

    public static EmployeeService getEmployeeService(){
        if (employeeService == null) {
            employeeService = new EmployeeService();
        }
        return employeeService;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee createEmployee(Employee employee) {
        // verifications checks
        if(employee.getFirstName().length() == 0 || employee.getLastName().length() == 0){
            throw new RuntimeException("Employee should have a full name!");
        } else if(employee.getPassword().length() == 0){
            throw new RuntimeException("Employee must have a password!");
        } else if(employee.getEmail().length() == 0){
            throw new RuntimeException("Employee must have a email address!");
        }

        // create employee in database
        Employee registeredEmployee = EmployeeDAOPostgres.getEmployeeDAOPostgres().createEmployee(employee);

        return registeredEmployee;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Employee getEmployee(int id) {
        // verifications checks
        if(id < 0){
            throw new RuntimeException("Employee id should be greater than 0");
        }

        // look up ticket by id
        Employee lookupEmployee = EmployeeDAOPostgres.getEmployeeDAOPostgres().getEmployee(id);

        return lookupEmployee;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee updateEmployee(Employee employee) {
        // verifications checks
        if(employee.getFirstName().length() == 0 || employee.getLastName().length() == 0){
            throw new RuntimeException("Employee should have a full name!");
        } else if(employee.getPassword().length() == 0){
            throw new RuntimeException("Employee must have a password!");
        } else if(employee.getEmail().length() == 0){
            throw new RuntimeException("Employee must have a email address!");
        }

        // look up ticket by id
        Employee updatedEmployee = EmployeeDAOPostgres.getEmployeeDAOPostgres().updateEmployee(employee);

        return updatedEmployee;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteEmployee(int id) {
        // verifications checks
        if(id < 0){
            throw new RuntimeException("Employee id should be greater than 0");
        }

        // look up employee by id
        boolean isDeleted = EmployeeDAOPostgres.getEmployeeDAOPostgres().deleteEmployee(id);

        return isDeleted;
    }
}
