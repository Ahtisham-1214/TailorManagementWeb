package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import Backend.ShopDetails;

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
            }else {
                response.sendRedirect("WEB-INF/view/Unauthorized.jsp");
            }
        } else if ("order".equals(action)) {
            if ("admin".equals(role)) {
                System.out.println("Forwarding to Customer Servlet for Order");
                response.sendRedirect(request.getContextPath() + "/CustomerServlet");
            } else {
                System.out.println("Failed to forward to Customer Servlet");
                response.sendRedirect( "WEB-INF/view/Unauthorized.jsp");
                return;
            }
        } else if ("setting".equals(action)) {
            if ("admin".equals(role)) {
                System.out.println("Forwarding to ShopDetailsServlet");
                response.sendRedirect(request.getContextPath() + "/ShopDetailsServlet");
            } else
                request.getRequestDispatcher(  "WEB-INF/view/Unauthorized.jsp").forward(request, response);
        } else if ("view".equals(action)) {
            if ("admin".equals(role)) {
                response.sendRedirect(request.getContextPath() + "/ViewServlet");
            }
            else {
                response.sendRedirect( "WEB-INF/view/Unauthorized.jsp");
            }


        } else {
            ShopDetails shopDetails = new ShopDetails();
            request.setAttribute("shopName", shopDetails.getName());
            request.setAttribute("shopAddress", shopDetails.getAddress());
            request.setAttribute("shopPhone", shopDetails.getPhone());
            request.setAttribute("shopEmail", shopDetails.getEmail());
            request.getRequestDispatcher("WEB-INF/view/Index.jsp").forward(request, response);
        }

        // Correct forwarding with absolute path
        // request.getRequestDispatcher("/Index.jsp").forward(request, response);
    }
}
