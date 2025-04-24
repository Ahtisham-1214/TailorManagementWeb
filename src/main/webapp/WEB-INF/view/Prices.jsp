<%-- Created by IntelliJ IDEA. User: ahtis Date: 4/21/2025 Time: 6:18 PM To change this template use File | Settings |
    File Templates. --%>
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
    <title>Clothing Rates - Admin Panel</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        .container {
            max-width: 900px;
            margin: 0 auto;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .header {
            background-color: #2c3e50;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .header h1 {
            margin: 0;
            font-size: 24px;
            font-weight: 600;
        }

        .save-container {
            padding: 15px 20px;
            background-color: #f8f9fa;
            border-top: 1px solid #e0e0e0;
            text-align: right;
        }

        button {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: 600;
            transition: all 0.2s;
        }

        .save-btn {
            background-color: #2ecc71;
            color: white;
        }

        .save-btn:hover {
            background-color: #27ae60;
        }

        .save-btn:disabled {
            background-color: #95a5a6;
            cursor: not-allowed;
        }

        .edit-btn {
            background-color: #3498db;
            color: white;
            padding: 5px 10px;
            font-size: 12px;
        }

        .edit-btn:hover {
            background-color: #2980b9;
        }

        .rates-table {
            width: 100%;
            border-collapse: collapse;
        }

        .rates-table th,
        .rates-table td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }

        .rates-table th {
            background-color: #f8f9fa;
            font-weight: 600;
            color: #2c3e50;
        }

        .rates-table tr:last-child td {
            border-bottom: none;
        }

        .rates-table tr:hover {
            background-color: #f8f9fa;
        }

        .price-input {
            width: 80px;
            padding: 6px 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-family: inherit;
            text-align: right;
            background-color: #f5f5f5;
            pointer-events: none;
        }

        .price-input.editable {
            background-color: white;
            pointer-events: auto;
            border-color: #3498db;
        }

        .price-input.invalid {
            border-color: #e74c3c;
            background-color: #ffebee;
        }

        .price-input:focus {
            outline: none;
            box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
        }

        .changed {
            background-color: #e8f5e9;
        }

        .error-message {
            color: #e74c3c;
            font-size: 12px;
            margin-top: 5px;
            display: none;
        }

        .disclaimer {
            padding: 15px;
            background-color: #fff8e1;
            border-top: 1px dashed #ffc107;
            font-size: 14px;
            color: #e65100;
        }

        .footer {
            padding: 15px;
            text-align: center;
            background-color: #f8f9fa;
            color: #7f8c8d;
            font-size: 12px;
        }

        @media (max-width: 600px) {

            .rates-table th,
            .rates-table td {
                padding: 10px;
            }

            .save-container {
                text-align: center;
            }
        }
    </style>
</head>

