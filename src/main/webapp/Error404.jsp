<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/20/2025
  Time: 10:12 PM
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
<html>
<head>
    <title>404 - Page Not Found</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            background-color: #f8f9fa;
        }
        .error-container {
            max-width: 600px;
            margin: 0 auto;
        }
        h1 {
            color: #dc3545;
            font-size: 50px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>404</h1>
    <h2>Page Not Found</h2>
    <p>The requested URL <strong>${pageContext.errorData.requestURI}</strong> was not found.</p>

    <c:if test="${not empty pageContext.errorData.queryString}">
        <p>Query string: ${pageContext.errorData.queryString}</p>
    </c:if>

    <p>Status code: ${pageContext.errorData.statusCode}</p>

    <a href="${pageContext.request.contextPath}/" class="btn">Go to Homepage</a>

    <!-- Hidden technical details (for debugging) -->
    <details style="margin-top: 30px; color: #6c757d;">
        <summary>Technical Details</summary>
        <p>Servlet Name: ${pageContext.errorData.servletName}</p>
        <p>Exception: ${pageContext.exception}</p>
        <p>Message: ${pageContext.exception.message}</p>
    </details>
</div>
</body>
</html>