<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("LoginServlet");
        return;
    }

    String message = (String) request.getAttribute("message");
    String name = (String) request.getAttribute("name");
    String phone = (String) request.getAttribute("phone");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Professional Customer Form</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #f5f7fa 0%, #e4e8eb 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background: white;
            padding: 32px;
            border-radius: 12px;
            box-shadow: 0 8px 30px rgba(0, 0, 0, 0.08);
            width: 360px;
            border: 1px solid rgba(0, 0, 0, 0.05);
        }

        h2 {
            color: #2c3e50;
            margin-top: 0;
            margin-bottom: 24px;
            font-weight: 600;
            font-size: 22px;
        }

        .form-group {
            margin-bottom: 24px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 500;
            color: #4a5568;
            font-size: 14px;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            box-sizing: border-box;
            font-size: 14px;
            transition: border-color 0.2s;
        }

        .form-actions {
            display: flex;
            justify-content: flex-end;
            gap: 12px;
        }

        .btn-clear {
            padding: 10px 20px;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
            background-color: white;
            color: #4a5568;
            transition: all 0.2s;
        }

        .btn-submit {
            padding: 10px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
            background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%);
            color: white;
            transition: all 0.2s;
        }

        .message {
            font-weight: bold;
            margin-bottom: 16px;
        }

        .message.success {
            color: green;
        }

        .message.error {
            color: red;
        }
    </style>
    <script>

        function validateForm(event) {
            const nameField = document.getElementById("name");
            const phoneField = document.getElementById("phone");
            const message = document.getElementById("message");

            if (message){
                message.style.display= "none";
            }

            if (!nameField.value.trim()) {
                if (message) {
                    message.textContent = "Name is required";
                    message.style.color = "red";
                    message.style.display = "block";
                }
                nameField.focus();
                return false;
            }

            if (!phoneField.value.trim()) {
                if (message) {
                    message.textContent = "Phone is required";
                    message.style.color = "red";
                    message.style.display = "block";
                }
                phoneField.focus();
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<div class="form-container">
    <h2>Customer Information</h2>

    <% if (message != null && !message.isEmpty()) { %>
    <div id = "message" class="message <%= message.toLowerCase().contains("success") ? "success" : "error" %>">
        <%= message %>
    </div>
    <% } %>

    <form id="customerForm" action="CustomerServlet" method="post">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="<%= name != null ? name : "" %>" required placeholder="Name">
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" value="<%= phone != null ? phone : "" %>" required placeholder="Phone">
        </div>

        <div class="form-actions">
            <button type="reset"  class="btn-clear">Clear</button>
            <button type="submit" class="btn-submit">Save</button>
        </div>
    </form>
</div>
</body>
</html>
