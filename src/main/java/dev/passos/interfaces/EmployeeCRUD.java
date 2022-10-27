package dev.passos.interfaces;

import dev.passos.entity.Employee;

public interface EmployeeCRUD {
    Employee createEmployee(Employee employee);
    Employee getEmployee(int id);
    Employee updateEmployee(Employee employee);
    boolean deleteEmployee(int id);
    Employee getEmployeeByEmail(String email);
}
