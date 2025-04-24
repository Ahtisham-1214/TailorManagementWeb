package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Database.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
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

        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection == null) {
                throw new SQLException("Failed to establish a database connection.");
            }

            System.out.println("Database connection established.");
            System.out.println("Authenticating user: " + userName);

            String role = authenticateUser(connection, userName, password);

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
                req.setAttribute("error", "Invalid username or password");
               System.out.println("Forwarding back to Login.jsp with error message.");
                req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Unable to connect to the database. Please try again later.");
            req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, res);
        } catch (Exception e) {
            req.setAttribute("error", "Server error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, res);
        }
    }

    /**
     * Authenticates the user and retrieves their role from the database.
     *
     * @param connection The database connection.
     * @param userName   The username.
     * @param password   The password.
     * @return The user's role if authentication is successful, otherwise null.
     * @throws Exception If an error occurs during authentication.
     */
    private String authenticateUser(Connection connection, String userName, String password) throws Exception {
        String query = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String role = resultSet.getString("role");
                    System.out.println("Authentication successful. Role: " + role);
                    return role; // Return the user's role
                }
            }
        }
        return null; // Return false if authentication fails
    }
}
