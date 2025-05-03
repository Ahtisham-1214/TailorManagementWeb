package Servlet;

import Backend.Order;
import Backend.Pant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/PantServlet")
public class PantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginServlet");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/view/Pant.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("Received action of Pant Servlet: " + action); // Debugging line

        HttpSession session = req.getSession();

        try {

            if ("save".equals(action) || "generate".equals(action)) {
                // Save form data to session or pass to ReceiptServlet
                float waist = Float.parseFloat(req.getParameter("waist"));
                float length = Float.parseFloat(req.getParameter("length"));
                float inseam = Float.parseFloat(req.getParameter("inseam"));
                byte type = Byte.parseByte(req.getParameter("type"));
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

                new Order().addPantsToOrder(new Pant(waist, length, type, inseam, status,
                        description, quantity, orderDate, deliveryDate));

                setAttributes(session, req);

            }
            if ("generate".equals(action)) {
                // Save form data to session or pass to ReceiptServlet
                setAttributes(session, req);
                System.out.println("Redirecting to ReceiptServlet from Pant servlet"); // debugging
                resp.sendRedirect(req.getContextPath() + "/ReceiptServlet");
            } else if ("next".equals(action)) {
                resp.sendRedirect(req.getContextPath() + "/ShirtServlet");
            } else {
                resp.sendRedirect(req.getContextPath() + "/PantServlet");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid number: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Pant.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Pant.jsp").forward(req, resp);
        }
    }

    private void setAttributes(HttpSession session, HttpServletRequest req) {
        session.setAttribute("waist", req.getParameter("waist"));
        session.setAttribute("length", req.getParameter("length"));
        session.setAttribute("inseam", req.getParameter("inseam"));
        session.setAttribute("type", req.getParameter("type"));
        session.setAttribute("status", req.getParameter("status"));
        session.setAttribute("quantity", req.getParameter("quantity"));
        session.setAttribute("orderDate", req.getParameter("order-date"));
        session.setAttribute("deliveryDate", req.getParameter("delivery-date"));
        session.setAttribute("description", req.getParameter("description"));
    }
}
