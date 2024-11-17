<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm
    </title>
</head>
<body>
<h>Password Change</h> <br><br>
Пароль должен соответствовать схеме: хотя бы одна заглавная и строчная буквы, хотя бы одна цифра <br> <br>
<form action = "/user/passwordChange/new" method="post">
    <input type="password" name="password"  placeholder="Password" required /> <br><br>
    <input type="password" name="passwordCheck"  placeholder="Password" required /><br><br>
    <input type = "submit" value = "Обновить пароль">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>

${errors!}

</body>
</html>