<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>User's home page</h1>
<img src="/image/${(image.getId())!"default text"}" height="60px" /> <br><br>
Имя: ${currentUser.getName()!} <br><br>
Фамилия: ${currentUser.getSurname()!} <br><br>
Email: ${currentUser.getEmail()!} <br><br>
Номер телефона: ${currentUser.getPhoneNumber()!} <br><br>

<#if currentUser.isAdmin()>
 <a href = "/admin"> Панель администратора</a>
</#if>

<#if !currentUser.isAdmin()>
<h1>Обновить данные поьзователя</h1>
<form action = "/user/update" method="post" enctype="multipart/form-data">
    Автатар: <input type = "file" name = "file" /> <br><br>
    Имя: <input type = "text" name = "name" /> <br><br>
    Фамилия: <input type = "text" name = "surname" /> <br><br>
    Номер телефона: <input type = "text" name = "phoneNumber" /> <br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type = "submit" value = "Обновить данные">
</form>
</#if>
<form action = "/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type = "submit" value = "Выйти">
</form>

</body>
</html>