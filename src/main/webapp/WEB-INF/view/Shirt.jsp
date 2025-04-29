<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/29/2025
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session == null || session.getAttribute("user") == null) {
        response.sendRedirect("LoginServlet");
        return;
    }

    String message = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shirt Order Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            color: #333;
        }

        h1 {
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }

        .section-title {
            color: #2980b9;
            margin: 20px 0 15px 0;
        }

        .form-header {
            background-color: var(--primary-color);
            color: white;
            padding: 15px 20px;
            font-size: 24px;
            font-weight: 600;
        }

        .form-container {
            max-width: 800px;
            margin: 0 auto;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-section {
            margin-bottom: 20px;
        }

        .form-row {
            display: flex;
            margin-bottom: 15px;
        }

        .form-field {
            flex: 1;
            margin-right: 15px;
        }

        .form-field:last-child {
            margin-right: 0;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="date"],
        input[type="number"],
        select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .divider {
            border-top: 1px solid #ddd;
            margin: 20px 0;
        }

        .button-group {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
        }

        .button {
            padding: 8px 15px;
            margin-left: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }

        .button-clear {
            background-color: #e74c3c;
            color: white;
        }

        .button-save {
            background-color: #2ecc71;
            color: white;
        }

        .button:hover {
            opacity: 0.9;
        }

        .message {
            display: none;
            text-align: center;
            font-weight: bold;
            margin-bottom: 16px;
            opacity: 0;
            transition: opacity 0.4s ease;
        }

        .message.success {
            color: green;
            opacity: 1;
            display: block;
        }

        .message.error {
            color: red;
            opacity: 1;
            display: block;
        }


    </style>

    <script>

        // Function to validate the delivery date
        function validateForm(event) {
            event.preventDefault();
            const neckField = document.getElementById("neck");
            const chestField = document.getElementById("chest");
            const shirtLengthField = document.getElementById("shirt-length");
            const shoulderField = document.getElementById("shoulder");
            const sleevesLengthField = document.getElementById("sleeve-length");
            const quantityField = document.getElementById("quantity");
            const cuffType = document.getElementById("cuff-type");
            const collarType = document.getElementById("collar-type");
            const orderDate = document.getElementById("order-date").value;
            const deliveryDate = document.getElementById("delivery-date").value;
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


            if (!neckField.value.trim()){
                highlightField(neckField);
                showMessage("Neck is Required");
                neckField.focus();
                return false;
            }else {
                resetFieldHighlight(neckField);
            }

            if (!chestField.value.trim()){
                highlightField(chestField);
                showMessage("Chest is Required");
                chestField.focus();
                return false;
            }else {
                resetFieldHighlight(chestField);
            }

            if (!shoulderField.value.trim()){
                highlightField(shoulderField);
                showMessage("Shoulder is Required");
                shoulderField.focus();
                return false;
            }else {
                resetFieldHighlight(shoulderField);
            }

            if (!shirtLengthField.value.trim()){
                highlightField(shirtLengthField);
                showMessage("Shirt Length is Required");
                shirtLengthField.focus();
                return false;
            }else {
                resetFieldHighlight(shirtLengthField);
            }

            if (!sleevesLengthField.value.trim()){
                highlightField(sleevesLengthField);
                showMessage("Sleeves Length is Required");
                sleevesLengthField.focus();
                return false;
            }else {
                resetFieldHighlight(sleevesLengthField);
            }

            if (!quantityField.value.trim()){
                highlightField(quantityField);
                showMessage("Quantity is Required");
                quantityField.focus();
                return false;
            }else {
                resetFieldHighlight(quantityField);
            }


            if (orderDate && deliveryDate && new Date(deliveryDate) <= new Date(orderDate)) {
                showMessage("Delivery date must be after the order date");
                return false;
            }

            document.getElementById("shirt-form").submit();
        }
    </script>

</head>

<body>
<div class="form-container">
    <div class="form-header">Shirt Form</div>


    <div id="message" class="message <%=
    (message != null && !message.isEmpty())
    ? (message.toLowerCase().contains("success") ? "success" : "error")
    : ""%>" aria-live="polite">
        <%= (message != null) ? message : "" %>
    </div>

    <form id="shirt-form" onsubmit="return validateForm(event)" action="ShirtServlet" method="post">
        <div class="form-section">
            <h2 class="section-title">Measurements</h2>

            <div class="form-row">
                <div class="form-field">
                    <label for="neck">Neck</label>
                    <input type="text" id="neck" name="neck" placeholder="Measurements in Inches" required>
                </div>

                <div class="form-field">
                    <label for="chest">Chest</label>
                    <input type="text" id="chest" name="chest" placeholder="Measurements in Inches" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-field">
                    <label for="shoulder">Shoulder</label>
                    <input type="text" id="shoulder" name="shoulder" placeholder="Measurements in Inches" required>
                </div>

                <div class="form-field">
                    <label for="shirt-length">Shirt Length</label>
                    <input type="text" id="shirt-length" name="shirt-length" placeholder="Measurements in Inches"
                           required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-field">
                    <label for="sleeve-length">Sleeve Length</label>
                    <input type="text" id="sleeve-length" name="sleeve-length" placeholder="Measurements in Inches"
                           required>
                </div>
                <div class="form-field">
                    <label for="cuff-type">Cuff Type</label>
                    <select id="cuff-type" name="cuff-type" required>
                        <option disabled selected hidden>Select Cuff Type</option>
                        <option value="1">Half Sleeves</option>
                        <option value="2">Square</option>
                        <option value="3">Round</option>
                    </select>
                </div>

            </div>

            <div class="form-row">
                <div class="form-field">
                    <label for="collar-type">Collar Type</label>
                    <select id="collar-type" name="collar-type" style="width: 49%;" required>
                        <option disabled selected hidden>Select Collar Type</option>
                        <option value="1">Classic</option>
                        <option value="2">Standard</option>
                        <option value="3">Cooper</option>
                    </select>
                </div>
            </div>

        </div>

        <div class="divider"></div>

        <div class="form-section">
            <h2 class="section-title">Order Details</h2>


            <div class="form-row">
                <div class="form-field">
                    <label for="quantity">Quantity</label>
                    <input type="number" id="quantity" name="quantity" min="1" value="1"
                           placeholder="Number of Shirts" required>
                </div>

                <div class="form-field">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option disabled selected hidden>Select Status</option>
                        <option value="1">Pending</option>
                        <option value="2">Progress</option>
                        <option value="3">Completed</option>
                        <option value="4">Delivered</option>
                    </select>
                </div>
            </div>

            <div class="form-row">
                <div class="form-field">
                    <label for="order-date">Order Date</label>
                    <input type="date" id="order-date" name="order-date">
                </div>

                <div class="form-field">
                    <label for="delivery-date">Delivery Date</label>
                    <input type="date" id="delivery-date" name="delivery-date">
                </div>
            </div>
        </div>

        <div class="divider"></div>

        <div class="form-section">
            <h2 class="section-title">Additional Information</h2>
            <div class="form-row">
                <div class="form-field">
                    <label for="description">Special Instructions</label>
                    <textarea id="description" name="description" rows="4" style="width: 100%;"></textarea>
                </div>
            </div>
        </div>

        <div class="button-group">
            <button type="reset" class="btn btn-clear">Clear</button>
            <button type="submit" class="btn btn-save" name="action" value="save">Save</button>
            <button type="submit" class="btn btn-generate" name="action" value="generate">Generate Receipt</button>
            <button type="button" class="button button-next">Next</button>
        </div>
    </form>
</div>
</body>

</html>