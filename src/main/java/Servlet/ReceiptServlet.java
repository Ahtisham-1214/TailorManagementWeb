package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import Backend.ShopDetails;
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
        ShopDetails shopDetails = new ShopDetails();
        request.setAttribute("shopName", shopDetails.getName());
        request.setAttribute("shopAddress", shopDetails.getAddress());
        request.setAttribute("shopPhone", shopDetails.getPhone());
        request.setAttribute("shopEmail", shopDetails.getEmail());
        request.setAttribute("date", new SimpleDateFormat("MMMM dd, yyyy").format(new Date()));
        request.setAttribute("paymentMethod", "Cash");
//        response.sendRedirect("Receipt.jsp"); this created new request which removes the attributes
        request.getRequestDispatcher("Receipt.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
