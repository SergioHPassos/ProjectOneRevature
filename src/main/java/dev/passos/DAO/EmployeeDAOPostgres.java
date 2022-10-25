package dev.passos.DAO;

import dev.passos.entity.Employee;
import dev.passos.entity.Ticket;
import dev.passos.interfaces.EmployeeCRUD;
import dev.passos.utility.DBConn;

import java.sql.*;

public class EmployeeDAOPostgres implements EmployeeCRUD {
    private static EmployeeDAOPostgres employeeDAOPostgres = null;

    public static EmployeeDAOPostgres getEmployeeDAOPostgres(){
        if(employeeDAOPostgres == null){
            employeeDAOPostgres = new EmployeeDAOPostgres();
        }
        return employeeDAOPostgres;
    }
    /**
     * @param employee
     * @return
     */
    @Override
    public Employee createEmployee(Employee employee) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "INSERT INTO \"CompanyData\".employees VALUES(default, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPassword());
            preparedStatement.setBoolean(4, employee.isManager());
            preparedStatement.setString(5, employee.getEmail());

            // execute query command on DB
            preparedStatement.execute();

            // return newly created employee record
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            // move head
            resultSet.next();

            // get auto generated id, and assign to employee
            int generatedKey = resultSet.getInt("id");
            employee.setId(generatedKey);

            //
            return employee;
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
    public Employee getEmployee(int id) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "SELECT * FROM \"CompanyData\".employees WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            // execute query command on DB
            ResultSet resultSet = preparedStatement.executeQuery();

            // move head
            resultSet.next();

            // create ticket object with data
            Employee lookupEmployee = new Employee(
                    resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("isManager"),
                    resultSet.getString("email")
            );

            //
            return lookupEmployee;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * @param employee
     * @return
     */
    @Override
    public Employee updateEmployee(Employee employee) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "UPDATE \"CompanyData\".employees SET \"firstName\"=?, \"lastName\"=?, password=?, \"isManager\"=?, email=? WHERE id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPassword());
            preparedStatement.setBoolean(4, employee.isManager());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setInt(6, employee.getId());

            // execute query command on DB
            preparedStatement.executeUpdate();

            //
            return employee;
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
    public boolean deleteEmployee(int id) {
        try(Connection connection = DBConn.getConnection()){
            // sql query
            String sql = "DELETE FROM \"CompanyData\".employees WHERE id=?";

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
}
