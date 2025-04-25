package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import Backend.ShopDetails;

@WebServlet("/ShopDetailsServlet")
public class ShopDetailsServlet extends HttpServlet {
    public ShopDetailsServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // More robust session checking
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        // Check for success message
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }


        ShopDetails shopDetails = new ShopDetails();
        request.setAttribute("name", shopDetails.getName());
        request.setAttribute("address", shopDetails.getAddress());
        request.setAttribute("phone", shopDetails.getPhone());
        request.setAttribute("email", shopDetails.getEmail());
        request.getRequestDispatcher("/ShopDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // More robust session checking
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        ShopDetails shopDetails =
                new ShopDetails(
                        request.getParameter("name"),
                        request.getParameter("address"),
                        request.getParameter("phone"),
                        request.getParameter("email"));

        System.out.println("ShopDetails set on Servlet Do Post");
        // Add success message to session
        session.setAttribute("successMessage", "Shop Details updated successfully");

        response.sendRedirect("ShopDetailsServlet");
//        request.getRequestDispatcher("/ShopDetails.jsp").forward(request, response);

    }


}