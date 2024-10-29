<!DOCTUPE html>
<html>
<head>
  <title>
    NestComm
  </title>
</head>
<body>
<h>Product info</h> <br><br>
<#list images as image>
    <img src="/image/${image.getId()?if_exists}" height="60px" /> <br><br>
</#list>
  Название товара: ${product.getName()!} <br><br>
  Описание объявления: ${product.getDescription()!} <br><br>
  Категория товара: ${product.getCategory()!} <br><br>
  Цена: ${product.getPrice()!} <br><br>
  Город: ${product.getCity()!} <br><br>
User ID: ${authorId!} <br><br>
<p> <a href = "/author/${product.getUser().getEmail()!}">  ${product.getUser().getEmail()!} </a> </p> <br><br>
  <#if currentUser.getUser().getID() == product.getUser().getID()>
<form action = "/product/delete/${product.getID()}" method="post">
  <input type="hidden" name="_csrf" value="${_csrf.token}">
  <input type = "submit" value = "Удалить товар">
  </#if>
</form>
</body>
</html>