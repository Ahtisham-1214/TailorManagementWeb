package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // More robust session checking
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }

        String role = (String) session.getAttribute("role");
        String action = request.getParameter("action");

        System.out.println("Role: " + role);
        System.out.println("Action: " + action);

        if ("price".equals(action)) {
            if ("admin".equals(role)) {
                System.out.println("Forwarding to /PricesServlet");
                // Redirect to PricesServlet instead of directly to JSP
                response.sendRedirect(request.getContextPath() + "/PricesServlet");
                return;
//                request.getRequestDispatcher("/Prices.jsp").forward(request, response);
            }else {
                response.sendRedirect(request.getContextPath() + "/Unauthorized.jsp");
            }
            return;
        }
        else if ("order".equals(action)) {
            if ("admin".equals(role)) {
                System.out.println("Forwarding to Coat.jsp");
                request.getRequestDispatcher("Coat.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/Unauthorized.jsp");
            }
        } else {
            request.getRequestDispatcher("/Index.jsp").forward(request, response);
        }

        // Correct forwarding with absolute path
        // request.getRequestDispatcher("/Index.jsp").forward(request, response);
    }
}
