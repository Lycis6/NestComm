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

  <title>Каталог</title>
  
  <link href="/static/css/product-style.css" rel="stylesheet" />
  <script src="/static/js/product-script.js" type="text/javascript"></script>
</head>
<body>

<!-- Менюшка -->
<nav class="navbar">
  <a href="login" class="logo">ГНЕЗДО</a>
  <div class="menu">
    <a href="/login#faq">Вопросы и ответы</a>
    <a href="/login#about">О нас</a>
    <a href="/login#contacts">Контакты</a>
  </div>
    <#if !currentUser.isRegistered()>
        <button class="auth" id="auth" type="button" onclick="toggleModal()">Войти</button>
    </#if>
    <!-- Кнопка для возвращения в ЛК -->
    <#if currentUser.isRegistered()>
        <form action="/home" method="get">
            <button class="lk" id="lk" type="submit">
                <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M15.75 6a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0ZM4.501 20.118a7.5 7.5 0 0 1 14.998 0A17.933 17.933 0 0 1 12 21.75c-2.676 0-5.216-.584-7.499-1.632Z" />
                </svg>
            </button>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
        </form>
    </#if>
</nav>

<!-- Вход в личный аккаунт -->
<div class="modal" id="authModal">
  <div class="container" id="container">
    <svg class="close" id="close" width="18" height="18" viewBox="0 0 18 18" fill="none"
         xmlns="http://www.w3.org/2000/svg" onclick="toggleModal()"
         style="position: absolute; top: 1.1vw; right: 1.1vw; z-index: 9999;">
      <path fill-rule="evenodd" clip-rule="evenodd"
            d="M17.7728 1.32372C18.0756 1.0209 18.0756 0.529933 17.7728 0.227114C17.47 -0.0757047 16.979 -0.0757047 16.6762 0.227114L8.99974 7.90357L1.32372 0.227552C1.0209 -0.0752664 0.529933 -0.0752668 0.227114 0.227552C-0.0757048 0.530371 -0.0757044 1.02134 0.227114 1.32416L7.90313 9.00017L0.22714 16.6762C-0.0756778 16.979 -0.0756782 17.47 0.227141 17.7728C0.529959 18.0756 1.02093 18.0756 1.32374 17.7728L8.99974 10.0968L16.6762 17.7732C16.979 18.076 17.47 18.076 17.7728 17.7732C18.0756 17.4704 18.0756 16.9794 17.7728 16.6766L10.0963 9.00017L17.7728 1.32372Z"
            fill="#383874" fill-opacity="0.2" />
    </svg>

    <!-- Регистрация -->
    <div class="form-container sign-up-container">
      <form action="/user/registration/new" method="post">
        <h1>Регистрация</h1>
        <p>Чтобы создать учетную запись, пожалуйста, введите вашу почту и выберите пароль</p>
        <input type="text" name="email" placeholder="Email" required />
        <input type="password" name="password" placeholder="Password" required />
        <label class="material-checkbox">
          <input type="checkbox" name="isAuthor">
          <span class="checkmark"></span>
          Хочу разместить свои товары
        </label>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Зарегистрироваться</button>
      </form>
    </div>

    <!-- Авторизация -->
    <div class="form-container sign-in-container">
      <form th:action="@{/login}" method="post">
        <h1>Авторизация</h1>
        <p>Для входа необходимо ввести почту и пароль</p>
        <input type="text" name="username" id="email" placeholder="Username" required />
        <input type="password" name="password" id="password" placeholder="Password" required />
        <div class="toggle-container">
          <label class="switch">
            <input type="checkbox" id="toggle-password" onclick="togglePasswordVisibility()">
            <span class="slider"></span>
          </label>
          <span class="toggle-text">Показать пароль</span>
          <span class="toggle-forget-password">Забыли пароль?</span>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Вход</button>
      </form>
    </div>

    <!-- Окно с переходом регистрация/авторизация -->
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-panel overlay-left">
          <h1>Рады видеть вас!</h1>
          <p>Пожалуйста, войдите в свой аккаунт для доступа ко всем возможностям</p>
          <button class="ghost" id="signIn" onclick="showSignIn()">Авторизация</button>
        </div>
        <div class="overlay-panel overlay-right">
          <h1>Добро пожаловать!</h1>
          <p>Зарегистрируйтесь, чтобы не пропустить уникальные товары от мастеров</p>
          <button class="ghost" id="signUp" onclick="showSignUp()">Регистрация</button>
        </div>
      </div>
    </div>
  </div>
</div> 

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

    <!-- СТРОКА ПОИСКА -->
    <form action="/product/findByName" method="get" class="InputContainer">
        <button type="submit" class="labelforsearch" for="input">
            <svg class="searchIcon" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" role="img" aria-labelledby="search">
                <path stroke="currentColor" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" d="M7.667 12.667A5.333 5.333 0 107.667 2a5.333 5.333 0 000 10.667zM14.334 14l-2.9-2.9"></path>
            </svg>
        </button>
        <input
                placeholder="Поиск"
                id="input"
                class="input"
                name="name"
                type="text"
        />
        <button type="submit" class="clearButton" onclick="clearInput()">
            <svg class="micIcon" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" role="img" aria-labelledby="close">
                <path stroke="currentColor" stroke-width="1" stroke-linecap="round" stroke-linejoin="round" d="M6 6l8 8M6 14l8-8"></path>
            </svg>
        </button>
    </form>

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

