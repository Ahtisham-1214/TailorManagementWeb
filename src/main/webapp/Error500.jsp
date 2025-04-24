<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/20/2025
  Time: 10:13 PM
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
    <title>500 - Server Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
            background-color: #f8f9fa;
        }
        .error-container {
            max-width: 800px;
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
        pre {
            text-align: left;
            background-color: #f1f1f1;
            padding: 15px;
            border-radius: 5px;
            overflow-x: auto;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>500</h1>
    <h2>Internal Server Error</h2>
    <p>Sorry, something went wrong on our end.</p>

    <a href="${pageContext.request.contextPath}/" class="btn">Go to Homepage</a>
    <a href="javascript:history.back()" class="btn">Go Back</a>

    <!-- Debug information (only show in development) -->
    <c:if test="${pageContext.request.serverName == 'localhost'}">
        <details open style="margin-top: 30px;">
            <summary>Error Details</summary>
            <p><strong>Servlet:</strong> ${pageContext.errorData.servletName}</p>
            <p><strong>Status Code:</strong> ${pageContext.errorData.statusCode}</p>
            <p><strong>Request URI:</strong> ${pageContext.errorData.requestURI}</p>

            <c:if test="${not empty pageContext.exception}">
                <h3>Exception:</h3>
                <p><strong>Type:</strong> ${pageContext.exception.class.name}</p>
                <p><strong>Message:</strong> ${pageContext.exception.message}</p>

                <h3>Stack Trace:</h3>
                <pre><c:forEach items="${pageContext.exception.stackTrace}" var="trace">
                    ${trace}
                </c:forEach></pre>
            </c:if>
        </details>
    </c:if>

    <!-- Contact information -->
    <div style="margin-top: 30px; color: #6c757d;">
        <p>If the problem persists, please contact support at:</p>
        <p>support@tailormanagement.com</p>
    </div>
</div>
</body>
</html>