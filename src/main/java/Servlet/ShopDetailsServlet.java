package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import Backend.ShopDetails;

@WebServlet("/ShopDetailsServlet")
public class ShopDetailsServlet extends HttpServlet {
    public ShopDetailsServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check for success message
        String successMessage = (String) request.getSession().getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            request.getSession().removeAttribute("successMessage");
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

        ShopDetails shopDetails =
                new ShopDetails(
                        request.getParameter("name"),
                        request.getParameter("address"),
                        request.getParameter("phone"),
                        request.getParameter("email"));

        System.out.println("ShopDetails set on Servlet Do Post");
        // Add success message to session
        request.getSession().setAttribute("successMessage", "Shop Details updated successfully");

        // Redirect back to GET to load updated data
        try {
            Thread.sleep(2000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            e.printStackTrace();
        }

        response.sendRedirect("ShopDetailsServlet");
//        request.getRequestDispatcher("/ShopDetails.jsp").forward(request, response);
    
}


}