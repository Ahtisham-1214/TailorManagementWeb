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
