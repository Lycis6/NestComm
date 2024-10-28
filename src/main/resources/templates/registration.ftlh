<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm Registration
    </title>
</head>
<body>


<h1>User registration</h1>
<form action = "/user/registration/new" method="post">
    Логин (email): <input type = "text" name = "email" /> <br><br>
    Пароль: <input type = "text" name = "password" /> <br><br>
    Автор: <input type = "checkbox" name = "isAuthor" /> <br><br>
    Пользователь: <input type = "checkbox" name = "isUser" /> <br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type = "submit" value = "Зарегистрироваться!">
</form>
</body>
</html>
