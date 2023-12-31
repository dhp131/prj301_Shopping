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
import sample.user.UserDAO;
import sample.user.UserDTO;
import sample.user.UserError;

/**
 *
 * @author Hp
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        UserError userError= new UserError();
        try {
            String fullName= request.getParameter("fullName");
            String address= request.getParameter("address");
            String email= request.getParameter("email");
            String phone= request.getParameter("phone");
            String roleID= "US";
            String password= request.getParameter("password");
            String confirm= request.getParameter("confirm");
            boolean checkValidation= true;
            UserDAO dao= new UserDAO();
            
//            if(userID.length()<2 || userID.length()>10){
//                userError.setUserIDError("User ID must be in [2,10]");
//                checkValidation =false;
//            }
//            boolean checkDuplicate= dao.checkDuplicate(userID);
//            if(checkDuplicate){
//                userError.setUserIDError("Duplicate UserID");
//                checkValidation= false;
//            }
            if(fullName.length()<5 || fullName.length()>20){
                userError.setFullNameError("Full name must be in [5,20]");
                checkValidation =false;
            }
            if(!password.equals(confirm)){
                userError.setConfirmError("The confirm password does not match!");
                checkValidation =false;
            }
            if(dao.checkDuplicateEmail(email)){
                userError.setEmailError("Email already in used! Try new one");
                checkValidation =false;
            }
            if(dao.checkDuplicatePhone(phone)){
                userError.setPhoneError("Phone already in used! Try new one");
                checkValidation =false;
            }
            
            if(checkValidation){
                UserDTO user= new UserDTO(fullName, password, address, email, phone, roleID);
//                boolean checkInsert= dao.insertUser(user);
                boolean checkInsert= dao.insertUserV2(user);
                if(checkInsert){
                    url=SUCCESS;
                    request.setAttribute("MESSAGE", "Create succesfully, please Login again!");
                } else {
                    request.setAttribute("ERROR", "Unknow error!");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
            
            
        } catch (Exception e) {
            log("ERROR at CreateController: "+e.toString());
            if(e.toString().contains("duplicate")){
                userError.setUserIDError("Duplicate ID, Try again!");
                request.setAttribute("USER_ERROR", userError);
            }
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
