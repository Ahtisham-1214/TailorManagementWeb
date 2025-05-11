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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Shirt.css">


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
<script src="${pageContext.request.contextPath}/js/Shirt.js"></script>
</body>

</html>