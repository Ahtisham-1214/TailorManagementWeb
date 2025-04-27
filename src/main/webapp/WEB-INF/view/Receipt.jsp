<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/24/2025
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<%-- Check if the user is logged in --%>
<%
    //    HttpSession shopDetailsJSPSession = request.getSession(false);
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("LoginServlet");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment Receipt</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            color: #333;
        }
        .header {
            text-align: center;
            margin-bottom: 30px;
            border-bottom: 2px solid #eee;
            padding-bottom: 20px;
        }
        .company-name {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .receipt-title {
            font-size: 20px;
            margin: 15px 0;
        }
        .receipt-details {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }
        .details-left, .details-right {
            width: 48%;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f5f5f5;
        }
        .total {
            font-weight: bold;
            font-size: 18px;
            text-align: right;
            margin-top: 20px;
        }
        .footer {
            margin-top: 40px;
            border-top: 2px solid #eee;
            padding-top: 20px;
            text-align: center;
            font-size: 14px;
            color: #777;
        }
        .thank-you {
            font-size: 18px;
            text-align: center;
            margin: 30px 0;
            font-style: italic;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="shop-name"><h1>${shopName}</h1></div>
    <div>${shopAddress}</div>
    <div>Phone: ${shopPhone} | Email: ${shopEmail}</div>

    <div class="receipt-title">PAYMENT RECEIPT</div>
</div>

<div class="receipt-details">
    <div class="details-left">
        <p><strong>Receipt #:</strong> INV-2023-001</p>
        <p><strong>Date:</strong> ${date}</p>
        <p><strong>Payment Method:</strong> ${paymentMethod}</p>
    </div>
    <div class="details-right">
        <p><strong>Customer:</strong> ${customerName}</p>
    </div>
</div>

<table>
    <thead>
    <tr>
        <th>Item</th>
        <th>Quantity</th>
        <th>Unit Price</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.quantity}</td>
            <td>$${item.unitPrice}</td>
            <td>$${item.amount}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<div class="total">
    Subtotal: $525.00<br>
    Tax (10%): $52.50<br>
    <span style="font-size: 20px;">Total: $577.50</span><br>
    <span style="color: green;">Payment Received: $577.50</span>
</div>

<div class="thank-you">
    Thank you for your business!
</div>

<div class="footer">
    <p>If you have any questions about this receipt, please contact us at ${shopEmail} or ${shopPhone}</p>
    <p>© 2023 Your Company Name. All rights reserved.</p>
</div>
</body>
</html>
