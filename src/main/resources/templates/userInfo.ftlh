<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm
    </title>
</head>
<body>
<h>User info</h> <br><br>

Email: ${user.getEmail()!} <br><br>
Имя: ${user.getName()!} <br><br>
Фамилия: ${user.getSurname()!} <br><br>
Номер телефона: ${user.getPhoneNumber()!} <br><br>
Дата регистрации: ${user.getRegistrationTime()!} <br><br>
Дата последней авторизации: ${user.getLastLogin()!} <br><br>
Роли: ${user.getRoles()!} <br><br>
Активность: ${user.isActive()!} <br><br>

</body>
</html>