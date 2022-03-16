/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javafx.scene.image.Image;

/**
 *
 * @author hocin
 */
public class Employee {

    int id;
    String image;
    String name;
    Date birth;
    String address, phone, type, email, password;

    public Employee() {
    }

    public Employee(int id, String image, String name, Date birth, String address, String phone, String type, String email, String password) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.birth = birth;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Override
    public String toString() {
        return "Employee{" + "id=" + id
                + ", image="
                + image + ", name="
                + name + ", birth="
                + birth + ", address="
                + address + ", phone="
                + phone + ", type="
                + type + ", email="
                + email + '}';
    }

}
