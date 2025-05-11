function validateForm(event) {
    const submitter = event.submitter;
    if (submitter && submitter.name === "action" && submitter.value === "next") {
        // Skip validation and submit the form
        return true;
    }

    event.preventDefault();
    const waistField = document.getElementById("waist");
    const lengthField = document.getElementById("length");
    const inseamField = document.getElementById("inseam");
    const quantity = document.getElementById("quantity");
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

    if (!waistField.value.trim()) {
        highlightField(waistField);
        showMessage("Waist is Required");
        waistField.focus();
        return false;
    } else {
        resetFieldHighlight(waistField);
    }

    if (!lengthField.value.trim()) {
        highlightField(lengthField);
        showMessage("Length is Required");
        lengthField.focus();
        return false;
    } else {
        resetFieldHighlight(lengthField);
    }

    if (!inseamField.value.trim()) {
        highlightField(inseamField);
        showMessage("Inseam is Required");
        inseamField.focus();
        return false;
    } else {
        resetFieldHighlight(inseamField);
    }

    if (!quantity.value.trim()) {
        highlightField(quantity);
        showMessage("Quantity is Required");
        quantity.focus();
        return false;
    } else {
        resetFieldHighlight(quantity);
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

    document.getElementById("pant-form").submit();

}