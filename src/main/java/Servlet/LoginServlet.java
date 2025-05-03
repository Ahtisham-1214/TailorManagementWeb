package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Backend.User;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // Prevent caching used to avoid go back to login page
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0
        res.setDateHeader("Expires", 0); // Proxies

        if (req.getSession(false) != null && req.getSession().getAttribute("user") != null) {
            res.sendRedirect(req.getContextPath() + "/DashboardServlet");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, res); // Changed to JSP and added forward slash
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        try {

            User user = new User(userName, password);
            System.out.println("Database connection established.");
            System.out.println("Authenticating user: " + userName);

            String role = user.authenticateUser();

            if (role != null) {
                System.out.println("User authenticated successfully. Role: " + role);

                HttpSession oldSession = req.getSession(false);
                if (oldSession != null) {
                    oldSession.invalidate();
                }
                HttpSession newSession = req.getSession(true);
                newSession.setAttribute("user", userName);
                newSession.setAttribute("role", role); // Store the user's role in the session
                System.out.println("User role stored in session: " + role);

                // Set session timeout to 30 minutes
//                newSession.setMaxInactiveInterval(30*60);
                System.out.println("User authenticated successfully.");
                res.sendRedirect(req.getContextPath() + "/DashboardServlet");
            } else {
                System.out.println("Authentication failed for user: " + userName);
                req.setAttribute("message", "Invalid username or password");
                System.out.println("Forwarding back to Login.jsp with error message.");
                req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, res);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            req.setAttribute("message", "Unable to connect to the database. Please try again later.");
            req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, res);
        } catch (Exception e) {
            req.setAttribute("message", "Server error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, res);
        }
    }


}
