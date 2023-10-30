/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.shopping.Order;
import sample.shopping.OrderDAO;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Hp
 */
public class UserUpdateController extends HttpServlet {

    private static final String SUCCESS = "usPage.jsp";
    private static final String ERROR = "usPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int userID = Integer.parseInt(request.getParameter("userID"));
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");

            UserDTO user = null;
            boolean check = false;

            if (confirm.equals(password)) {
                user = new UserDTO(userID, fullName, "", password, address, "", phone);
                UserDAO uDAO = new UserDAO();
                check = uDAO.userUpdateInforWithPassword(user);
            } else {
                user = new UserDTO(userID, fullName, "", "", address, "", phone);
                UserDAO uDAO = new UserDAO();
                check = uDAO.userUpdateInforWithOutPassword(user);
            }

            UserDAO uDAO = new UserDAO();
            if (check) {
                uDAO = new UserDAO();
                UserDTO user2 = uDAO.getUserByID(userID);
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", user2);
                request.setAttribute("MESSAGE", "Successfully!");

                OrderDAO oDAO = new OrderDAO();
                List<Order> orders = oDAO.getOrderByUserID(userID);
                if (orders.size() > 0) {
                    request.setAttribute("ORDER", orders);
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("EROR_MESSAGE", "Fail!");
                url = ERROR;
            }
        } catch (Exception e) {
            log("ERROR at UserUpdateController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