<div class="path">
    <ul class="breadcrumbs">
        <li><a href="/login">Главная</a></li>
        <li><a href="/product">Каталог</a></li>
    </ul>
</div>

<!-- Каталог -->
<div class="catalog-page">
    <!-- Фильтр -->
    <form action="/product/findByCategoryAndPrice" method="get" >
    <aside class="catalog-sidebar">
        <div class="filters" style="padding: 10px 0px">
            <h3>Фильтр</h3>
    
            <!-- Фильтр по категориям -->
            <div class="filter-section">
                <h4>Категории</h4>
                <ul class="filter-category">
                    <li>
                        <div class="category" onclick="selectCategory(this)" data-category="Одежда">
                            Одежда
                            <input type="radio" name = "category" value="Одежда">
                        </div>
                    </li>
                    <li>
                        <div class="category" onclick="selectCategory(this)" data-category="Украшения">
                            Украшения
                            <input type="radio" name = "category" value="Украшения">
                        </div>
                    </li>
                    <li>
                        <div class="category" onclick="selectCategory(this)" data-category="Дом и интерьер">
                            Дом и интерьер
                            <input type="radio" name = "category" value="Дом и интерьер">
                        </div>
                    </li>
                    <li>
                        <div class="category" onclick="selectCategory(this)" data-category="Электроника">
                            Электроника
                            <input type="radio" name = "category" value="Электроника">
                        </div>
                    </li>
                    <li>
                        <div class="category" onclick="selectCategory(this)" data-category="Косметика">
                            Косметика
                            <input type="radio" name = "category" value="Косметика">
                        </div>
                    </li>
                    <li>
                        <div class="category" onclick="selectCategory(this)" data-category="Еда и напитки">
                            Еда и напитки
                            <input type="radio" name = "category" value="Еда и напитки">
                        </div>
                    </li>
                </ul>
            </div>
            
    
            <!-- Фильтр по цене -->

            <div class="filter-section">
                <h4>Цена</h4>
                <div class="filter-price">
                  <div class="input-price">
                    <input class="ds-input" data-action="minprice" data-limit="0.000000" type="text" name="minprice">
                    <div class="input-price__currency">₽</div>
                  </div>
                  <div class="input-price">
                    <input class="ds-input" data-action="maxprice" data-limit="200000.000000" type="text" name="maxprice">
                    <div class="input-price__currency">₽</div>
                  </div>
                </div>
                <div class="rangeslider">
                    <input class="min input-ranges" name="min" type="range" min="0" max="200000.000000" value="0">
                    <input class="max input-ranges" name="max" type="range" min="0" max="200000.000000" value="200000">
                </div>
            </div>


            <div class="filter-buttons">
                <button class="btn-apply" onclick="applyFilters()">Применить</button>
                <button class="btn-reset" onclick="resetFilters()" style="margin-bottom: 3px;">Сбросить</button>
            </div>

        </div>
    </aside>
    </form>
    <!-- Товары -->
    <main class="catalog-content">
      <#list listOfGoods as product>
        <!-- Карточка товара -->
        <div class="catalog-page-item">
            <div class="catalog-card" id="product.getID()">
              <div class="card-image">
                <a href="/product/${product.getID()}">
                  <img src="/image/${product.getPreviewImageId()!}">
                </a>
              </div>
              <div class="favorite-icon">
                <button class="buttonFavourite" onclick="addFavourite(this)">
                    <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="size-6" id="heart-icon">
                        <path strokeLinecap="round" strokeLinejoin="round" d="M21 8.25c0-2.485-2.099-4.5-4.688-4.5-1.935 0-3.597 1.126-4.312 2.733-.715-1.607-2.377-2.733-4.313-2.733C5.1 3.75 3 5.765 3 8.25c0 7.22 9 12 9 12s9-4.78 9-12Z" />
                    </svg>
                </button>
              </div>
              <div class="card-content">
                <p class="card-title"><a href = "/product/${product.getID()}">${product.getName()}</a></p>
                <p class="card-seller">${product.getUser().getName()!}</p>
                <p class="card-price">${product.getPrice()!} Руб</p>
                  <form action = "/basket/add/${product.getID()}" method="get">
                      <input type="hidden" name="_csrf" value="${_csrf.token}">
<#--                      <input type = "submit" value = "Добавить в корзину">-->
                      <button class="add-to-cart" onclick="addCart()">В корзину</button>
                  </form>
              </div>
            </div>
        </div>
      </#list>
    </main>
</div>


<div class="container-contact" id="contacts">
  <div>
      <h3 style="margin-top: 2.66vw;">Контакты</h3>
      <p>mail@mail.ru</p>
      <p>+7 999 999-99-99</p>
  </div>
  <div>
      <h3 style="margin-top: 2.66vw;">Навигация</h3>
      <div style="display: flex;">
          <a  href="/login">Главная</a>
          <a  href="" style="margin-left: 1.875vw;">Каталог</a>
          <a  href="workshop.html" style="margin-left: 1.875vw;">Мастер-классы</a>
          <a  href="/login#faq" style="margin-left: 1.875vw;">Вопросы и ответы</a>
          <a  href="/login#about" style="margin-left: 1.875vw;">О нас</a>
      </div>
  </div>
  <div>
      <p style="margin-top: 2.66vw;">Все права защищены</p>
      <p>Политика конфиденциальности</p>
      <div style="display: flex; align-items: center;">
          <p style="margin: 0vw; margin-right: 0.26vw;">Сайт разработан ФС 2.0</p>
      </div>
  </div>
</div>
</body>