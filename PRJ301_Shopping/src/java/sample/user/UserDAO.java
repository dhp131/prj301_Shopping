/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUtils;

/**
 *
 * @author Hp
 */
public class UserDAO {

    private static String LOGIN = "SELECT userId, fullName, password, address, email, phone \n"
            + "FROM customers \n"
            + "WHERE fullName=? AND password=? OR email=? AND password=?";
    private static String SEARCH_CUSTOMER = "SELECT userId, fullName, password, address, email, phone\n"
            + "FROM customers\n"
            + "WHERE fullName like ?";

    private static String DELETE_CUSTOMER = "DELETE customers WHERE userID=?";

    private static String UPDATE_CUSTOMER = "UPDATE customers SET fullName=?, address=?, email=?, phone=?, roleID=? WHERE userID=?";
    private static String CHECK_DUPLICATE_EMAIL = "SELECT email FROM customers WHERE email=?";
    private static String CHECK_DUPLICATE_PHONE = "SELECT phone FROM customers WHERE phone=?";

    private static String INSERT_CUSTOMER = "INSERT INTO customers(fullName, password, address, email, phone) VALUES(?,?,?,?,?)";

    private static String GET_USER_BY_ID = "SELECT fullName, password, address, email, phone, roleID FROM customers WHERE userId = ?";
    private static String GET_USER_BY_EMAIL = "SELECT fullName, password, address, phone, roleID FROM customers WHERE email = ?";

    private static String DELETE_CARTITEMS = "DELETE FROM cartItems\n"
            + "WHERE cartId IN (\n"
            + "    SELECT cartId\n"
            + "    FROM carts\n"
            + "    WHERE customerId = ?\n"
            + ");";
    private static String DELETE_ORDER_DETAILS = "DELETE FROM orderDetails\n"
            + "WHERE orderId IN (\n"
            + "    SELECT id\n"
            + "    FROM orders\n"
            + "    WHERE customerId = ?\n"
            + ");";
    private static String DELETE_CART = "DELETE FROM carts WHERE customerId = ?";
    private static String DELETE_ORDER = "DELETE FROM orders WHERE customerId = ?";

    private static String USER_UPDATE_WITHOUT_PASSWORD = "UPDATE customers SET fullName = ?, address= ?, phone= ? WHERE userId = ?";
    private static String USER_UPDATE_WITH_PASSWORD = "UPDATE customers SET fullName = ?, address= ?, phone= ?, password = ? WHERE userId = ?";

    public UserDTO checkLogin(String loginUserID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, loginUserID);
                ptm.setString(2, password);
                ptm.setString(3, loginUserID);
                ptm.setString(4, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userId = rs.getInt("userId");
                    String fullName = rs.getString("fullName");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userId, fullName, roleID, password, address, email, phone);
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
        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
//        "SELECT userId, fullName, password, address, email, phone, roleID \n"
//        "FROM customers \n"
//        "WHERE fullName=? AND password=? OR email=? AND password=?"
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_CUSTOMER);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("userId");
                    String fullName = rs.getString("fullName");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    list.add(new UserDTO(userID, fullName, roleID, password, address, email, phone));
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
        return list;
    }

    public boolean deleteUser(int userID) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                deleteCartItemByUserID(userID);
                deleteOrderDetailsByUserID(userID);
                deleteCartsByUserID(userID);
                deleteOrderByUserID(userID);

                ptm = conn.prepareStatement(DELETE_CUSTOMER);
                ptm.setInt(1, userID);
                checkDelete = ptm.executeUpdate() > 0 ? true : false;
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
        return checkDelete;
    }

    private boolean deleteCartItemByUserID(int userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(DELETE_CARTITEMS);
                ptm.setInt(1, userID);
                int rowsEffect = ptm.executeUpdate();
                if (rowsEffect > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
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

    private boolean deleteCartsByUserID(int userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(DELETE_CART);
                ptm.setInt(1, userID);
                int rowsEffect = ptm.executeUpdate();
                if (rowsEffect > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
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

    private boolean deleteOrderDetailsByUserID(int userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(DELETE_ORDER_DETAILS);
                ptm.setInt(1, userID);
                int rowsEffect = ptm.executeUpdate();
                if (rowsEffect > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
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

    private boolean deleteOrderByUserID(int userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(DELETE_ORDER);
                ptm.setInt(1, userID);
                int rowsEffect = ptm.executeUpdate();
                if (rowsEffect > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
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

    public boolean updateUser(UserDTO user) throws SQLException {
//        UPDATE customers SET fullName=?, address=?, email=?, phone=?, roleID=? WHERE userID=?
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CUSTOMER);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getAddress());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getPhone());
                ptm.setString(5, user.getRoleID());
                ptm.setInt(6, user.getUserID());
                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
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
        return checkUpdate;
    }

    public boolean checkDuplicate(String column, String query) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setString(1, column);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

    public boolean checkDuplicateEmail(String email) throws SQLException {
        return checkDuplicate(email, CHECK_DUPLICATE_EMAIL);
    }

    public boolean checkDuplicatePhone(String phone) throws SQLException {
        return checkDuplicate(phone, CHECK_DUPLICATE_PHONE);
    }

    public boolean insertUser(UserDTO user) throws SQLException {
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_CUSTOMER);
                ptm.setInt(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
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
        return checkUpdate;
    }

    public boolean insertUserV2(UserDTO user) throws ClassNotFoundException, SQLException, NamingException {
//        INSERT INTO customers(fullName, password, address, email, phone) VALUES(?,?,?,?,?)
        boolean checkUpdate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_CUSTOMER);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getAddress());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getPhone());
                checkUpdate = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return checkUpdate;
    }

    public UserDTO getUserByID(int userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(GET_USER_BY_ID);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(userID, fullName, roleID, password, address, email, phone);
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
        return user;
    }

    public boolean checkUserByEmail(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(GET_USER_BY_EMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs != null) {
                    check = true;
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
        return check;
    }

    public UserDTO getUserByEmail(String email) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(GET_USER_BY_EMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String password = rs.getString("password");
                    String address = rs.getString("address");
                    String phone = rs.getString("phone");
                    String roleID = rs.getString("roleID");
                    user = new UserDTO(fullName, password, address, email, phone, roleID);
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
        return user;
    }

    public boolean userUpdateInforWithOutPassword(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(USER_UPDATE_WITHOUT_PASSWORD);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getAddress());
                ptm.setString(3, user.getPhone());
                ptm.setInt(4, user.getUserID());

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
    
    public boolean userUpdateInforWithPassword(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareCall(USER_UPDATE_WITH_PASSWORD);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getAddress());
                ptm.setString(3, user.getPhone());
                ptm.setString(4, user.getPassword());
                ptm.setInt(5, user.getUserID());

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
}
