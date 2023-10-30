/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Hp
 */
public class Order {

    private String id;
    private int customerId;
    private String shippingAddress;
    private Date orderDate;
    private double totalPrice;
    private String phone;
    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(String id, int customerId, String shippingAddress, Date orderDate, double totalPrice, String phone, List<OrderDetail> orderDetails) {
        this.id = id;
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.phone = phone;
        this.orderDetails = orderDetails;
    }

    public Order(String id, int customerId, String shippingAddress, Date orderDate, double totalPrice, String phone) {
        this.id = id;
        this.customerId = customerId;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.phone = phone;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    
}
