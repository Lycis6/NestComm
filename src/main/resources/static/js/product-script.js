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

    // Переключаем отображение модального окна
    modal.style.display = modal.style.display === "flex" ? "none" : "flex";
}


function clearInput() {
    document.getElementById('input').value = ''; // Очищает поле ввода
    document.getElementById('input').focus();
}


// Добавить в избранное (еще будет редактироваться)
function addFavourite(button) {
    const heartIconContainer = button.closest('.favorite-icon');
    heartIconContainer.classList.toggle('active');
}

function selectCategory(element) {
    const categoryName = element.getAttribute('data-category');
    
    if (element.classList.contains('selected')) {
        // Убираем выделение
        element.classList.remove('selected');
    } else {
        const categories = document.querySelectorAll('.category');
        categories.forEach(category => category.classList.remove('selected'));

        // Добавляем выделение
        element.classList.add('selected');
    }
}

// Значение цены
document.addEventListener('DOMContentLoaded', function () {
    const minPriceInput = document.querySelector('input[name="minprice"]');
    const maxPriceInput = document.querySelector('input[name="maxprice"]');
    const minRangeSlider = document.querySelector('.rangeslider .min');
    const maxRangeSlider = document.querySelector('.rangeslider .max');

    function updatePriceInputs() {
        minPriceInput.value = minRangeSlider.value;
        maxPriceInput.value = maxRangeSlider.value;

        if (parseInt(minPriceInput.value) > parseInt(maxPriceInput.value)) {
            minPriceInput.value = maxPriceInput.value;
            minRangeSlider.value = maxPriceInput.value;
        }
    }

    function updateRangeSliders() {
        minRangeSlider.value = minPriceInput.value;
        maxRangeSlider.value = maxPriceInput.value;

        if (parseInt(minRangeSlider.value) > parseInt(maxRangeSlider.value)) {
            minRangeSlider.value = maxRangeSlider.value;
            minPriceInput.value = maxRangeSlider.value;
        }
    }

    minPriceInput.addEventListener('input', updateRangeSliders);
    maxPriceInput.addEventListener('input', updateRangeSliders);

    minRangeSlider.addEventListener('input', updatePriceInputs);
    maxRangeSlider.addEventListener('input', updatePriceInputs);

    updatePriceInputs();
});

// Применить фильтры
function applyFilters() {
    const minPriceInput = document.querySelector('input[name="minprice"]');
    const maxPriceInput = document.querySelector('input[name="maxprice"]');

    const selectedCategoryElement = document.querySelector('.category.selected');
    const selectedCategory = selectedCategoryElement ? selectedCategoryElement.getAttribute('data-category') : null;
    console.log(`Выбранная категория: ${selectedCategory}`);

    console.log(`Минимальная цена: ${minPriceInput.value}`);
    console.log(`Максимальная цена: ${maxPriceInput.value}`);
}

// Сбросить фильтры
function resetFilters() {
    const categories = document.querySelectorAll('.category');
    categories.forEach(category => category.classList.remove('selected'));

    console.log('Фильтры сброшены');
}


