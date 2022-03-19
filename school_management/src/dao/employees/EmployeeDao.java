/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.employees;

import java.util.List;
import javafx.collections.ObservableList;
import models.Employee;

/**
 *
 * @author hocin
 */
public interface EmployeeDao {
    
    
    ObservableList<Employee> findAll();
    
    Employee findById(int id);
    
    void save(Employee employee);
    
    void deleteById(int id);
    
}
