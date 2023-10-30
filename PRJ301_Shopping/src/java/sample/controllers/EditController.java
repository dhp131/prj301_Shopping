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
import sample.shopping.Cart;
import sample.shopping.CartDAO;
import sample.shopping.ProductDTO;
import sample.shopping.ProductInCart;

/**
 *
 * @author Hp
 */
public class EditController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int userID = Integer.parseInt(request.getParameter("userID"));

            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                ProductInCart productInCart = cart.getCart().get(productID);
                ProductDTO product = cart.getCart().get(productID).getProduct();
                if (product != null) {
                    productInCart.setQuantity(quantity);
                }
                boolean check = cart.edit(productID, productInCart);
                if (check) {
                    session.setAttribute("CART", cart);
                    CartDAO cartDAO = new CartDAO();
                    if (cartDAO.updateCartQuantity(userID, productInCart)) {
                        request.setAttribute("MESSAGE", "Something went wrong please try again!");
                        url = ERROR;
                    } else {
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at EditController " + e.toString());
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
