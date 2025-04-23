package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import Backend.Prices;

@WebServlet("/PricesServlet")
public class PricesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check for success message
        String successMessage = (String) request.getSession().getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            request.getSession().removeAttribute("successMessage");
        }


        Prices prices = new Prices();
        try {
            // In PricesServlet.doGet()
            System.out.println("Fetched prices: " +
                    "Shirt=" + prices.getShirtPrice() + ", " +
                    "Pant=" + prices.getPantPrice());

            request.setAttribute("coatPrice", prices.getCoatPrice());
            request.setAttribute("pantPrice", prices.getPantPrice());
            request.setAttribute("shirtPrice", prices.getShirtPrice());
            request.setAttribute("kameezShalwaarPrice", prices.getKameezShalwaarPrice());
            request.getRequestDispatcher("/Prices.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load price");
            request.getRequestDispatcher("/Prices.jsp").forward(request, response);
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            // Debugging
            System.out.println("Pant Price: " + request.getParameter("pantPrice"));
            System.out.println("Shirt Price: " + request.getParameter("shirtPrice"));
            System.out.println("Coat Price: " + request.getParameter("coatPrice"));
            System.out.println("Kameez Shalwaar Price: " + request.getParameter("kameezShalwaarPrice"));

            Prices prices = new Prices(Float.parseFloat(request.getParameter("pantPrice")),
                    Float.parseFloat(request.getParameter("shirtPrice")),
                    Float.parseFloat(request.getParameter("coatPrice")),
                    Float.parseFloat(request.getParameter("kameezShalwaarPrice")));


            // Add success message to session
            request.getSession().setAttribute("successMessage", "Prices updated successfully");

            // Introduce a delay to allow frontend animation to complete
            try {
                Thread.sleep(5000); // Delay for 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                e.printStackTrace();
            }
            response.sendRedirect("PricesServlet");
        } catch (Exception e) {
            request.setAttribute("error", "Update failed" + e.getMessage());
            doGet(request, response);
        }
    }
}
