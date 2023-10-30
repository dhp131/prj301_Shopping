/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.util.HashMap;
import java.util.List;
import sample.shopping.Order;

/**
 *
 * @author Hp
 */
public class UserDTO {
    private int userID;
    private String fullName;
    private String password;
    private String address;
    private String email;
    private String phone;
    private String roleID;
    private List<Order> oders;
    
    public UserDTO() {
    }

    public UserDTO(int userID, String fullName, String roleID, String password, String address, String email, String phone) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public UserDTO(String fullName, String password, String address, String email, String phone, String roleID) {
        this.fullName = fullName;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.roleID = roleID;
    }
    
    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Order> getOders() {
        return oders;
    }

    public void setOders(List<Order> oders) {
        this.oders = oders;
    }

    
    
    
}
