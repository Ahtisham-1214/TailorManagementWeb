package Servlet;

import Backend.Order;
import Backend.Shirt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@WebServlet("/ShirtServlet")
public class ShirtServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginServlet");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/view/Shirt.jsp").forward(req, resp);
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
        System.out.println("Received action of Shirt Servlet: " + action); // Debugging line


        try {

            if ("save".equals(action)) {

                float neck = Float.parseFloat(req.getParameter("neck"));
                float chest = Float.parseFloat(req.getParameter("chest"));
                float shoulder = Float.parseFloat(req.getParameter("shoulder"));
                float shirtLength = Float.parseFloat(req.getParameter("shirt-length"));
                float sleeveLength = Float.parseFloat(req.getParameter("sleeve-length"));
                byte cuffType = Byte.parseByte(req.getParameter("cuff-type"));
                byte collarType = Byte.parseByte(req.getParameter("collar-type"));
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

                Shirt shirt = new Shirt(chest, sleeveLength, shirtLength, shoulder,
                        neck, collarType, cuffType, status, description, quantity, orderDate, deliveryDate);

                new Order().addShirtToOrder(shirt);

                setAttributes(session, req);
                req.setAttribute("message", "Shirt saved successfully");
                req.getRequestDispatcher("/WEB-INF/view/Shirt.jsp").forward(req, resp);
                return;
            }
            if ("next".equals(action)) {
                resp.sendRedirect(req.getContextPath() + "/KameezShalwaarServlet");
            } else if ("generate".equals(action)) {
                // Save form data to session or pass to ReceiptServlet
                setAttributes(session, req);
                System.out.println("Redirecting to ReceiptServlet from Shirt servlet"); // debugging
                resp.sendRedirect(req.getContextPath() + "/ReceiptServlet");
            } else {
                resp.sendRedirect(req.getContextPath() + "/ShirtServlet");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid number: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Shirt.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", "Error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/Shirt.jsp").forward(req, resp);
        }

    }


    private void setAttributes(HttpSession session, HttpServletRequest req) {
        session.setAttribute("neck", req.getParameter("neck"));
        session.setAttribute("chest", req.getParameter("chest"));
        session.setAttribute("shoulder", req.getParameter("shoulder"));
        session.setAttribute("shirt-length", req.getParameter("shirt-length"));
        session.setAttribute("sleeve-length", req.getParameter("sleeve-length"));
        session.setAttribute("cuff-type", req.getParameter("cuff-type"));
        session.setAttribute("collar-type", req.getParameter("collar-type"));
        session.setAttribute("status", req.getParameter("status"));
        session.setAttribute("quantity", req.getParameter("quantity"));
        session.setAttribute("order-date", req.getParameter("order-date"));
        session.setAttribute("delivery-date", req.getParameter("delivery-date"));
        session.setAttribute("description", req.getParameter("description"));
    }
}
