/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.ProductDAO;
import enity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "SearchAjax", urlPatterns = {"/searchAjax"})
public class SearchAjax extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        /* TODO output your page here. You may use following sample code. */
        String textSearch = request.getParameter("txtsearch");

        ProductDAO pdao = new ProductDAO();

        List<Product> listP = pdao.getProductBySearch(textSearch);

        for (Product product : listP) {
            out.println("<div class=\"col-md-4\">\n"
                    + "                                <div class=\"card mb-4 product-wap rounded-0\">\n"
                    + "                                    <div class=\"card rounded-0\">\n"
                    + "                                        <img class=\"card-img rounded-0 img-fluid\" src=\""+product.getImg()+"\">\n"
                    + "                                        <div class=\"card-img-overlay rounded-0 product-overlay d-flex align-items-center justify-content-center\">\n"
                    + "                                            <ul class=\"list-unstyled\">\n"
                    + "\n"
                    + "                                                <li><a class=\"btn btn-success text-white mt-2\" href=\"detailProduct?pid="+product.getProductID()+"\">Detail Product</a></li>\n"
                    + "                                            </ul>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <a href=\"shop-single.html\" class=\"h3 text-decoration-none\">"+product.getProductName()+"</a>\n"
                    + "                                        <ul class=\"w-100 list-unstyled d-flex justify-content-between mb-0\">\n"
                    + "\n"
                    + "                                            <li class=\"pt-2\">\n"
                    + "                                                <span class=\"product-color-dot color-dot-red float-left rounded-circle ml-1\"></span>\n"
                    + "                                                <span class=\"product-color-dot color-dot-blue float-left rounded-circle ml-1\"></span>\n"
                    + "                                                <span class=\"product-color-dot color-dot-black float-left rounded-circle ml-1\"></span>\n"
                    + "                                                <span class=\"product-color-dot color-dot-light float-left rounded-circle ml-1\"></span>\n"
                    + "                                                <span class=\"product-color-dot color-dot-green float-left rounded-circle ml-1\"></span>\n"
                    + "                                            </li>\n"
                    + "                                        </ul>\n"
                    + "\n"
                    + "                                        <p class=\"text-center mb-0\"><span style=\"color: red; font-weight: bold\">$</span>\n"
                    + "                                            "+product.getPrice()+"</p>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>");
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
