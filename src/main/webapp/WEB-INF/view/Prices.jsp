<%-- Created by IntelliJ IDEA. User: ahtis Date: 4/21/2025 Time: 6:18 PM To change this template use File | Settings |
    File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <title>Clothing Rates - Admin Panel</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Prices.css">
</head>

<body>
<div class="container">
    <div class="header">
        <h1>Clothing Items Price List</h1>
    </div>
    <!-- Debug output -->
    <c:if test="${not empty shirtPrice}">
        <script>console.log("Prices loaded:", {
            shirt: ${ shirtPrice },
            pant: ${ pantPrice }
        });</script>
    </c:if>
    <form id="priceForm" action="PricesServlet" method="post">
        <table class="rates-table">
            <thead>
            <tr>
                <th>Item</th>
                <th>Price (RS)</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Shirt</td>
                <td>
                    <input type="number" name="shirtPrice" class="price-input"
                           value="${shirtPrice}"
                           data-original="${shirtPrice}" min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            <tr>
                <td>Coat</td>
                <td>
                    <input type="number" name="coatPrice" class="price-input"
                           value="${coatPrice}"
                           data-original="${coatPrice}"
                           min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            <tr>
                <td>Kameez Shalwaar</td>
                <td>
                    <input type="number" name="kameezShalwaarPrice" class="price-input"
                           value="${kameezShalwaarPrice}"
                           data-original="${kameezShalwaarPrice}"
                           min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            <tr>
                <td>Pant</td>
                <td>
                    <input type="number" name="pantPrice" class="price-input"
                           value="${pantPrice}"
                           data-original="${pantPrice}"
                           min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            </tbody>
        </table>

        <div class="save-container">
            <button type="submit" class="save-btn" id="saveBtn">Update Prices</button>
            <div id="loadingIndicator" style="display:none;">Saving...</div>
        </div>
    </form>
    <div class="disclaimer">
        <strong>Important:</strong> All prices shown are current but subject to change.
        Changes made here will update the public price list.
    </div>

    <div class="footer">
        <p>For assistance with price updates, contact IT support</p>
        <p id="saveStatus"></p>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/Prices.js"></script>
</body>

</html>