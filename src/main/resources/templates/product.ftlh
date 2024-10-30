<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm
    </title>
</head>
<body>
<#if !currentUser.isRegistered() >
    <div>
        <p> <a href = "/login"> Войти </a> </p>
    </div>
    <div>
        <p> <a href = "/user/registration"> Зарегистрироваться </a> </p>
    </div>
</#if>
<br><br>
<h>List of goods</h>
<br><br>
<form action = "/product/find" method="get">
    Поиск: <input type = "text" name = "name" />
    <input type = "submit" value = "Поиск">
</form>

<#list listOfGoods as product>
    <div>
        <p> <a href = "/product/${product.getID()}"> ${product.getName()} </a> </p>
    </div>
</#list>
<#if currentUser.isRegistered() && currentUser.isAuthor()>
<h1>Product create</h1>
<form action = "/product/add" method="post" enctype="multipart/form-data">
    Название товара: <input type = "text" name = "name" /> <br><br>
    Описание объявления: <input type = "text" name = "description" /> <br><br>
    Категория товара: <input type = "text" name = "category" /> <br><br>
    Цена: <input type = "number" name = "price" /> <br><br>
    Город: <input type = "text" name = "city" /> <br><br>
    Автор: <input type = "text" name = "author" /> <br><br>
    Первая фотография: <input type = "file" name = "file1" /> <br><br>
    Первая фотография: <input type = "file" name = "file2" /> <br><br>
    Первая фотография: <input type = "file" name = "file3" /> <br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type = "submit" value = "Добавить товар">
</form>
</#if>
</body>
</html>
