package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import Backend.Customer;
import Backend.Order;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private static final String Customer_ADDED_SESSION_KEY = "customerAdded";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/loginServlet");
            return;
        }


        // Fetch message from session if it exists
        String message = (String) session.getAttribute("message");
        session.removeAttribute("message"); // clear it after fetching
        req.setAttribute("message", message);

        // Fetch form values back if stored (only in error cases)
        req.setAttribute("name", session.getAttribute("name"));
        req.setAttribute("phone", session.getAttribute("phone"));
        session.removeAttribute("name");
        session.removeAttribute("phone");

        // Check for previous submission
        String orderSubmitted = (String) session.getAttribute(Customer_ADDED_SESSION_KEY);
        if ("true".equals(orderSubmitted)) {
            req.setAttribute("message", "Customer already Added");
            session.removeAttribute(Customer_ADDED_SESSION_KEY);
            resp.sendRedirect(req.getContextPath() + "/CoatServlet");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/view/Customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/loginServlet");
            return;
        }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        // Store back inputs in session (in case of error)
        session.setAttribute("name", name);
        session.setAttribute("phone", phone);

        // Check if name is empty or invalid
        if (name == null || name.trim().isEmpty()) {
            session.setAttribute("message", "Name is required");
            response.sendRedirect("CustomerServlet");
            return;
        }

        // Check if phone number is invalid
        if (phone == null || phone.trim().isEmpty()) {
            session.setAttribute("message", "Phone number Required");
            response.sendRedirect("CustomerServlet");
            return;
        }


        try {
            new Order().addOrder(new Customer(name, phone));
            session.setAttribute("message", "Customer added successfully");
            session.setAttribute(Customer_ADDED_SESSION_KEY, "true");
            // Clear values on success
            session.removeAttribute("name");
            session.removeAttribute("phone");
        } catch (IllegalArgumentException e) {
            session.setAttribute("message", e.getMessage());
            response.sendRedirect("CustomerServlet");
            return;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        response.sendRedirect("CustomerServlet"); // PRG pattern
    }
}
