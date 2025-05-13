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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/KameezShalwaar.css">
</head>
<body>
<div class="form-container">
    <div class="form-header">Shalwar Kameez Order</div>

    <div id="message" class="message <%=
    (message != null && !message.isEmpty())
    ? (message.toLowerCase().contains("success") ? "success" : "error")
    : ""%>">
        <%= (message != null) ? message : "" %>
    </div>

    <form id="kameez-shalwaar-form" onsubmit="return validateForm(event)" action="KameezShalwaarServlet" method="post">
        <!-- Measurements Section -->
        <div class="form-section">
            <div class="section-title">Measurements</div>
            <div class="form-row">
                <div class="form-group">
                    <label for="kameez-length">Kameez Length</label>
                    <input type="text" id="kameez-length" name="kameez-length" placeholder="Measurements in inches" required>
                </div>

                <div class="form-group">
                    <label for="chest">Chest</label>
                    <input type="text" id="chest" name="chest" placeholder="Measurements in inches" required>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="sleeve-length">Sleeve Length</label>
                    <input type="text" id="sleeve-length" name="sleeve-length" placeholder="Measurements in inches" required>
                </div>
                <div class="form-group">
                    <label for="shoulder">Shoulder</label>
                    <input type="text" id="shoulder" name="shoulder" placeholder="Measurements in inches" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="neck">Neck</label>
                    <input type="text" id="neck" name="neck" placeholder="Measurements in inches" required>
                </div>
                <div class="form-group">
                    <label for="cuff-type">Cuff Type</label>
                    <select id="cuff-type" name="cuff-type" required>
                        <option disabled selected hidden>Select Cuff Type</option>
                        <option value="1">Round</option>
                        <option value="2">Square</option>
                    </select>
                </div>

            </div>
            <div class="form-row">
                <div class="form-group">
                    <label for="kameez-type">Kameez Type</label>
                    <select id="kameez-type" name="kameez-type" required>
                        <option disabled selected hidden>Select Kameez Type</option>
                        <option value="1">Square</option>
                        <option value="2">Oval</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="collar-type">Collar Type</label>
                    <select id="collar-type" name="collar-type" required>
                        <option disabled selected hidden>Select Collar Type</option>
                        <option value="1">French</option>
                        <option value="2">Sherwani</option>
                        <option value="3">Cooper</option>
                    </select>
                </div>

            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="trouser-length">Trouser Length</label>
                    <input type="text" id="trouser-length" name="trouser-length" placeholder="Measurements in inches" required>
                </div>


                <div class="form-group">
                    <label for="trouser-ankle">Trouser Ankle</label>
                    <input type="text" id="trouser-ankle" name="trouser-ankle" placeholder="Measurements in inches" required>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label for="trouser-type">Trouser Type</label>
                    <select id="trouser-type" name="trouser-type" style="width: 44%;" required>
                        <option disabled selected hidden>Select Trouser Type</option>
                        <option value="1">Shalwaar</option>
                        <option value="2">Pajama</option>
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
                    <input type="number" id="quantity" name="quantity" min="1" value="1" placeholder="Enter Quantity" required>
                </div>
                <div class="form-group">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option disabled selected hidden>Select Status</option>
                        <option value="1">Pending</option>
                        <option value="2">In Progress</option>
                        <option value="3">Completed</option>
                        <option value="4">Delivered</option>
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
                    <label for="description">Special Instructions</label>
                    <textarea id="description" name="description" placeholder="Enter any special instructions here..."></textarea>
                </div>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="form-section">
            <div class="action-buttons">
                <button class="btn btn-clear" type="reset">Clear</button>
                <button class="btn btn-save" name="action" type="submit" value="save">Save</button>
                <button class="btn btn-generate" name="action" type="submit" value="generate">Generate Receipt</button>

            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/KameezShalwaar.js"></script>
</body>
</html>