<body>
<div class="container">
    <div class="header">
        <h1>Clothing Items Price List</h1>
    </div>
    <!-- Debug output -->
    <c:if test="${not empty shirtPrice}">
        <script>console.log("Prices loaded:", {
            shirt: ${ shirtPrice },
            pant: ${ pantPrice }
        });</script>
    </c:if>
    <form id="priceForm" action="PricesServlet" method="post">
        <table class="rates-table">
            <thead>
            <tr>
                <th>Item</th>
                <th>Price (RS)</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Shirt</td>
                <td>
                    <input type="number" name="shirtPrice" class="price-input"
                           value="${shirtPrice}"
                           data-original="${shirtPrice}" min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            <tr>
                <td>Coat</td>
                <td>
                    <input type="number" name="coatPrice" class="price-input"
                           value="${coatPrice}"
                           data-original="${coatPrice}"
                           min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            <tr>
                <td>Kameez Shalwaar</td>
                <td>
                    <input type="number" name="kameezShalwaarPrice" class="price-input"
                           value="${kameezShalwaarPrice}"
                           data-original="${kameezShalwaarPrice}"
                           min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            <tr>
                <td>Pant</td>
                <td>
                    <input type="number" name="pantPrice" class="price-input"
                           value="${pantPrice}"
                           data-original="${pantPrice}"
                           min="1">
                    <div class="error-message">Price must be at least 1 Rs</div>
                </td>
                <td>
                    <button type="button" class="edit-btn">Edit</button>
                </td>
            </tr>
            </tbody>
        </table>

        <div class="save-container">
            <button type="submit" class="save-btn" id="saveBtn">Update Prices</button>
            <div id="loadingIndicator" style="display:none;">Saving...</div>
        </div>
    </form>
    <div class="disclaimer">
        <strong>Important:</strong> All prices shown are current but subject to change.
        Changes made here will update the public price list.
    </div>

    <div class="footer">
        <p>For assistance with price updates, contact IT support</p>
        <p id="saveStatus"></p>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const priceInputs = document.querySelectorAll('.price-input');
        const saveBtn = document.getElementById('saveBtn');
        const saveStatus = document.getElementById('saveStatus');
        const editButtons = document.querySelectorAll('.edit-btn');
        const errorMessages = document.querySelectorAll('.error-message');

        // Minimum price validation
        const MIN_PRICE = 1;

        document.getElementById('priceForm').addEventListener('submit', function (e) {
            if (!checkChanges()) {
                e.preventDefault(); // Prevent form submission if validation fails
                saveStatus.textContent = 'No changes to save';
                saveStatus.style.color = '#e74c3c';
                setTimeout(() => {
                    saveStatus.textContent = '';
                }, 3000);
                return;
            }

            // Show loading animation
            saveBtn.disabled = true;
            document.getElementById('loadingIndicator').style.display = 'block';
            saveStatus.textContent = 'Price Updated Successfully';
            saveStatus.style.color = '#2ecc71';

            // Reset editable fields
            priceInputs.forEach((input, index) => {
                input.dataset.original = input.value;
                input.classList.remove('editable');
                editButtons[index].textContent = 'Edit';
            });

            // Let the form submit naturally after a small delay if you want animations first
            // Otherwise, remove this `setTimeout` to submit immediately
            // (This part is optional and mostly aesthetic)
            setTimeout(() => {
                saveStatus.textContent = 'Prices updated successfully!';
                saveStatus.style.color = '#2ecc71';
                document.getElementById('loadingIndicator').style.display = 'none';

                setTimeout(() => {
                    saveStatus.textContent = '';
                }, 3000);
            }, 1000);
        });



        // Toggle edit mode for each row
        editButtons.forEach((button, index) => {
            button.addEventListener('click', function () {
                const input = priceInputs[index];
                const isEditing = !input.classList.contains('editable');

                if (isEditing) {
                    // Entering edit mode
                    input.classList.add('editable');
                    button.textContent = 'Cancel';
                    input.focus();
                } else {
                    // Canceling edit mode
                    input.classList.remove('editable');
                    input.classList.remove('invalid');
                    errorMessages[index].style.display = 'none';
                    button.textContent = 'Edit';
                    input.value = input.dataset.original;
                }

                validateInput(input, index);
                checkChanges();
            });
        });

        // Validate individual input
        function validateInput(input, index) {
            const value = parseFloat(input.value);
            const errorMessage = errorMessages[index];

            if (isNaN(value) || value < MIN_PRICE) {
                input.classList.add('invalid');
                errorMessage.style.display = 'block';
                return false;
            } else {
                input.classList.remove('invalid');
                errorMessage.style.display = 'none';
                return true;
            }
        }

        // Check for changes and validate all inputs
        function checkChanges() {
            let hasChanges = false;
            let isValid = true;

            priceInputs.forEach((input, index) => {
                const row = input.closest('tr');
                const isChanged = input.value !== input.dataset.original;

                if (input.classList.contains('editable')) {
                    isValid = validateInput(input, index) && isValid;
                }

                if (isChanged) {
                    row.classList.add('changed');
                    hasChanges = true;
                } else {
                    row.classList.remove('changed');
                }
            });

            // Enable/disable save button based on validation and changes
            saveBtn.disabled = !hasChanges || !isValid;
            return hasChanges && isValid;
        }


        // Validate on input change
        priceInputs.forEach((input, index) => {
            input.addEventListener('input', function () {
                validateInput(input, index);
                checkChanges();
            });

            // Also validate when losing focus
            input.addEventListener('blur', function () {
                validateInput(input, index);
                checkChanges();
            });
        });
    });
</script>
</body>

</html>