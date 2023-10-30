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
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.utils.DBUtils;

/**
 *
 * @author Hp
 */
public class CartDAO {

    private static final String GET_CART_BY_USER_ID = "SELECT c.productId, c.quantity AS 'cartQuantity', p.name, p.price, p.description, p.image, p.quantity, p.createDate "
            + "FROM cartItems c "
            + "INNER JOIN products p ON c.productId = p.id "
            + "INNER JOIN carts ct ON c.cartId = ct.cartId "
            + "WHERE ct.customerId = ?";

    private static final String ADD_CART_ITEM = "INSERT INTO cartItems (cartId, productId, quantity) "
            + "VALUES (?, ?, ?)";

    private static final String ADD_CART = "INSERT INTO carts (customerId) VALUES (?)";

    private static final String GET_CART_ID_BY_USER_ID = "SELECT cartId FROM carts WHERE customerId = ?";

    private static final String UPDATE_CART_QUANTITY = "UPDATE cartItems SET quantity = ? "
            + "WHERE cartId IN (SELECT cartId FROM carts WHERE customerId = ?) AND productId = ?";

    private static final String DELETE_PRODUCT_IN_CART = "DELETE cartItems "
            + "WHERE cartId IN (SELECT cartId FROM carts WHERE customerId = ?) AND productId = ?";

    private static final String CART_TO_ORDER = "INSERT INTO orders (id, customerId, shippingAddress, orderDate, totalPrice, phone) VALUES(?, ?, ?, ?, ?, ?)";

    private static final String CART_TO_ORDER_DETAIL = "INSERT INTO orderDetails (orderId, productId, price, quantity) VALUES(?, ?, ?, ?)";

    public Cart getCartByUserID(int userID) throws SQLException {
        Cart cart = new Cart();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(GET_CART_BY_USER_ID);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("productId");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    int quantity = rs.getInt("quantity");
                    String createDate = rs.getString("createDate");
                    int cartQuantity = rs.getInt("cartQuantity");

                    ProductDTO product = new ProductDTO(productId, name, price, description, image, quantity, createDate);
                    ProductInCart productIncart = new ProductInCart(product, cartQuantity);

                    cart.add(productIncart);
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
        return cart;
    }

    public boolean pushCartIntoDB(ProductInCart productInCart, int userID) throws SQLException, ClassNotFoundException {
        boolean check = false;
        ProductDTO product = productInCart.getProduct();
        Connection conn = null;
        PreparedStatement cartPtm = null;
        PreparedStatement cartItemPtm = null;

        try {
            conn = DBUtils.getConnection();

            // Check if the customer already has a cart
            int cartId = getCartIdByUserId(conn, userID);
            if (cartId == 0) {
                // If the customer doesn't have a cart, insert a new cart
                cartPtm = conn.prepareStatement(ADD_CART, Statement.RETURN_GENERATED_KEYS);
                cartPtm.setInt(1, userID);
                int rowsAffected = cartPtm.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = cartPtm.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        cartId = generatedKeys.getInt(1);
                    }
                }
            }

            // Insert the product into the cartItems table
            cartItemPtm = conn.prepareStatement(ADD_CART_ITEM);
            cartItemPtm.setInt(1, cartId);
            cartItemPtm.setInt(2, product.getId());
            cartItemPtm.setInt(3, productInCart.getQuantity());

            int rowsAffected = cartItemPtm.executeUpdate();
            if (rowsAffected > 0) {
                check = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cartItemPtm != null) {
                cartItemPtm.close();
            }
            if (cartPtm != null) {
                cartPtm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    private int getCartIdByUserId(Connection conn, int userID) throws SQLException {
        int cartId = 0;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            ptm = conn.prepareStatement(GET_CART_ID_BY_USER_ID);
            ptm.setInt(1, userID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                cartId = rs.getInt("cartId");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }
        return cartId;
    }

    public boolean updateCartQuantity(int userID, ProductInCart productInCart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(UPDATE_CART_QUANTITY);
                ptm.setInt(1, productInCart.getQuantity());
                ptm.setInt(2, userID);
                ptm.setInt(3, productInCart.getProduct().getId());

                int rowsEffect = ptm.executeUpdate();
                if (rowsEffect > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean deleteProductIncart(int userID, ProductInCart productInCart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(DELETE_PRODUCT_IN_CART);
                ptm.setInt(1, userID);
                ptm.setInt(2, productInCart.getProduct().getId());

                int rowsEffect = ptm.executeUpdate();
                if (rowsEffect > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean transferToOrder(int userID) throws SQLException {
        boolean check = false;

        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.getUserByID(userID);

        CartDAO cartDAO = new CartDAO();
        Cart cart = cartDAO.getCartByUserID(userID);

        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();

        java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String orderId = generateOrderId();
                ptm = conn.prepareCall(CART_TO_ORDER);
                ptm.setString(1, orderId);
                ptm.setInt(2, userID);
                ptm.setString(3, user.getAddress());
                ptm.setDate(4, sqlDate);
                ptm.setDouble(5, cart.getTotalBill());
                ptm.setString(6, user.getPhone());

                int rowsEffect = ptm.executeUpdate();
                if (rowsEffect > 0) {
                    for(ProductInCart pic: cart.getCart().values()){
                        transferToOrderDetail(orderId, pic);
                        deleteProductIncart(userID, pic);
                    }
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean transferToOrderDetail(String oderID, ProductInCart productInCart) throws SQLException {
//        (orderId, productId, price, quantity)
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareCall(CART_TO_ORDER_DETAIL);
            ptm.setString(1, oderID);
            ptm.setInt(2, productInCart.getProduct().getId());
            ptm.setDouble(3, productInCart.getProduct().getPrice());
            ptm.setInt(4, productInCart.getQuantity());

            int rowsEffect = ptm.executeUpdate();
            if (rowsEffect > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    private String generateOrderId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
