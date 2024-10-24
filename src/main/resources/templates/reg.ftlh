<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm Registration
    </title>
</head>
<body>


<h1>User registration</h1>
<form action = "/NestComm/reg/newUser" method="post">
    Логин (email): <input type = "text" name = "email" /> <br><br>
    Пароль: <input type = "text" name = "password" /> <br><br>
    Автор: <input type = "checkbox" name = "isAuthor" /> <br><br>
    Пользователь: <input type = "checkbox" name = "isUser" /> <br><br>
    <input type = "submit" value = "Зарегистрироваться!">
</form>
</body>
</html>
