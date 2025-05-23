package Servlet;

import Backend.Coat;
import Backend.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/CoatServlet")
public class CoatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginServlet");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/view/Coat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginServlet");
            return;
        }

        String action = req.getParameter("action");
        System.out.println("Received action from Coat: " + action); // Debugging line


        try {

            if ("save".equals(action)) {
                float chest = Float.parseFloat(req.getParameter("chest"));
                float waist = Float.parseFloat(req.getParameter("waist"));
                float sleeves = Float.parseFloat(req.getParameter("sleeves"));
                float shoulder = Float.parseFloat(req.getParameter("shoulder"));
                byte status = Byte.parseByte(req.getParameter("status"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                String description = req.getParameter("description");
                String orderDateStr = req.getParameter("order-date");
                String deliveryDateStr = req.getParameter("delivery-date");

                Date orderDate = null;
                Date deliveryDate = null;

                if (orderDateStr != null && !orderDateStr.isEmpty()) {
                    orderDate = java.sql.Date.valueOf(orderDateStr);
                }

                if (deliveryDateStr != null && !deliveryDateStr.isEmpty()) {
                    deliveryDate = java.sql.Date.valueOf(deliveryDateStr);
                }

                Order order = new Order();
                order.addCoatToOrder(
                        new Coat(chest, waist, sleeves, shoulder,
                                status, description, quantity, orderDate,
                                deliveryDate, order.getCustomer().getPhoneNumber()));

                req.setAttribute("message", "Coat saved successfully");
                req.getRequestDispatcher("/WEB-INF/view/Coat.jsp").forward(req, resp);
                return;

            }
            if ("generate".equals(action)) {
                System.out.println("Redirecting to Receipt Servlet from Coat Servlet"); // debugging
                resp.sendRedirect(req.getContextPath() + "/ReceiptServlet");

            } else if ("next".equals(action)) {
                resp.sendRedirect(req.getContextPath() + "/PantServlet");
            } else {
                resp.sendRedirect(req.getContextPath() + "/CoatServlet"); // PRG Pattern

            }
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid number: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Coat.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", "Error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Coat.jsp").forward(req, resp);
        }

    }

}
