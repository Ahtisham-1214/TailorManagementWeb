package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import Backend.KameezShalwaar;
import Backend.Order;

@WebServlet("/KameezShalwaarServlet")
public class KameezShalwaarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginServlet");
            return;
        }
        req.getRequestDispatcher("/WEB-INF/view/KameezShalwaar.jsp").forward(req, resp);
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
        System.out.println("Received action from Kameez Shalwaar: " + action); // Debugging line

        try {
            if ("save".equals(action)) {
                float kameezLength = Float.parseFloat(req.getParameter("kameez-length"));
                float chest = Float.parseFloat(req.getParameter("chest"));
                float sleeveLength = Float.parseFloat(req.getParameter("sleeve-length"));
                float shoulder = Float.parseFloat(req.getParameter("shoulder"));
                float neck = Float.parseFloat(req.getParameter("neck"));
                byte cuffType = Byte.parseByte(req.getParameter("cuff-type"));
                byte kameezType = Byte.parseByte(req.getParameter("kameez-type"));
                byte collarType = Byte.parseByte(req.getParameter("collar-type"));
                byte status = Byte.parseByte(req.getParameter("status"));
                byte trouserType = Byte.parseByte(req.getParameter("trouser-type"));
                float trouserLength = Float.parseFloat(req.getParameter("trouser-length"));
                float trouserAnkle = Float.parseFloat(req.getParameter("trouser-ankle"));
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
                KameezShalwaar kameezShalwaar = new KameezShalwaar(trouserLength, trouserType,
                        trouserAnkle, kameezLength, chest, sleeveLength, cuffType, kameezType, shoulder,
                        neck, collarType, status, description, quantity, orderDate, deliveryDate, order.getCustomer().getPhoneNumber());

                order.addKameezShalwaarToOrder(kameezShalwaar);
                setAttributes(session, req);

                req.setAttribute("message", "Kameez Shalwaar saved successfully");
                req.getRequestDispatcher("/WEB-INF/view/KameezShalwaar.jsp").forward(req, resp);
                return;
            }


            if ("generate".equals(action)) {
                setAttributes(session, req);
                System.out.println("Redirecting to Receipt Servlet from KameezShalwaar Servlet"); // debugging
                resp.sendRedirect(req.getContextPath() + "/ReceiptServlet");
            } else if ("next".equals(action)) {
                resp.sendRedirect(req.getContextPath() + "/CustomerServlet");
            } else {
                resp.sendRedirect(req.getContextPath() + "/KameezShalwaarServlet"); // PRG pattern
            }

        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid number: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/KameezShalwaar.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("message", "Error: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/view/KameezShalwaar.jsp").forward(req, resp);
        }

    }


    private void setAttributes(HttpSession session, HttpServletRequest request) {
        session.setAttribute("kameez-length", request.getAttribute("kameez-length"));
        session.setAttribute("chest", request.getAttribute("chest"));
        session.setAttribute("sleeve-length", request.getAttribute("sleeve-length"));
        session.setAttribute("cuff-type", request.getAttribute("cuff-type"));
        session.setAttribute("kameez-type", request.getAttribute("kameez-type"));
        session.setAttribute("shoulder", request.getAttribute("shoulder"));
        session.setAttribute("neck", request.getAttribute("neck"));
        session.setAttribute("collar-type", request.getAttribute("collar-type"));
        session.setAttribute("status", request.getAttribute("status"));
        session.setAttribute("trouser-type", request.getAttribute("trouser-type"));
        session.setAttribute("trouser-ankle", request.getAttribute("trouser-ankle"));
        session.setAttribute("quantity", request.getAttribute("quantity"));
        session.setAttribute("description", request.getAttribute("description"));
        session.setAttribute("order-date", request.getAttribute("order-date"));
        session.setAttribute("delivery-date", request.getAttribute("delivery-date"));
    }
}
