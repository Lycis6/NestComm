// Переключение между вкладками
document.addEventListener('DOMContentLoaded', () => {
    const tabButtons = document.querySelectorAll('.tab-btn');
    const tabPanels = document.querySelectorAll('.tab-panel');

    tabButtons.forEach(button => {
        button.addEventListener('click', () => {
            // Удаляем активный класс у всех кнопок
            tabButtons.forEach(btn => btn.classList.remove('active'));
            // Добавляем активный класс на текущую кнопку
            button.classList.add('active');

            // Скрываем все панели
            tabPanels.forEach(panel => panel.classList.remove('active'));
            // Показываем соответствующую панель
            const targetTab = document.getElementById(button.dataset.tab);
            targetTab.classList.add('active');
        });
    });

    document.querySelectorAll('.password-control').forEach((control) => {
      control.addEventListener('click', (event) => {
          event.preventDefault();
          const input = control.previousElementSibling;
          if (input.type === 'password') {
              input.type = 'text';
              control.classList.add('view');
          } else {
              input.type = 'password';
              control.classList.remove('view');
          }
      });
    });

    document.getElementById('phoneNumber').addEventListener('focus', function () {
      if (!this.value) {
          this.value = '+7 ';
      }
    });

    document.getElementById('phoneNumber').addEventListener('input', function (e) {
        let input = e.target.value.replace(/\D/g, '');
        let formatted = '+7';
    
        if (input.length > 1) {
            formatted += ' (' + input.slice(1, 4);
        }
        if (input.length > 4) {
            formatted += ') ' + input.slice(4, 7);
        }
        if (input.length > 7) {
            formatted += '-' + input.slice(7, 9);
        }
        if (input.length > 9) {
            formatted += '-' + input.slice(9, 11);
        }
    
        e.target.value = formatted;
    });

    document.getElementById('name').addEventListener('click', function() {
      document.getElementById('nameError').style.display = 'none';
    });

    document.getElementById('surname').addEventListener('click', function() {
      document.getElementById('surnameError').style.display = 'none';
    });

    document.getElementById('email').addEventListener('click', function() {
      document.getElementById('emailError').style.display = 'none';
    });

    document.getElementById('phoneNumber').addEventListener('click', function() {
      document.getElementById('phoneError').style.display = 'none';
    });
});

function checkPasswords() {
  const passwordInput = document.getElementById('passwordNew');  // Получаем поле для нового пароля
  const passwordCheckInput = document.getElementById('passwordCheck');  // Получаем поле для подтверждения пароля
  const errorMessage = document.getElementById('error-message');  // Получаем элемент для ошибки

  // Получаем значения из полей
  const password = passwordInput.value;
  const passwordCheck = passwordCheckInput.value;

  // Проверяем, чтобы оба поля были заполнены
  if (password === "" || passwordCheck === "") {
      errorMessage.textContent = "Пожалуйста, заполните оба поля пароля";  // Сообщение, если одно из полей пустое
      errorMessage.style.display = 'block';  // Показываем сообщение об ошибке
      return;  // Выход из функции, чтобы не продолжать проверку паролей
  }

  // Проверяем, совпадают ли пароли
  if (password !== passwordCheck) {
      errorMessage.textContent = "Пароли не совпадают";  // Сообщение об ошибке
      errorMessage.style.display = 'block';  // Показываем сообщение об ошибке
  } else {
      // Если пароли совпадают, скрываем ошибку и отправляем форму
      errorMessage.style.display = 'none';  // Скрываем сообщение об ошибке

      // Отправляем форму
      document.getElementById('passwordForm').submit();
  }
}


function updateFileName() {
    var fileInput = document.getElementById("file");
    var fileName = document.getElementById("file-name");
  
    if (fileInput.files.length > 0) {
      var selectedFileName = fileInput.files[0].name;
      if (selectedFileName.length > 10) {
        fileName.textContent = selectedFileName.slice(0, 10) + '...' + selectedFileName.slice(-6);
      } else {
        fileName.textContent = selectedFileName;
      }
    } else {
      fileName.textContent = "Файл не выбран";
    }
}

function submitForm(event) {
  event.preventDefault();

  const form = document.getElementById('updateForm');
  
  const name = document.getElementById('name').value;
  const surname = document.getElementById('surname').value;
  const email = document.getElementById('email').value;
  const phoneNumber = document.getElementById('phoneNumber').value;

  const nameError = document.getElementById('nameError');
  const surnameError = document.getElementById('surnameError');
  const emailError = document.getElementById('emailError');
  const phoneError = document.getElementById('phoneError');

  // Флаг для проверки ошибок
  let formValid = true;

  if (name === "") {
    nameError.textContent = "Поле не может быть пустым";
    nameError.style.display = 'block';
    formValid = false;
  } else {
    nameError.style.display = 'none';
  }

  if (surname === "") {
    surnameError.textContent = "Поле не может быть пустым";
    surnameError.style.display = 'block';
    formValid = false;
  } else {
    surnameError.style.display = 'none';
  }

  if (email === "") {
    emailError.textContent = "Поле не может быть пустым";
    emailError.style.display = 'block';
    formValid = false;
  } else {
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!emailPattern.test(email)) {
      emailError.textContent = "Введите корректный Email";
      emailError.style.display = 'block';
      formValid = false;
    } else {
      emailError.style.display = 'none';
    }
  }

  if (phoneNumber === "") {
    phoneError.textContent = "Поле не может быть пустым";
    phoneError.style.display = 'block';
    formValid = false;
  } else {
    const phonePattern = /^\+7 \(\d{3}\) \d{3}-\d{2}-\d{2}$/;
    if (!phonePattern.test(phoneNumber)) {
      phoneError.textContent = "Введите корректный номер телефона";
      phoneError.style.display = 'block';
      formValid = false;
    } else {
      phoneError.style.display = 'none';
    }
  }

  if (formValid) {
    form.submit();
  }
}

