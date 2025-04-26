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

@WebServlet("/CoatServlet")
public class CoatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/LoginServlet");
        }

        req.getRequestDispatcher("/WEB-INF/view/Coat.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        float chest = Float.parseFloat(req.getParameter("chest"));
        float waist = Float.parseFloat(req.getParameter("waist"));
        float sleeves = Float.parseFloat(req.getParameter("sleeves"));
        float shoulder = Float.parseFloat(req.getParameter("shoulder"));
        byte status = Byte.parseByte(req.getParameter("status"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        java.sql.Date orderDate = java.sql.Date.valueOf(req.getParameter("order-date"));
        java.sql.Date deliveryDate = java.sql.Date.valueOf(req.getParameter("delivery-date"));
        String description = req.getParameter("description");

        new Order().getOrders().getFirst().getCoats().add(
                new Coat(chest, waist, sleeves, shoulder, status, description, quantity, orderDate, deliveryDate));
        System.out.println("Coat added successfully");

    }
}
