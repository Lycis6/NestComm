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

// Закрытие окна авторизации/регистрации и очистка полей форм
function toggleModal() {
    const modal = document.getElementById("authModal");

    // Очистка всех полей ввода
    // const forms = modal.querySelectorAll("form"); // Найдем все формы в модальном окне
    // forms.forEach(form => {
    //     const inputs = form.querySelectorAll("input"); // Найдем все input в форме
    //     inputs.forEach(input => input.value = ""); // Очищаем значения полей
    //     const checkboxes = form.querySelectorAll("input[type='checkbox']"); // Найдем все чекбоксы
    //     checkboxes.forEach(checkbox => checkbox.checked = false); // Снимаем отметку с чекбоксов
    // });

    // Переключаем отображение модального окна
    modal.style.display = modal.style.display === "flex" ? "none" : "flex";
}

function clearInput() {
    document.getElementById('input').value = ''; // Очищает поле ввода
    document.getElementById('input').focus(); // Устанавливает фокус обратно на поле ввода
}

function paymentModal() {
    const modal = document.getElementById("paymentModal");

    // Переключаем отображение модального окна
    modal.style.display = modal.style.display === "flex" ? "none" : "flex";
}

function createModal() {
    const modal = document.getElementById("createModal");

    // Переключаем отображение модального окна
    modal.style.display = modal.style.display === "flex" ? "none" : "flex";
}

function addFavourite(button) {
    // Здесь мы получаем родительский элемент для текущей кнопки
    const heartIconContainer = button.closest('.favorite-icon');

    // Переключаем класс 'active' для изменения состояния
    heartIconContainer.classList.toggle('active');
}
