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
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 40px 20px;
            color: #495057;
        }

        .form-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
        }

        h1 {
            text-align: center;
            color: #2b3a4a; /* Professional dark blue-gray */
            margin-bottom: 35px;
            font-weight: 600;
            font-size: 28px;
            letter-spacing: -0.5px;
            position: relative;
            padding-bottom: 15px;
        }

        h1:after {
            content: "";
            position: absolute;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 80px;
            height: 3px;
            background: linear-gradient(90deg, #4a6bff, #3a5af9);
            border-radius: 3px;
        }

        .form-group {
            margin-bottom: 25px;
            display: flex;
            align-items: center;
        }

        label {
            flex: 1;
            font-weight: 500;
            color: #5a6268;
            margin-right: 20px;
            font-size: 15px;
        }

        input {
            flex: 3;
            padding: 12px 18px;
            border: 1px solid #e0e3e8;
            border-radius: 6px;
            font-size: 15px;
            transition: all 0.3s;
            background-color: #f8fafc;
        }

        input:focus {
            outline: none;
            border-color: #4a6bff;
            box-shadow: 0 0 0 3px rgba(74, 107, 255, 0.15);
            background-color: white;
        }

        .edit-btn {
            background-color: #f0f2f5;
            color: #4a6bff;
            border: none;
            padding: 10px 15px;
            border-radius: 6px;
            cursor: pointer;
            font-size: 14px;
            font-weight: 500;
            margin-left: 15px;
            transition: all 0.3s;
            display: flex;
            align-items: center;
            gap: 6px;
        }

        .edit-btn:hover {
            background-color: #e6e9f0;
        }

        .edit-btn:before {
            content: "✏";
            font-size: 12px;
        }

        .submit-btn {
            display: block;
            width: 100%;
            padding: 14px;
            background: linear-gradient(135deg, #4a6bff, #3a5af9);
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            margin-top: 35px;
            transition: all 0.3s;
            box-shadow: 0 4px 12px rgba(74, 107, 255, 0.2);
        }

        .submit-btn:hover {
            background: linear-gradient(135deg, #3a5af9, #2a4af0);
            transform: translateY(-2px);
            box-shadow: 0 6px 16px rgba(74, 107, 255, 0.3);
        }

        .submit-btn:active {
            transform: translateY(0);
        }

        .form-header {
            margin-bottom: 30px;
        }
    </style>
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

<script>
    const form = document.querySelector('form');
    const saveStatus = document.getElementById('saveStatus');

    // Store original values on load
    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        input.dataset.originalValue = input.value;
    });

    // Toggle edit/cancel button
    document.querySelectorAll('.edit-btn').forEach(button => {
        button.addEventListener('click', function () {
            const input = this.parentElement.querySelector('input');

            if (input.hasAttribute('readonly')) {
                input.dataset.originalValue = input.value;
                input.removeAttribute('readonly');
                input.focus();
                input.select();
                this.textContent = 'Cancel';
            } else {
                input.value = input.dataset.originalValue;
                input.setAttribute('readonly', true);
                this.textContent = 'Edit';
            }
        });
    });

    // Check for changes
    function checkChanges() {
        return Array.from(inputs).some(input => input.value !== input.dataset.originalValue);
    }

    // Form submit handler
    form.addEventListener('submit', function (e) {
        if (!checkChanges()) {
            e.preventDefault();
            saveStatus.textContent = 'No changes to save';
            saveStatus.style.color = '#e74c3c';

            setTimeout(() => {
                saveStatus.textContent = '';
            }, 3000);
            return;
        }

        // Simulate saving
        // e.preventDefault(); // If you don’t want to actually submit
        saveStatus.textContent = '';

        setTimeout(() => {
            saveStatus.textContent = 'Information updated successfully!';
            saveStatus.style.color = '#2ecc71';

            // Reset state
            inputs.forEach(input => {
                input.dataset.originalValue = input.value;
                input.setAttribute('readonly', true);

                const btn = input.parentElement.querySelector('.edit-btn');
                if (btn) btn.textContent = 'Edit';
            });

            setTimeout(() => {
                saveStatus.textContent = '';
            }, 3000);
        }, 1000); // Simulated delay — you can reduce/remove this if desired
    });

</script>

</body>
</html>