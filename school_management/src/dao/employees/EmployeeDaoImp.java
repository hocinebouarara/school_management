/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.employees;

import dao.DbConnect;
import helpres.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Employee;

/**
 *
 * @author hocin
 */
public class EmployeeDaoImp implements EmployeeDao {
    
    @Override
    public List<Employee> findAll() {
        Connection connection = DbConnect.getConnect();
        if (connection == null) {
            return null;
        }
        List<Employee> employees = new LinkedList<>();
        String query = "select * from employees;";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("image"), resultSet.getString("fullname"), resultSet.getDate("birth"),
                        resultSet.getString("address"), resultSet.getString("phone"), resultSet.getString("type"), resultSet.getString("email"), resultSet.getString("password")
                );
                employees.add(employee);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return employees;
    }
    
    @Override
    public Employee findById(int id) {
        Connection connection = DbConnect.getConnect();
        String query = "SELECT * FROM employees;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee(resultSet.getInt("id"), resultSet.getString("image"), resultSet.getString("fullname"), resultSet.getDate("birth"),
                        resultSet.getString("address"), resultSet.getString("phone"), resultSet.getString("type"), resultSet.getString("email"), resultSet.getString("password")
                );
                return employee;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return null;
        
    }
    
    @Override
    public void save(Employee employee
    ) {
        Connection connection = DbConnect.getConnect();
        if (connection == null) {
            return;
        } else {
            if (employee.getId() > 0) {
                // update 
                String query = "update employees set image = ?, fullname = ?, birth = ?, address = ?, phone = ?, type = ?, email = ?, password = ? where id = ?;";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                    
                    preparedStatement.setString(1, employee.getImage());
                    preparedStatement.setString(2, employee.getName());
                    preparedStatement.setDate(3, Utils.getSqlDate(new java.util.Date()));
                    preparedStatement.setString(4, employee.getAddress());
                    preparedStatement.setString(5, employee.getPhone());
                    preparedStatement.setString(6, employee.getType());
                    preparedStatement.setString(7, employee.getEmail());
                    preparedStatement.setString(8, employee.getPassword());
                    preparedStatement.setInt(9, employee.getId());
                    
                    preparedStatement.executeUpdate();
                    System.out.println("insert successfully");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                String query = "INSERT INTO `employees`(`image`, `fullName`, `birth`, `address`, `phone`, `type`, `email`, `password`) VALUES (?,?,?,?,?,?,?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
                    
                    preparedStatement.setString(1, employee.getImage());
                    preparedStatement.setString(2, employee.getName());
                    preparedStatement.setDate(3, Utils.getSqlDate(new java.util.Date()));
                    preparedStatement.setString(4, employee.getAddress());
                    preparedStatement.setString(5, employee.getPhone());
                    preparedStatement.setString(6, employee.getType());
                    preparedStatement.setString(7, employee.getEmail());
                    preparedStatement.setString(8, employee.getPassword());
                    
                    preparedStatement.executeUpdate();
                    System.out.println("insert successfully");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        }
    }
    
    @Override
    public void deleteById(int id) {
        Connection connection = DbConnect.getConnect();
        String query = "DELETE FROM employees WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
}
