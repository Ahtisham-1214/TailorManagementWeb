<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/26/2025
  Time: 5:59 PM
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
    <title>Kameez Shalwaar Order Form</title>
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
            padding: 20px;
            margin: 0 auto;
            max-width: 800px;
            box-sizing: border-box;
        }

        .form-container {
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 100%;
            box-sizing: border-box;
        }

        .form-header {
            background-color: var(--primary-color);
            color: white;
            padding: 15px 20px;
            font-size: 24px;
            font-weight: 600;
        }

        .form-section {
            padding: 15px 20px;
            border-bottom: 1px solid var(--border-color);
            box-sizing: border-box;
        }

        .section-title {
            color: var(--secondary-color);
            font-size: 18px;
            margin-bottom: 12px;
            font-weight: 600;
            width: 100%;
        }

        .form-row {
            display: flex;
            justify-content: space-between;
            gap: 20px;
        }

        .form-row .form-group {
            flex: 1;
        }

        .form-group {
            margin-bottom: 12px;
            padding-left: 20px;
            position: relative;
            width: calc(100% - 20px);
            box-sizing: border-box;
        }

        .form-group:before {
            position: absolute;
            left: 0;
            color: var(--dark-gray);
            font-size: 14px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 500;
            color: var(--secondary-color);
            width: 100%;
            font-size: 14px;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            width: calc(100% - 24px);
            padding: 8px 12px;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }

        .form-group textarea {
            min-height: 80px;
            resize: vertical;
        }

        .form-group select {
            appearance: none;
            background-image: url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%2334495e' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
            background-repeat: no-repeat;
            background-position: right 10px center;
            background-size: 12px;
        }

        .action-buttons {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 15px;
            width: 100%;
        }

        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            font-weight: 500;
            cursor: pointer;
            font-size: 14px;
        }

        .btn-clear {
            background-color: var(--light-gray);
            color: var(--secondary-color);
        }

        .btn-save {
            background-color: var(--accent-color);
            color: white;
        }

        .btn-generate{
            background-color: #2ecc71 ;
            color: white;
        }

        @media (max-width: 600px) {
            body {
                padding: 10px;
            }

            .form-section {
                padding: 15px;
            }

            .form-group {
                width: calc(100% - 15px);
            }
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
        function validateForm(event) {
            event.preventDefault();

            const message = document.getElementById("message");

            const fields = [
                { id: "kameez-length", label: "Kameez Length" },
                { id: "chest", label: "Chest" },
                { id: "sleeve-length", label: "Sleeve Length" },
                { id: "shoulder", label: "Shoulder" },
                { id: "neck", label: "Neck" },
                { id: "shalwar-length", label: "Shalwar Length" },
                { id: "shalwar-ankle", label: "Shalwar Ankle" }
            ];

            if (message) {
                message.style.display = "none";
            }

            function showMessage(text, type = "error") {
                if (message) {
                    message.textContent = text;
                    message.className = "message " + type + " show";
                    message.style.display = "block";
                }
            }

            function highlightField(field) {
                field.style.border = "2px solid red";
            }

            function resetFieldHighlight(field) {
                field.style.border = "1px solid var(--border-color)";
            }

            for (const fieldData of fields) {
                const field = document.getElementById(fieldData.id);
                if (!field.value.trim()) {
                    highlightField(field);
                    showMessage(fieldData.label + " is required");
                    field.focus();
                    return false;
                } else {
                    resetFieldHighlight(field);
                }
            }

            const orderDate = document.getElementById("order-date").value;
            const deliveryDate = document.getElementById("delivery-date").value;

            if (orderDate && deliveryDate && new Date(deliveryDate) <= new Date(orderDate)) {
                showMessage("Delivery date must be after the order date.");
                return false;
            }

            // All validations passed, submit the form
            document.getElementById("kameezShalwaarForm").submit();
        }

    </script>
</head>
<body>
<div class="form-container">
    <div class="form-header">Shalwar Kameez Order</div>

    <div id="message" class="message <%=
    (message != null && !message.isEmpty())
    ? (message.toLowerCase().contains("success") ? "success" : "error")
    : ""%>" >
        <%= (message != null) ? message : "" %>
    </div>

    <form id="kameez-shalwaar-form" onsubmit="return validateForm(event)" action="KameezShalwaarServlet" method="post">
        <!-- Measurements Section -->
        <div class="form-section">
            <div class="section-title">Measurements</div>
            <div class="form-row">
                <div class="form-group">
                    <label for="kameez-length">Kameez Length</label>
                    <input type="text" id="kameez-length" placeholder="Measurements in inches" required>
                </div>

                <div class="form-group">
                    <label for="chest">Chest</label>
                    <input type="text" id="chest" placeholder="Measurements in inches" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="sleeve-length">Sleeve Length</label>
                    <input type="text" id="sleeve-length" placeholder="Measurements in inches" required>
                </div>
                <div class="form-group">
                    <label for="shoulder">Shoulder</label>
                    <input type="text" id="shoulder" placeholder="Measurements in inches" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="neck">Neck</label>
                    <input type="text" id="neck" placeholder="Measurements in inches" required>
                </div>
                <div class="form-group">
                    <label for="cuff-type">Cuff Type</label>
                    <select id="cuff-type" required>
                        <option disabled selected hidden>Select Cuff Type</option>
                        <option value="round">Round</option>
                        <option value="square">Square</option>
                        <option value="angled">Angled</option>
                    </select>
                </div>

            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="kameez-type">Kameez Type</label>
                    <select id="kameez-type" required>
                        <option disabled selected hidden>Select Kameez Type</option>
                        <option value="straight">Straight</option>
                        <option value="a-line">A-Line</option>
                        <option value="angrakha">Angrakha</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="collar-type">Collar Type</label>
                    <select id="collar-type" required>
                        <option disabled selected hidden>Select Collar Type</option>
                        <option value="round">Round</option>
                        <option value="mandarin">Mandarin</option>
                        <option value="v-neck">V-Neck</option>
                    </select>
                </div>

            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="shalwar-length">Shalwar Length</label>
                    <input type="text" id="shalwar-length" placeholder="Measurements in inches" required>
                </div>


                <div class="form-group">
                    <label for="shalwar-ankle">Shalwar Ankle</label>
                    <input type="text" id="shalwar-ankle" placeholder="Measurements in inches" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="shalwar-type">Shalwar Type</label>
                    <select id="shalwar-type" style="width: 44%;" required>
                        <option disabled selected hidden>Select Shalwar Type</option>
                        <option value="regular">Regular</option>
                        <option value="patiala">Patiala</option>
                        <option value="churidar">Churidar</option>
                    </select>
                </div>

            </div>


        </div>

        <!-- Order Details Section -->
        <div class="form-section">
            <div class="section-title">Order Details</div>
            <div class="form-row">
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input type="number" id="quantity" min="1" value="1" placeholder="Enter Quantity" required>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status">
                        <option disabled selected hidden>Select Status</option>
                        <option value="pending">Pending</option>
                        <option value="in-progress">In Progress</option>
                        <option value="completed">Completed</option>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="order-date">Order Date</label>
                    <input type="date" id="order-date">
                </div>
                <div class="form-group">
                    <label for="delivery-date">Delivery Date</label>
                    <input type="date" id="delivery-date">
                </div>
            </div>
        </div>

        <!-- Additional Information Section -->
        <div class="form-section">
            <div class="section-title">Additional Information</div>
            <div class="form-row">
                <div class="form-group">
                    <label for="instructions">Special Instructions</label>
                    <textarea id="instructions" placeholder="Enter any special instructions here..."></textarea>
                </div>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="form-section">
            <div class="action-buttons">
                <button class="btn btn-clear" type="reset">Clear</button>
                <button class="btn btn-save" type="submit">Save</button>
                <button class="btn btn-generate" name="action" type="submit" value="generate">Generate Receipt</button>

            </div>
        </div>
    </form>
</div>

</body>
</html>
