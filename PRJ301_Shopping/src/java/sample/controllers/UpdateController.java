/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.user.UserDAO;
import sample.user.UserDTO;

/**
 *
 * @author Hp
 */
public class UpdateController extends HttpServlet {

    private static final String ERROR = "SearchController";
    private static final String SUCCESS = "SearchController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            
//            User dang update
            int userID = Integer.parseInt(request.getParameter("userID"));
            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String roleID = request.getParameter("roleID");
            
//            User dang Login
            HttpSession session= request.getSession();
            UserDTO loginUser= (UserDTO) session.getAttribute("LOGIN_USER");

//            Update len Database
            UserDAO dao = new UserDAO();
            UserDTO user= new UserDTO(userID, fullName, roleID, "", address, email, phone);
            boolean checkUpdate = dao.updateUser(user);
            if (checkUpdate) {
                url = SUCCESS;
            }
            
//            Update user dang login va day len Session
            if(userID==loginUser.getUserID()){
                loginUser.setFullName(user.getFullName());
                loginUser.setAddress(user.getAddress());
                loginUser.setEmail(user.getEmail());
                loginUser.setPhone(user.getPhone());
                loginUser.setRoleID(user.getRoleID());
                session.setAttribute("LOGIN_USER", loginUser);
            }
        } catch (Exception e) {
            log("ERROR at DeleteController" + e.toString());
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
