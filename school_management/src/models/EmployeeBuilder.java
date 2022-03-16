/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author hocin
 */
public class EmployeeBuilder {

    int id;
    String image;
    String name;
    Date birth;
    String address, phone, type, email, password;

    public EmployeeBuilder id(int id) {
        this.id = id;
        return this;
    }

    public EmployeeBuilder image(String image) {
        this.image = image;
        return this;
    }

    public EmployeeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public EmployeeBuilder birth(Date birth) {
        this.birth = birth;
        return this;
    }

    public EmployeeBuilder address(String address) {
        this.address = address;
        return this;
    }

    public EmployeeBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public EmployeeBuilder type(String type) {
        this.type = type;
        return this;
    }

    public EmployeeBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder password(String password) {
        this.password = password;
        return this;
    }
    
    public Employee build(){
        return new Employee(id, image, name, birth, address, phone, type, email, password);
    }

}
