/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Hp
 */
public class OrderDAO {

    private static final String GET_ORDER_BY_CUSTOMER_ID = "SELECT id, shippingAddress, orderDate, totalPrice, phone "
            + "FROM orders WHERE customerId = ?";
    
    private static final String GET_ORDER_DETAIL_BY_ORDER_ID= "SELECT id, orderId, productId, price, quantity FROM orderDetails WHERE orderId = ?";
    
    public List<Order> getOrderByUserID(int userID) throws ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pmt = conn.prepareStatement(GET_ORDER_BY_CUSTOMER_ID);
            pmt.setInt(1, userID);
            rs = pmt.executeQuery();

            while (rs.next()) {
                String orderId = rs.getString("id");
                String shippingAddress = rs.getString("shippingAddress");
                Date orderDate = rs.getDate("orderDate");
                double totalPrice = rs.getDouble("totalPrice");
                String phone = rs.getString("phone");

                Order order= new Order(orderId, userID, shippingAddress, orderDate, totalPrice, phone);
                order.setOrderDetails(getOrderDetailByOrderID(orderId));
                
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pmt != null) {
                    pmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orders;
    }

    public List<OrderDetail> getOrderDetailByOrderID(String orderID) throws ClassNotFoundException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pmt = conn.prepareStatement(GET_ORDER_DETAIL_BY_ORDER_ID);
            pmt.setString(1, orderID);
            rs = pmt.executeQuery();

            while (rs.next()) {
                int orderDetailId = rs.getInt("id");
                String orderId = rs.getString("orderId");
                int productId = rs.getInt("productId");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                
                ProductDAO pDAO= new ProductDAO();
                ProductDTO product= pDAO.getProduct(productId);

                OrderDetail orderDetail = new OrderDetail(orderDetailId, orderId, product, price, quantity);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pmt != null) {
                    pmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orderDetails;
    }

}
