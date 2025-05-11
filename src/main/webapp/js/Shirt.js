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


    if (!neckField.value.trim()) {
        highlightField(neckField);
        showMessage("Neck is Required");
        neckField.focus();
        return false;
    } else {
        resetFieldHighlight(neckField);
    }

    if (!chestField.value.trim()) {
        highlightField(chestField);
        showMessage("Chest is Required");
        chestField.focus();
        return false;
    } else {
        resetFieldHighlight(chestField);
    }

    if (!shoulderField.value.trim()) {
        highlightField(shoulderField);
        showMessage("Shoulder is Required");
        shoulderField.focus();
        return false;
    } else {
        resetFieldHighlight(shoulderField);
    }

    if (!shirtLengthField.value.trim()) {
        highlightField(shirtLengthField);
        showMessage("Shirt Length is Required");
        shirtLengthField.focus();
        return false;
    } else {
        resetFieldHighlight(shirtLengthField);
    }

    if (!sleevesLengthField.value.trim()) {
        highlightField(sleevesLengthField);
        showMessage("Sleeves Length is Required");
        sleevesLengthField.focus();
        return false;
    } else {
        resetFieldHighlight(sleevesLengthField);
    }

    if (!quantityField.value.trim()) {
        highlightField(quantityField);
        showMessage("Quantity is Required");
        quantityField.focus();
        return false;
    } else {
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