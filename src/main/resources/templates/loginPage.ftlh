<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm Login
    </title>
</head>

<body>
<h1>Login Here!</h1>
<form action = "/NestComm/validate" method="post">
    Логин: <input type = "text" name = "login" /> <br><br>
    Пароль: <input type = "text" name = "password" /> <br><br>
    <input type = "submit" value = "Войти">
</form>

<h1>Create an account!</h1>
<form action = "/NestComm/reg" method="post">
    <input type = "submit" value = "Зарегистрироваться">
</form>

</body>
</html>