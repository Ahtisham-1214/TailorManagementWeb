<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/20/2025
  Time: 5:22 PM
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
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Tailor Management</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Index.css">
</head>
<body>

<header>
    <button class="hamburger-menu" id="hamburger">☰</button>
    <h1>${shopName}</h1>
</header>

<div class="sidebar" id="sidebar">
    <h2>Menu</h2>
    <ul>
        <li><a href="DashboardServlet?action=setting" style="color: white; text-decoration: none;">Settings</a></li>
        <!-- <li>Price</li> -->
        <li><a href="DashboardServlet?action=price" style="color: white; text-decoration: none;">Price</a></li>
    </ul>
    <hr style="margin: 20px 0; border-color: rgba(255,255,255,0.2);"/>

    <div class="shop-info" style="font-size: 13px; line-height: 1.6;">
        <p><strong>${shopName}</strong></p>
        <p>📍 ${shopAddress}</p>
        <p>📞 <a href="tel:${shopPhone}" style="color: white; text-decoration: none;">${shopPhone}</a></p>
        <p style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">✉️ <a href="mailto:${shopEmail}"
                                                                                         style="color: white; text-decoration: none;"
                                                                                         title="${shopEmail}">${shopEmail}</a>
        </p>
        <hr style="margin: 20px 0; border-color: rgba(255,255,255,0.2);"/>
<%--        <p><a href="" style="color: white; text-decoration: none;">🔓 Logout</a></p>--%>
        <p><a href="LogoutServlet" style="display: inline-block; padding: 8px 15px; background-color: #e74c3c; color: white; border-radius: 5px; text-decoration: none; margin-top: 10px;">🔓 Logout</a>
        </p>

    </div>
</div>

<div class="overlay" id="overlay"></div>

<div class="main" id="mainContent">
    <a href="DashboardServlet?action=order" style="text-decoration: none; color: inherit;">
        <div class="card" id="ordercard">
            <%--                <%&#45;&#45;        <%&#45;&#45;%>--%>
            <%--                <%&#45;&#45;&lt;%&ndash;            String contextPath = request.getContextPath();&ndash;%&gt; when images were not showing&#45;&#45;%>-->--%>
            <%--        <!--        <%&#45;&#45;        %>&#45;&#45;%>-->--%>
            <%--        <!--        <%&#45;&#45;    <img src="${pageContext.request.contextPath}/images/measurement.jpg" alt="Order Image"/> now image working without this-->--%>
            <!--        problem maybe was mnvd clean package or maybe isELI&#45;&#45;%>-->

            <img src="${pageContext.request.contextPath}/images/measurement.jpg" alt="Order Image"/>
            <div class="card-content">
                <h3>Order</h3>
            </div>
        </div>
    </a>

    <a href="DashboardServlet?action=view" style="text-decoration: none; color: inherit;">
        <div class="card">
            <img src="${pageContext.request.contextPath}/images/view.jpg"
                 alt="View Image"/>
            <div class="card-content">
                <h3>View</h3>
            </div>
        </div>
    </a>

    <div class="card">
        <img src="${pageContext.request.contextPath}/images/analysis.jpg" alt="Analysis Image"/>
        <div class="card-content">
            <h3>Analysis</h3>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/Index.js"></script>
</body>
</html>

