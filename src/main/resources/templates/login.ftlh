<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org">

<head>
  <title>Please Log In</title>

  <link href="/static/css/main.css" rel="stylesheet" />
</head>

<body>
<h1>Please Log In</h1>
<form th:action="@{/login}" method="post">
  <div>
    <input type="text" name="username" placeholder="Username"/>
  </div>
  <div>
    <input type="password" name="password" placeholder="Password"/>
  </div>
  <input type="submit" value="Log in" />
  <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>

<form action = "/user/registration" method="get">
  <input type = "submit" value = "Зарегистрироваться">
</form>
${error!} <br><br>


</body>
</html>