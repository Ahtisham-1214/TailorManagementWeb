<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/22/2025
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Shop Details Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ShopDetails.css">
</head>
<body>
<div class="form-container">
    <div class="form-header">
        <h1>Shop Information</h1>
    </div>

    <form id="shopDetails" action="ShopDetailsServlet" method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="${name}" data-original="${name}" readonly>
            <button type="button" class="edit-btn">Edit</button>
        </div>

        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" id="address" name="address" value="${address}" data-original="${address}" readonly>
            <button type="button" class="edit-btn">Edit</button>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number</label>
            <input type="tel" id="phone" name="phone" value="${phone}" data-original="${phone}" readonly>
            <button type="button" class="edit-btn">Edit</button>
        </div>

        <div class="form-group">
            <label for="email">Email Address</label>
            <input type="email" id="email" name="email" value="${email}" data-original="${email}" readonly>
            <button type="button" class="edit-btn">Edit</button>
        </div>

        <button type="submit" class="submit-btn">Save Changes</button>

        <div id="saveStatus" style="margin-top: 15px; font-weight: 500;"></div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/ShopDetails.js"></script>
</body>
</html>