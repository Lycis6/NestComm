<!DOCTYPE html>
<html
        lang="en"
        xmlns:th="http://www.thymeleaf.org"
        class="h-full bg-white">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="referrer" content="no-referrer" />
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Rubik:wght@400;700&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Rubik+Bubbles&display=swap" rel="stylesheet">

  <title>Личный кабинет</title>
  
  <link href="/static/css/home-style.css" rel="stylesheet" />
  <script src="/static/js/home-script.js" type="text/javascript"></script>
</head>
<body>

<!-- Менюшка -->
<nav class="navbar">
  <a href="/login" class="logo">ГНЕЗДО</a>
  <div class="menu">
    <a href="/login#faq">Вопросы и ответы</a>
    <a href="/login#about">О нас</a>
    <a href="/login#contacts">Контакты</a>
  </div>
  <!-- Кнопка для входа в ЛК -->
  <form action="/home" method="get">
    <button class="lk" id="lk" type="submit">
        <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
            <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
        </svg>
    </button>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
  </form>
</nav>

<div class="container-2" id="container-2">
  <form action="/product">
    <button class="catalog" id="catalog">
      <svg class="catalogIcon" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 -1 22 23" fill="none" role="img" aria-labelledby="gridIcon">
        <rect width="8" height="8" x="0" y="0" rx="2" ry="2" stroke="currentColor" stroke-width="1"></rect>
        <rect width="8" height="8" x="12" y="0" rx="2" ry="2" stroke="currentColor" stroke-width="1"></rect>
        <rect width="8" height="8" x="0" y="12" rx="2" ry="2" stroke="currentColor" stroke-width="1"></rect>
        <rect width="8" height="8" x="12" y="12" rx="2" ry="2" stroke="currentColor" stroke-width="1"></rect>
      </svg>
      <span>Каталог</span>
    </button>
  </form>

  <div class="buttonsRight">
    <button class="buttonRight" id="favouriteProduct" type="button" onclick="goFavourite()">
      <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="none" viewBox="0 0 24 24" stroke="currentColor" className="size-6">
        <path strokeLinecap="round" strokeLinejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
      </svg>
    </button>
    <button class="buttonRight" id="shopCart" type="button" onclick="goCart()">
      <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="none" viewBox="0 0 24 24" stroke="currentColor" className="size-6">
        <path strokeLinecap="round" strokeLinejoin="round" d="M15.75 10.5V6a3.75 3.75 0 1 0-7.5 0v4.5m11.356-1.993 1.263 12c.07.665-.45 1.243-1.119 1.243H4.25a1.125 1.125 0 0 1-1.12-1.243l1.264-12A1.125 1.125 0 0 1 5.513 7.5h12.974c.576 0 1.059.435 1.119 1.007ZM8.625 10.5a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Zm7.5 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0Z" />
      </svg>
    </button>
    <button class="buttonRight" id="masterClass" type="button" onclick="goClass()">
      <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
        <path strokeLinecap="round" strokeLinejoin="round" d="M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 0 1 2.25-2.25h13.5A2.25 2.25 0 0 1 21 7.5v11.25m-18 0A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75m-18 0v-7.5A2.25 2.25 0 0 1 5.25 9h13.5A2.25 2.25 0 0 1 21 11.25v7.5m-9-6h.008v.008H12v-.008ZM12 15h.008v.008H12V15Zm0 2.25h.008v.008H12v-.008ZM9.75 15h.008v.008H9.75V15Zm0 2.25h.008v.008H9.75v-.008ZM7.5 15h.008v.008H7.5V15Zm0 2.25h.008v.008H7.5v-.008Zm6.75-4.5h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008V15Zm0 2.25h.008v.008h-.008v-.008Zm2.25-4.5h.008v.008H16.5v-.008Zm0 2.25h.008v.008H16.5V15Z" />
      </svg>
    </button>
  </div>
</div>

<div class="path">
    <ul class="breadcrumbs">
        <li><a href="/login">Вернуться на главную</a></li>
    </ul>
