/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author ngohu
 */
public class ProductDAO {

    private static final String LIST_ALL_PRODUCT = "SELECT id, name, price, description, image, quantity, createDate\n"
            + "FROM products\n"
            + "WHERE name like ?";
    private static final String GET_PRODUCT_BY_ID = "SELECT id, name, price, description, image, quantity, createDate\n"
            + "FROM products\n"
            + "WHERE id = ?";

    private static final String GET_ALL_CATEGORY_PRODUCT = "SELECT p.id, p.name, p.price, p.description, p.image, p.quantity, p.createDate\n"
            + "FROM products p\n"
            + "JOIN category c ON p.categoryId = c.id\n"
            + "WHERE c.name = ? AND p.name LIKE ?";

    public List<ProductDTO> searchProduct(String search) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(LIST_ALL_PRODUCT);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    String createDate = rs.getString("createDate");

                    products.add(new ProductDTO(id, name, price, description, image, quantity, createDate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return products;
    }

    public ProductDTO getProduct(int productID) throws SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(GET_PRODUCT_BY_ID);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    product = new ProductDTO();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDescription(rs.getString("description"));
                    product.setImage(rs.getString("image"));
                    product.setQuantity(rs.getInt("quantity"));
                    product.setCreateDate(rs.getString("createDate"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return product;
    }

    public List<ProductDTO> searchProductByCategory(String search, String category) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(GET_ALL_CATEGORY_PRODUCT);
                ptm.setString(1, category);
                ptm.setString(2, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    String createDate = rs.getString("createDate");

                    products.add(new ProductDTO(id, name, price, description, image, quantity, createDate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return products;
    }
}
