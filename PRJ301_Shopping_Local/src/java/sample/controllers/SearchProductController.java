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
import sample.shopping.ProductDAO;
import sample.shopping.ProductDTO;

/**
 *
 * @author Hp
 */
public class SearchProductController extends HttpServlet {

    private static final String SUCCESS = "shopping.jsp";
    private static final String ERROR = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String search = request.getParameter("searchProduct");
            String category = request.getParameter("category");
            if (search == null) {
                search = "";
            }
            if (category == null) {
                category = "All";
            }
            if ("All".equals(category)) {
                ProductDAO dao = new ProductDAO();
                List<ProductDTO> products = dao.searchProduct(search);
                if (products.size() > 0) {
                    request.setAttribute("PRODUCTS", products);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Can't not found!");
                }
            } else {
                ProductDAO dao = new ProductDAO();
                List<ProductDTO> products = dao.searchProductByCategory(search, category);
                if (products.size() > 0) {
                    request.setAttribute("PRODUCTS", products);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", "Can't not found!");
                }
            }

        } catch (Exception e) {
            log("ERROR at ListAllProductController" + e.toString());
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