</div>

<div class="container-info">

    <!-- Фильтр -->
    <aside class="info-sidebar">
        <div class="personal-info" style="padding: 10px 0px">
            <div class="avatar-image">
                <#if image?? >
                     <img src="/image/${(image.getId())!}" height="60px" /> <br><br>
                 <#else>
                     <img src="../static/images/main.jpg">
                </#if>
            </div>

            <div class="info-name">
                <p style="cursor: default;">${currentUser.getName()!} ${currentUser.getSurname()!}</p>
            </div>

            <div class="info-contact">
                <p style="cursor: default;">Email: ${currentUser.getEmail()!}</p>
                <p style="cursor: default;">Номер телефона: ${currentUser.getPhoneNumber()!}</p>
            </div>
            
            <form action="/logout" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type="submit" class="logout-btn">Выйти</button>
            </form>
        </div>
    </aside>

    <!-- Настройки -->
    <main class="info-add">
      <!-- Вкладки -->
      <div class="tabs">
          <button class="tab-btn active" data-tab="info">Общие настройки</button>
          <button class="tab-btn" data-tab="password">Смена пароля</button>
          <button class="tab-btn" data-tab="opportunity">Дополнительные возможности</button>
      </div>

      <div class="tab-content">
          <!-- Смена личной информации -->
          <div class="tab-panel active" id="info">
            <h3 style="cursor: default;">Изменить личные данные</h3>
            <form action="/user/update" method="post" class="form-grid" id="updateForm" enctype="multipart/form-data">
              <div class="form-group">
                <label for="name">Имя</label>
                <input type="text" name="name" id="name" required />
                <p id="nameError" class="error-message" style="font-size: 12px; color: red; display: none; position: absolute; top: 405px; left: 640px"></p>
              </div>
              <div class="form-group">
                <label for="surname">Фамилия</label>
                <input type="text" name="surname" id="surname" required />
                <p id="surnameError" class="error-message" style="font-size: 12px; color: red; display: none; position: absolute; top: 405px; left: 963px"></p>
              </div>
              <div class="form-group">
                <label for="email" style="margin-bottom: 6px;">Email</label>
                <input type="text" name="email" id="email" required />
                <p id="emailError" class="error-message" style="font-size: 12px; color: red; display: none; position: absolute; top: 487px; left: 640px"></p>
              </div>
              <div class="form-group">
                <label for="phoneNumber">Номер телефона</label>
                <input type="text" name="phoneNumber" id="phoneNumber" required />
                <p id="phoneError" class="error-message" style="font-size: 12px; color: red; display: none; position: absolute; top: 487px; left: 963px"></p>
              </div>
              <div class="file-upload">
                <label for="file" id="upload-btn" class="upload-btn btn-primary" style="margin-right: 10px;">Выбрать изображение</label>
                <span id="file-name">Файл не выбран</span>
                <input type="file" name="file" id="file" accept="image/*" style="display: none;" onchange="updateFileName()" />
              </div>
              <div class="save-info">
                <input type="submit" value="Сохранить изменения" class="save-btn" onclick="submitForm(event)" />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
              </div>
            </form>
          </div>          

          <!-- Смена пароля -->
          <div class="tab-panel" id="password">
            <h3 style="cursor: default;">Изменить пароль</h3>
            <form action="/user/passwordChange/new" method="post" class="form-grid" id="passwordForm">
              <div class="form-group">
                  <label for="passwordNew">Новый пароль</label>
                  <div class="password-wrapper">
                      <input type="password" name="password" id="passwordNew" placeholder="Password" required
                          pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$" 
                          title="Пароль должен содержать хотя бы одну заглавную букву, одну строчную и одну цифру" />
                      <a href="#" class="password-control"></a>
                  </div>
              </div>
              <p class="password-info" style="cursor: default;">Пароль должен содержать хотя бы одну заглавную и одну строчную букву, а также хотя бы одну цифру</p>
              <div class="form-group" style="margin-bottom: 0px;">
                  <label for="passwordCheck" style="margin-bottom: 6px;">Подтвердить пароль</label>
                  <div class="password-wrapper">
                      <input type="password" name="passwordCheck" id="passwordCheck" placeholder="Confirm Password" required 
                          pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$" 
                          title="Пароль должен содержать хотя бы одну заглавную букву, одну строчную и одну цифру" />
                      <a href="#" class="password-control"></a>
                  </div>
              </div>
              <p id="error-message" style="font-size: 12px; color: red; display: none; position: absolute; top: 490px;">Пароль не совпадает</p>
              <div class="update-password" style="margin-top: 65px;">
                  <input type="button" value="Обновить пароль" class="update-btn" onclick="checkPasswords()" />
                  <input type="hidden" name="_csrf" value="${_csrf.token}" />
              </div>
            </form>
          </div>

          <!-- Дополнительные возможности -->
          <div class="tab-panel" id="opportunity">
            <#if currentUser.isAuthor()>
            <p style="cursor: default;">Перейдите в личный кабинет продавца, чтобы управлять магазином и товарными предложениями.</p>
            <form action = "/author/${currentUser.getEmail()!}" method="get">
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <input type = "submit" class="author-button" value = "Авторская страница">
            </form>
            </#if>

            <#if currentUser.isApproved() && !currentUser.isAuthor()>
            <p style="cursor: default;">Заполнив информацию, вы получите доступ к инструментам для продвижения ваших товаров и увеличения продаж. Начните свой путь с нами и откройте новые горизонты для бизнеса! <br><br>
              Станьте автором и получите доступ к эксклюзивным возможностям для роста. </p>
            <p style="cursor: default;"><strong>Как это работает:</strong> вы заполняете данные — мы помогаем вам продвигать товар, находить новых клиентов и увеличивать прибыль. <br><br>
              Не упустите шанс стать частью нашей успешной платформы.</p>
            <a style="text-decoration: none; color: rgba(238, 127, 126, 1); font-weight: bold;" href = "/user/became/author"> Стань автором и начни развивать свой бизнес!</a>
            <br><br>
            <p style="cursor: default;">Никаких рисков, все данные защищены, а поддержка доступна 24/7.</p>
            </#if>

            <#if currentUser.isAdmin()>
                <p style="cursor: default;">Для доступа к настройкам и управлению сайтом, перейдите в панель администратора.</p>
                <a style="text-decoration: none; color: rgba(238, 127, 126, 1); font-weight: bold;" href="/admin">Перейти в панель администратора</a>
            </#if>
          </div>
      </div>
  </main>
  
</div>

<!-- Контакты/подвал -->
<div class="container-contact" id="contacts">
  <div>
      <h3 style="margin-top: 2.66vw; cursor: default;">Контакты</h3>
      <p style="cursor: default;">mail@mail.ru</p>
      <p style="cursor: default;">+7 999 999-99-99</p>
  </div>
  <div>
      <h3 style="margin-top: 2.66vw; cursor: default;">Навигация</h3>
      <div style="display: flex;">
          <a  href="/login">Главная</a>
          <a  href="/product" style="margin-left: 1.875vw;">Каталог</a>
          <a  href="workshop.html" style="margin-left: 1.875vw;">Мастер-классы</a>
          <a  href="/login#faq" style="margin-left: 1.875vw;">Вопросы и ответы</a>
          <a  href="/login#about" style="margin-left: 1.875vw;">О нас</a>
      </div>
  </div>
  <div>
      <p style="margin-top: 2.66vw; cursor: default;">Все права защищены</p>
      <p style="cursor: default;">Политика конфиденциальности</p>
      <div style="display: flex; align-items: center;">
          <p style="margin: 0vw; margin-right: 0.26vw; cursor: default;">Сайт разработан ФС 2.0</p>
      </div>
  </div>
</div>
</body>
</html>