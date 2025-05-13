function validateForm(event) {

    const submitter = event.submitter;
    if (submitter && submitter.name === "action" && submitter.value === "next") {
        // Skip validation and submit the form
        return true;
    }

    event.preventDefault();

    const message = document.getElementById("message");

    const fields = [
        {id: "kameez-length", label: "Kameez Length"},
        {id: "chest", label: "Chest"},
        {id: "sleeve-length", label: "Sleeve Length"},
        {id: "shoulder", label: "Shoulder"},
        {id: "neck", label: "Neck"},
        {id: "trouser-length", label: "Trouser Length"},
        {id: "trouser-ankle", label: "Trouser Ankle"}
    ];

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


    // Create a hidden input to preserve the action value
    const hiddenInput = document.createElement('input');
    hiddenInput.type = 'hidden';
    hiddenInput.name = 'action';
    hiddenInput.value = submitter.value;
    event.target.appendChild(hiddenInput);
    // All validations passed, submit the form
    document.getElementById("kameez-shalwaar-form").submit();
}