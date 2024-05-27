/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import DAO.PayDAO;
import DAO.ProductDAO;
import enity.Cart;
import enity.Category;
import enity.PayMethod;
import enity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/shoppingCartController"})
public class ShoppingCartController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShoppingCartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //processRequest(request, response);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        ProductDAO pdao = new ProductDAO();
        if (cart == null) {
            cart = new Cart();
        }

        String action = request.getParameter("action");

        PayDAO payDAO = new PayDAO();

        List<PayMethod> payMethods = payDAO.getAllPayMethod();

        request.setAttribute("payMethods", payMethods);

        switch (action) {
            case "put" -> {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Product product = pdao.getElementProductByPid(pid);
                cart.add(product);
                session.setAttribute("cart", cart);
                response.sendRedirect("detailProduct?pid=" + pid);
            }

            case "get" -> {
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }
            case "post" -> {
                String fuction = request.getParameter("function");
                int id = Integer.parseInt(request.getParameter("id"));

                if (fuction.equals("increase")) {
                    cart.updateQuantity(id);
                } else {
                    cart.deleteQuantity(id);
                }
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }

            case "delete" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                cart.deleteProduct(id);
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }

            case "updateQuanity" -> {

                String arrQuanity[] = request.getParameterValues("quanity");

                String arrProductID[] = request.getParameterValues("productID");

                List<Integer> arrQuanityInt = new ArrayList<>();

                for (String i : arrQuanity) {

                    if (i == null || i.equals("")) {
                        arrQuanityInt.add(-1);
                    } else {
                        arrQuanityInt.add(Integer.valueOf(i));
                    }

                }

                List<Integer> arrProductIDint = new ArrayList<>();

                for (String i : arrProductID) {
                    arrProductIDint.add(Integer.valueOf(i));
                }

                for (int i = 0; i < arrProductIDint.size(); i++) {
                    int number = arrQuanityInt.get(i);
                    int idProduct = arrProductIDint.get(i);

                    if (number == -1) {
                        int quanity = cart.getQuanityByProductID(idProduct);
                        number = quanity;
                    }

                    if (number <= 0) {
                        request.setAttribute("errQuanity", "You must enter more than 0");
                    } else {
                        cart.setQuanity(number, idProduct);
                    }

                }
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
//                System.out.println(request.getParameter("productID"));
//                
//                String numberText = request.getParameter("number");
//                
//                System.out.println(numberText);
//                int number = Integer.parseInt(request.getParameter("number"));
//                int idProduct = Integer.parseInt(request.getParameter("productID"));
            }

            default -> {
                int pid = Integer.parseInt(request.getParameter("id"));
                Product product = pdao.getElementProductByPid(pid);
                cart.add(product);
                session.setAttribute("cart", cart);
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }
        }

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
        //processRequest(request, response);

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        ProductDAO pdao = new ProductDAO();
        if (cart == null) {
            cart = new Cart();
        }

        String action = request.getParameter("action");

        PayDAO payDAO = new PayDAO();

        List<PayMethod> payMethods = payDAO.getAllPayMethod();

        request.setAttribute("payMethods", payMethods);

        switch (action) {
            case "put" -> {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Product product = pdao.getElementProductByPid(pid);
                cart.add(product);
                session.setAttribute("cart", cart);
                response.sendRedirect("detailProduct?pid=" + pid);
            }

            case "get" -> {
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }
            case "post" -> {
                String fuction = request.getParameter("function");
                int id = Integer.parseInt(request.getParameter("id"));

                if (fuction.equals("increase")) {
                    cart.updateQuantity(id);
                } else {
                    cart.deleteQuantity(id);
                }
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }

            case "delete" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                cart.deleteProduct(id);
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }

            case "updateQuanity" -> {

                String arrQuanity[] = request.getParameterValues("quanity");

                String arrProductID[] = request.getParameterValues("productID");

                List<Integer> arrQuanityInt = new ArrayList<>();

                for (String i : arrQuanity) {

                    if (i == null || i.equals("")) {
                        arrQuanityInt.add(-1);
                    } else {
                        arrQuanityInt.add(Integer.valueOf(i));
                    }

                }

                List<Integer> arrProductIDint = new ArrayList<>();

                for (String i : arrProductID) {
                    arrProductIDint.add(Integer.valueOf(i));
                }

                for (int i = 0; i < arrProductIDint.size(); i++) {
                    int number = arrQuanityInt.get(i);
                    int idProduct = arrProductIDint.get(i);

                    if (number == -1) {
                        int quanity = cart.getQuanityByProductID(idProduct);
                        number = quanity;
                    }

                    if (number <= 0) {
                        request.setAttribute("errQuanity", "You must enter more than 0");
                    } else {
                        cart.setQuanity(number, idProduct);
                    }

                }
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
//                System.out.println(request.getParameter("productID"));
//                
//                String numberText = request.getParameter("number");
//                
//                System.out.println(numberText);
//                int number = Integer.parseInt(request.getParameter("number"));
//                int idProduct = Integer.parseInt(request.getParameter("productID"));
            }

            default -> {
                int pid = Integer.parseInt(request.getParameter("id"));
                Product product = pdao.getElementProductByPid(pid);
                cart.add(product);
                session.setAttribute("cart", cart);
                List<Category> listCate = pdao.getAllCate();
                request.setAttribute("listC", listCate);
                request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
            }
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     *
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
