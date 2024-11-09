// Переключение между регистрацией/авторизацией
document.addEventListener('DOMContentLoaded', () => {
    const signInButton = document.getElementById('signIn');
    const signUpButton = document.getElementById('signUp');
    const container = document.getElementById('container');
    const signUpForm = document.querySelector('.form-container.sign-up-container form');
    const signInForm = document.querySelector('.form-container.sign-in-container form');

    signInButton.addEventListener('click', () => {
        container.classList.remove('right-panel-active');
        signUpForm.reset();
    });

    signUpButton.addEventListener('click', () => {
        container.classList.add('right-panel-active');
        signInForm.reset();
    });
});

// Видимость пароля
function togglePasswordVisibility() {
    const passwordInput = document.getElementById('password');
    const toggleCheckbox = document.getElementById('toggle-password');
    
    if (toggleCheckbox.checked) {
        passwordInput.type = 'text';
    } else {
        passwordInput.type = 'password';
    }
}

// Закрытие окна авторизации/регистрации
function toggleModal() {
    const modal = document.getElementById("authModal");
    modal.style.display = modal.style.display === "flex" ? "none" : "flex";
}

function clearInput() {
    document.getElementById('input').value = ''; // Очищает поле ввода
    document.getElementById('input').focus(); // Устанавливает фокус обратно на поле ввода
}
