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

/**
 *
 * @author ngohu
 */
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE="login.jsp";
    
    private static final String LOGIN="Login";
    private static final String LOGIN_CONTROLLER="LoginController";
    
    private static final String SEARCH_USER="SearchUser";
    private static final String SEARCH_USER_CONTROLLER="SearchUserController";
    
    private static final String SEARCH_PRODUCT="Search Product";
    private static final String SEARCH_PRODUCT_CONTROLLER="SearchProductController";
    
    private static final String AD_SEARCH_PRODUCT="ADSearchProduct";
    private static final String AD_SEARCH_PRODUCT_CONTROLLER="ADSearchProductController";
    
    private static final String DELETE="Delete";
    private static final String DELETE_CONTROLLER="DeleteController";
    
    private static final String UPDATE="Update";
    private static final String UPDATE_CONTROLLER="UpdateController";
    
    private static final String CREATE_PAGE="CreatePage";
    private static final String CREATE_PAGE_VIEW="create.jsp";
    
    private static final String CREATE="Create";
    private static final String CREATE_CONTROLLER="CreateController";
    
    private static final String SHOPPING_PAGE="ShoppingPage";
    private static final String SHOPPING_PAGE_VIEW="ListAllProductController";
    
    private static final String ADD="Add";
    private static final String ADD_CONTROLLER="AddController";
    
    private static final String VIEW="View";
    private static final String VIEW_PAGE="viewCart.jsp";
    
    private static final String REMOVE="Remove";
    private static final String REMOVE_CONTROLLER="RemoveController";
    
    private static final String EDIT="Edit";
    private static final String EDIT_CONTROLLER="EditController";
    
    private static final String LOGOUT="Logout";
    private static final String LOGOUT_CONTROLLER="LogoutController";
    
    private static final String CHECK_OUT="Confirm";
    private static final String CHECK_OUT_CONTROLLER="CheckOutController";
    
    private static final String VIEW_CHECK_OUT="ViewCheckOut";
    private static final String VIEW_CHECK_OUT_PAGE="checkOut.jsp";
    
    private static final String VIEW_INFORMATION="ViewInformation";
    private static final String VIEW_INFORMATION_CONTROLLER="ViewInformationController";
    
    private static final String USER_UPDATE="Save";
    private static final String USER_UPDATE_CONTROLLER="UserUpdateController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= LOGIN_PAGE;
        try {
            String action= request.getParameter("action");
            if(action== null){
                url=LOGIN_PAGE;
            } else if (LOGIN.equals(action)){
                url= LOGIN_CONTROLLER;
            } else if (SEARCH_USER.equals(action)){
                url= SEARCH_USER_CONTROLLER;
            } else if (DELETE.equals(action)){
                url= DELETE_CONTROLLER;
            } else if (UPDATE.equals(action)){
                url= UPDATE_CONTROLLER;
            } else if (CREATE_PAGE.equals(action)){
                url= CREATE_PAGE_VIEW;
            } else if (CREATE.equals(action)){
                url= CREATE_CONTROLLER;
            } else if (SHOPPING_PAGE.equals(action)){
                url= SHOPPING_PAGE_VIEW;
            } else if (ADD.equals(action)){
                url= ADD_CONTROLLER;
            } else if (VIEW.equals(action)){
                url= VIEW_PAGE;
            } else if (REMOVE.equals(action)){
                url= REMOVE_CONTROLLER;
            } else if (EDIT.equals(action)){
                url= EDIT_CONTROLLER;
            } else if (LOGOUT.equals(action)){
                url= LOGOUT_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(action)){
                url= SEARCH_PRODUCT_CONTROLLER;
            } else if (AD_SEARCH_PRODUCT.equals(action)){
                url= AD_SEARCH_PRODUCT_CONTROLLER;
            } else if (CHECK_OUT.equals(action)){
                url= CHECK_OUT_CONTROLLER;
            } else if (VIEW_CHECK_OUT.equals(action)){
                url= VIEW_CHECK_OUT_PAGE;
            } else if (VIEW_INFORMATION.equals(action)){
                url= VIEW_INFORMATION_CONTROLLER;
            } else if (USER_UPDATE.equals(action)){
                url= USER_UPDATE_CONTROLLER;
            } 
        } catch (Exception e) {
            log("Error at MainController: "+ e.toString());
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
