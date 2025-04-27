package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import Backend.Receipt;


import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet("/ReceiptServlet")
public class ReceiptServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // More robust session checking
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        Receipt receipt = new Receipt();
        request.setAttribute("shopName", receipt.getShopName());
        request.setAttribute("shopAddress", receipt.getShopAddress());
        request.setAttribute("shopPhone", receipt.getShopPhone());
        request.setAttribute("shopEmail", receipt.getShopEmail());
        request.setAttribute("date", new SimpleDateFormat("MMMM dd, yyyy").format(new Date()));
        request.setAttribute("paymentMethod", "Cash");
//        response.sendRedirect("Receipt.jsp"); this created new request which removes the attributes

//        Order order = new Order();
//        order.addOrder(new Customer("Ahtisham", "12345678901"));
//        order.getOrders().getFirst().getCoats().add(
//                new Coat(12, 12, 12, 12, (byte) 1,
//                        "hello", 10, null, null));



            request.setAttribute("customerName", receipt.getCustomerName());
            request.setAttribute("items", receipt.getItems());
//            if (!order.getOrders().getFirst().getCoats().isEmpty()) {
//                request.setAttribute("itemName", order.getOrders().getFirst().getCoats().getFirst().getClassName());
//                request.setAttribute("itemQuantity", new Order().getOrders().getFirst().getCoats().getFirst().getQuantity());
//                request.setAttribute("itemUnitPrice", new Prices().getCoatPrice());
//                request.setAttribute("itemAmount", );
//
//            }

        request.getRequestDispatcher("/WEB-INF/view/Receipt.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
