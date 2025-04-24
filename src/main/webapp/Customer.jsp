<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/23/2025
  Time: 3:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Professional Customer Form</title>
    <script>
        // JavaScript function to clear all input fields
        function clearFields() {
            document.getElementById("customerForm").reset();
        }

        // JavaScript function to validate the form
        function validateForm(event) {
            const nameField = document.getElementById("name");
            const phoneField = document.getElementById("phone");

            if (!nameField.value.trim()) {
                alert("Name is required.");
                nameField.focus();
                return false;
            }

            if (!phoneField.value.trim()) {
                alert("Phone is required.");
                phoneField.focus();
                return false;
            }

            // If all validations pass, allow form submission
            alert("Form submitted successfully!");
            return true;
        }
    </script>
</head>
<body style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #f5f7fa 0%, #e4e8eb 100%); display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0;">
<div style="background: white; padding: 32px; border-radius: 12px; box-shadow: 0 8px 30px rgba(0, 0, 0, 0.08); width: 360px; border: 1px solid rgba(0, 0, 0, 0.05);">
    <h2 style="color: #2c3e50; margin-top: 0; margin-bottom: 24px; font-weight: 600; font-size: 22px;">Customer Information</h2>

    <!-- Add a form element with an ID -->
    <form id="customerForm" onsubmit="return validateForm(event)">
        <div style="margin-bottom: 24px;">
            <label style="display: block; margin-bottom: 8px; font-weight: 500; color: #4a5568; font-size: 14px;">Name</label>
            <input type="text" id="name" name="name" required style="width: 100%; padding: 12px; border: 1px solid #e2e8f0; border-radius: 6px; box-sizing: border-box; font-size: 14px; transition: border-color 0.2s;" placeholder="Name">
        </div>

        <div style="margin-bottom: 32px;">
            <label style="display: block; margin-bottom: 8px; font-weight: 500; color: #4a5568; font-size: 14px;">Phone</label>
            <input type="tel" id="phone" name="phone" required style="width: 100%; padding: 12px; border: 1px solid #e2e8f0; border-radius: 6px; box-sizing: border-box; font-size: 14px; transition: border-color 0.2s;" placeholder="Phone">
        </div>

        <div style="display: flex; justify-content: flex-end; gap: 12px;">
            <!-- Add an onclick event to the Clear button -->
            <button type="button" onclick="clearFields()" style="padding: 10px 20px; border: 1px solid #e2e8f0; border-radius: 6px; cursor: pointer; font-weight: 500; background-color: white; color: #4a5568; transition: all 0.2s;">Clear</button>
            <button type="submit" style="padding: 10px 20px; border: none; border-radius: 6px; cursor: pointer; font-weight: 500; background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%); color: white; transition: all 0.2s;">Save</button>
        </div>
    </form>
</div>
</body>
</html>