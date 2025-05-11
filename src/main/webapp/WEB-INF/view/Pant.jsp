<%--
  Created by IntelliJ IDEA.
  User: ahtis
  Date: 4/29/2025
  Time: 11:30 AM
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
    <title>Pant Order Form</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Pant.css">
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

    <form id="pant-form" onsubmit="return validateForm(event)" action="PantServlet" method="post">
        <div class="form-section">
            <h2 class="section-title">Measurements</h2>

            <div class="measurements-grid">
                <div class="form-group">
                    <label for="waist">Waist</label>
                    <input type="text" id="waist" name="waist" placeholder="Measurement in inches" required>
                </div>
                <div class="form-group">
                    <label for="length">Length</label>
                    <input type="text" id="length" name="length" placeholder="Measurement in inches" required>
                </div>
                <div class="form-group">
                    <label for="inseam">Inseam</label>
                    <input type="text" id="inseam" name="inseam" placeholder="Measurement in inches" required>
                </div>
                <div class="form-group">
                    <label for="type">Type</label>
                    <select id="type" name="type" required>
                        <option selected disabled hidden>Select type</option>
                        <option value="1">Dress Pants</option>
                        <option value="2">Casual Pants</option>
                        <option value="3">Jeans</option>
                        <option value="4">Chinos</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option selected disabled hidden>Select status</option>
                        <option value="1">Pending</option>
                        <option value="2">In Progress</option>
                        <option value="3">Completed</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input type="number" id="quantity" name="quantity" min="1" value="1" required>
                </div>
            </div>
        </div>

        <div class="form-section">
            <h2 class="section-title">Order Details</h2>
            <div class="date-selectors">
                <div class="form-group date-selector">
                    <label for="order-date">Order Date</label>
                    <input type="date" id="order-date" name="order-date">
                </div>
                <div class="form-group date-selector">
                    <label for="delivery-date">Delivery Date</label>
                    <input type="date" id="delivery-date" name="delivery-date">
                </div>
            </div>
        </div>

        <div class="form-section">
            <h2 class="section-title">Additional Information</h2>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" placeholder="Additional notes about the pants"></textarea>
            </div>

            <div class="action-buttons">
                <button class="btn btn-clear" type="reset">Clear</button>
                <button class="btn btn-save" name="action" value="save" type="submit">Save</button>
                <button class="btn btn-generate" name="action" value="generate" type="submit">Generate Receipt</button>
                <button class="btn btn-next" name="action" value="next" type="submit" formnovalidate>Next</button>

            </div>
        </div>

    </form>
</div>
<script src="${pageContext.request.contextPath}/js/Pant.js"></script>
</body>
</html>
