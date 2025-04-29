<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/29/2025
  Time: 11:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pant Order Form</title>
    <style>
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34495e;
            --accent-color: #3498db;
            --light-gray: #ecf0f1;
            --dark-gray: #7f8c8d;
            --border-color: #bdc3c7;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: #f9f9f9;
            padding: 20px;
            max-width: 800px;
            margin: 0 auto;
        }

        .form-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .form-header {
            background-color: var(--primary-color);
            color: white;
            padding: 15px 20px;
            font-size: 24px;
            font-weight: 600;
        }

        .form-section {
            padding: 20px;
            border-bottom: 1px solid var(--border-color);
        }

        .measurements-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: 15px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
            color: var(--secondary-color);
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }

        .form-group textarea {
            min-height: 80px;
            resize: vertical;
        }

        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            outline: none;
            border-color: var(--accent-color);
            box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
        }

        .date-selectors {
            display: flex;
            gap: 15px;
        }

        .date-selector {
            flex: 1;
        }

        .action-buttons {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 20px;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.2s;
            font-size: 14px;
        }

        .btn-clear {
            background-color: var(--light-gray);
            color: var(--secondary-color);
        }

        .btn-clear:hover {
            background-color: #dfe6e9;
        }

        .btn-save {
            background-color: var(--accent-color);
            color: white;
        }

        .btn-save:hover {
            background-color: #2980b9;
        }

        @media (max-width: 600px) {
            .measurements-grid {
                grid-template-columns: 1fr;
            }

            .date-selectors {
                flex-direction: column;
                gap: 10px;
            }
        }
    </style>

    <script>
        function validateForm(event){
            event.preventDefault();

            const message = document.getElementById("message");

            if (message) {
                message.style.display = "none";
            }

            function showMessage(text, type = "error") {
                if (message) {
                    message.textContent = text;
                    message.className = "message " + type;
                    message.style.display = "block";
                }
            }

            function highlightField(field) {
                field.style.border = "2px solid red";
            }

            function resetFieldHighlight(field) {
                field.style.border = "1px solid var(--border-color)";
            }

            document.getElementById("pant-form").submit();
        }
    </script>
</head>
<body>
<div class="form-container">
    <div class="form-header">Pant</div>

    <div id="message" class="message <%=
    (message != null && !message.isEmpty())
    ? (message.toLowerCase().contains("success") ? "success" : "error")
    : ""%>" aria-live="polite">
        <%= (message != null) ? message : "" %>
    </div>

    <form id="pant-form" onsubmit="return validateForm(event)" action="PricesServlet" method="post">
    <div class="form-section">
        <div class="measurements-grid">
            <div class="form-group">
                <label for="waist">Waist</label>
                <input type="text" id="waist" placeholder="Measurement in inches" required>
            </div>
            <div class="form-group">
                <label for="length">Length</label>
                <input type="text" id="length" placeholder="Measurement in inches" required>
            </div>
            <div class="form-group">
                <label for="inseam">Inseam</label>
                <input type="text" id="inseam" placeholder="Measurement in inches" required>
            </div>
            <div class="form-group">
                <label for="type">Type</label>
                <select id="type" required>
                    <option selected disabled hidden>Select type</option>
                    <option value="dress">Dress Pants</option>
                    <option value="casual">Casual Pants</option>
                    <option value="jeans">Jeans</option>
                    <option value="chinos">Chinos</option>
                </select>
            </div>
            <div class="form-group">
                <label for="status">Status</label>
                <select id="status">
                    <option selected disabled hidden>Select status</option>
                    <option value="pending">Pending</option>
                    <option value="in-progress">In Progress</option>
                    <option value="completed">Completed</option>
                </select>
            </div>
            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" min="1" value="1" required>
            </div>
        </div>
    </div>

    <div class="form-section">
        <div class="date-selectors">
            <div class="form-group date-selector">
                <label for="order-date">Order Date</label>
                <input type="date" id="order-date">
            </div>
            <div class="form-group date-selector">
                <label for="delivery-date">Delivery Date</label>
                <input type="date" id="delivery-date">
            </div>
        </div>
    </div>

    <div class="form-section">
        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" placeholder="Additional notes about the pants"></textarea>
        </div>

        <div class="action-buttons">
            <button class="btn btn-clear" type="reset">Clear</button>
            <button class="btn btn-save" type="submit">Save</button>
        </div>
    </div>

    </form>
</div>
</body>
</html>
