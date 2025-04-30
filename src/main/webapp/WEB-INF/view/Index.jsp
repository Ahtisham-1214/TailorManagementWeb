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
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            min-height: 100vh;
            background-color: #ecf0f1;
            display: flex;
            flex-direction: column;
            overflow-x: hidden;
        }

        header {
            background-color: #2c3e50;
            color: white;
            padding: 15px 20px;
            position: relative;
            z-index: 1001;
            height: 60px;
        }

        header h1 {
            font-size: 24px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
        }

        .hamburger-menu {
            font-size: 26px;
            background: none;
            border: none;
            color: white;
            cursor: pointer;
            position: absolute;
            left: 20px;
            top: 50%;
            transform: translateY(-50%);
        }

        .sidebar {
            width: 250px;
            background-color: #34495e;
            color: white;
            padding: 20px;
            height: 100vh;
            position: fixed;
            left: -250px;
            top: 0;
            transition: left 0.3s ease;
            z-index: 1001;
        }

        .sidebar.active {
            left: 0;
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            background-color: rgba(0, 0, 0, 0.4);
            display: none;
            z-index: 1000;
        }

        .overlay.active {
            display: block;
        }

        .sidebar h2 {
            font-size: 20px;
            margin-bottom: 20px;
        }

        .sidebar ul {
            list-style: none;
        }

        .sidebar li {
            margin: 15px 0;
            cursor: pointer;
        }

        .main {
            margin-top: 20px;
            padding: 20px;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
            justify-content: center;
            transition: filter 0.3s ease;
        }

        .card {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            width: 350px; /* Increased width */
            height: 400px; /* Added height to accommodate larger images */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }

        .card:hover {
            transform: scale(1.03);
        }

        .card img {
            width: 100%;
            height: 250px; /* Adjusted height for better image visibility */
            object-fit: cover;
        }

        .card-content {
            padding: 15px;
            text-align: center;
        }

        .card-content h3 {
            font-size: 20px;
        }
    </style>
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

<script>
    const hamburger = document.getElementById('hamburger');
    const sidebar = document.getElementById('sidebar');
    const overlay = document.getElementById('overlay');

    // Open/close sidebar and toggle overlay
    hamburger.addEventListener('click', (e) => {
        e.stopPropagation();
        sidebar.classList.toggle('active');
        overlay.classList.toggle('active');
    });

    // Close sidebar when clicking outside (on overlay)
    overlay.addEventListener('click', () => {
        sidebar.classList.remove('active');
        overlay.classList.remove('active');
    });

    // Optional: close if clicking anywhere else too
    document.addEventListener('click', (e) => {
        if (!sidebar.contains(e.target) && !hamburger.contains(e.target)) {
            sidebar.classList.remove('active');
            overlay.classList.remove('active');
        }
    });
</script>
</body>
</html>

