<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/29/2025
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
<STYLE>
    :root {
        --primary-color: #2c3e50;
        --secondary-color: #34495e;
        --accent-color: #3498db;
        --light-gray: #ecf0f1;
        --dark-gray: #7f8c8d;
        --border-color: #bdc3c7;
        --success-color: #2ecc71;
        --error-color: #e74c3c;
        --divider-color: #eee;
    }

    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        line-height: 1.6;
        color: #333;
        background-color: #f9f9f9;
        padding: 20px;
        margin: 0;
        display: flex;
        justify-content: center;
        min-height: 100vh;
    }

    .form-container {
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        width: 100%;
        max-width: 800px;
    }

    .form-header {
        background-color: var(--primary-color);
        color: white;
        padding: 15px 20px;
        font-size: 24px;
        font-weight: 600;
        margin: 0;
    }

    .form-section {
        padding: 20px;
    }

    .section-title {
        color: var(--secondary-color);
        margin-top: 0;
        margin-bottom: 20px;
        font-size: 18px;
        font-weight: 600;
    }

    .form-row {
        display: flex;
        gap: 15px;
        margin-bottom: 15px;
    }

    .form-field {
        flex: 1;
    }

    .form-field label {
        display: block;
        margin-bottom: 5px;
        font-weight: 500;
        color: var(--secondary-color);
    }

    .form-field input,
    .form-field select,
    .form-field textarea {
        width: 100%;
        padding: 10px 12px;
        border: 1px solid var(--border-color);
        border-radius: 4px;
        font-size: 14px;
        box-sizing: border-box;
        transition: border-color 0.3s, box-shadow 0.3s;
    }

    .form-field textarea {
        min-height: 100px;
        resize: vertical;
    }

    .form-field input:focus,
    .form-field select:focus,
    .form-field textarea:focus {
        outline: none;
        border-color: var(--accent-color);
        box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
    }

    .divider {
        height: 1px;
        background-color: var(--divider-color);
        margin: 0 20px;
    }

    .button-group {
        display: flex;
        justify-content: flex-end;
        gap: 10px;
        padding: 20px;
        border-top: 1px solid var(--border-color);
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

    .btn-generate {
        background-color: var(--success-color);
        color: white;
    }

    .btn-generate:hover {
        background-color: #27ae60;
    }

    .btn-next {
        background-color: #2b3a4a;
        color: white;
    }

    .btn-next:hover {
        background-color: #1a252f;
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

    @media (max-width: 600px) {
        .form-row {
            flex-direction: column;
            gap: 10px;
        }

        .button-group {
            flex-wrap: wrap;
            justify-content: center;
        }

        .btn {
            flex: 1;
            min-width: 120px;
        }
    }
</STYLE>

    <script>

        // Function to validate the delivery date
        function validateForm(event) {
            const submitter = event.submitter;
            if (submitter && submitter.name === "action" && submitter.value === "next") {
                // Skip validation and submit the form
                return true;
            }

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

            // Create a hidden input to preserve the action value
            const hiddenInput = document.createElement('input');
            hiddenInput.type = 'hidden';
            hiddenInput.name = 'action';
            hiddenInput.value = submitter.value;
            event.target.appendChild(hiddenInput);

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
            <button type="submit" class="btn btn-next" name="action" value="next" formnovalidate>Next</button>
        </div>
    </form>
</div>
</body>

</html>