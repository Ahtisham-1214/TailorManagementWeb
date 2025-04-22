package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import Backend.Prices;
import Database.*;

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


        PricesDatabase pricesDatabase = new PricesDatabase();
        try {
            pricesDatabase.fetchPrices();
            // In PricesServlet.doGet()
            System.out.println("Fetched prices: " +
                    "Shirt=" + pricesDatabase.getPrices().getShirtPrice() + ", " +
                    "Pant=" + pricesDatabase.getPrices().getPantPrice());

            request.setAttribute("coatPrice", pricesDatabase.getPrices().getCoatPrice());
            request.setAttribute("pantPrice", pricesDatabase.getPrices().getPantPrice());
            request.setAttribute("shirtPrice", pricesDatabase.getPrices().getShirtPrice());
            request.setAttribute("kameezShalwaarPrice", pricesDatabase.getPrices().getKameezShalwaarPrice());
            request.getRequestDispatcher("/Prices.jsp").forward(request, response);
        } catch (SQLException e) {
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
            Prices prices = new Prices();

            // Debugging
            System.out.println("Pant Price: " + request.getParameter("pantPrice"));
            System.out.println("Shirt Price: " + request.getParameter("shirtPrice"));
            System.out.println("Coat Price: " + request.getParameter("coatPrice"));
            System.out.println("Kameez Shalwaar Price: " + request.getParameter("kameezShalwaarPrice"));


            prices.setPantPrice(Float.parseFloat(request.getParameter("pantPrice")));
            prices.setShirtPrice(Float.parseFloat(request.getParameter("shirtPrice")));
            prices.setCoatPrice(Float.parseFloat(request.getParameter("coatPrice")));
            prices.setKameezShalwaarPrice(Float.parseFloat(request.getParameter("kameezShalwaarPrice")));

            PricesDatabase pricesDatabase = new PricesDatabase();
            pricesDatabase.setPrices(prices);
            try (Connection connection = DatabaseConnection.getConnection()) {
                connection.setAutoCommit(false);
                pricesDatabase.updatePrices(connection);
                connection.commit();
            }

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
        } catch (SQLException e) {
            request.setAttribute("error", "Update failed" + e.getMessage());
            doGet(request, response);
        }
    }
}
