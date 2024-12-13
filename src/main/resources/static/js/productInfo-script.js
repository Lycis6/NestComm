// Переключение между регистрацией/авторизацией
document.addEventListener('DOMContentLoaded', () => {
    var balanceElement = document.getElementById('product-balance');
    var balanceCount = parseInt(document.getElementById('balance-count').textContent);

    if (balanceCount >= 10) {
        balanceElement.textContent = 'Есть в наличии';
        balanceElement.style.backgroundColor = '#E1EB75';
    } else if (balanceCount > 0) {
        balanceElement.textContent = 'Осталось: ' + balanceCount;
        balanceElement.style.backgroundColor = '#FFB682';
    } else {
        balanceElement.textContent = 'Нет в наличии';
        balanceElement.style.backgroundColor = '#FF0000';
    }

    if (balanceCount <= 0) {
        balanceElement.style.color = '#555';
    } else {
        balanceElement.style.color = '#fff';
    }

    const images = document.querySelectorAll('.product-image'); 
    let currentIndex = 0; // Индекс текущего изображения
  
    // Если изображений нет, выходим из функции
    if (images.length === 0) return;
  
    // Функция для изменения изображения
    function changeImage(direction) {
      // Скрываем текущее изображение
      images[currentIndex].style.display = 'none';
    
      // Обновляем индекс текущего изображения
      currentIndex += direction;
  
      // Если текущий индекс выходит за пределы массива, обнуляем его
      if (currentIndex < 0) {
        currentIndex = images.length - 1;
      } else if (currentIndex >= images.length) {
        currentIndex = 0;
      }
  
      // Показываем новое изображение
      images[currentIndex].style.display = 'block';
    }
  
    // Инициализация: скрыть все изображения, кроме первого
    images.forEach((img, index) => {
      if (index !== 0) {
        img.style.display = 'none';
      }
    });
  
    // Привязка кнопок "prev" и "next" к функциям
    const prevButton = document.querySelector('.prev');
    const nextButton = document.querySelector('.next');
  
    if (prevButton) prevButton.addEventListener('click', () => changeImage(-1));
    if (nextButton) nextButton.addEventListener('click', () => changeImage(1));

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

function toggleCartStatus() {
    // Получаем кнопки
    var addToCartBtn = document.getElementById('add-to-cart');
    var removeFromCartBtn = document.getElementById('remove-from-cart');
    
    // Переключаем видимость кнопок
    if (addToCartBtn.style.display !== 'none') {
        addToCartBtn.style.display = 'none';
        removeFromCartBtn.style.display = 'inline-block'; // Показываем кнопку для удаления из корзины
    } else {
        addToCartBtn.style.display = 'inline-block';
        removeFromCartBtn.style.display = 'none'; // Показываем кнопку для добавления в корзину
    }
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


