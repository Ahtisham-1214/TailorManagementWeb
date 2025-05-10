const form = document.querySelector('form');
const saveStatus = document.getElementById('saveStatus');

// Store original values on load
const inputs = document.querySelectorAll('input');
inputs.forEach(input => {
    input.dataset.originalValue = input.value;
});

// Toggle edit/cancel button
document.querySelectorAll('.edit-btn').forEach(button => {
    button.addEventListener('click', function () {
        const input = this.parentElement.querySelector('input');

        if (input.hasAttribute('readonly')) {
            input.dataset.originalValue = input.value;
            input.removeAttribute('readonly');
            input.focus();
            input.select();
            this.textContent = 'Cancel';
        } else {
            input.value = input.dataset.originalValue;
            input.setAttribute('readonly', true);
            this.textContent = 'Edit';
        }
    });
});

// Check for changes
function checkChanges() {
    return Array.from(inputs).some(input => input.value !== input.dataset.originalValue);
}

// Form submit handler
form.addEventListener('submit', function (e) {
    if (!checkChanges()) {
        e.preventDefault();
        saveStatus.textContent = 'No changes to save';
        saveStatus.style.color = '#e74c3c';

        setTimeout(() => {
            saveStatus.textContent = '';
        }, 3000);
        return;
    }

    // Simulate saving
    // e.preventDefault(); // If you don’t want to actually submit
    saveStatus.textContent = '';

    setTimeout(() => {
        saveStatus.textContent = 'Information updated successfully!';
        saveStatus.style.color = '#2ecc71';

        // Reset state
        inputs.forEach(input => {
            input.dataset.originalValue = input.value;
            input.setAttribute('readonly', true);

            const btn = input.parentElement.querySelector('.edit-btn');
            if (btn) btn.textContent = 'Edit';
        });

        setTimeout(() => {
            saveStatus.textContent = '';
        }, 3000);
    }, 1000); // Simulated delay — you can reduce/remove this if desired
});