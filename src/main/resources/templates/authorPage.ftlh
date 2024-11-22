<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm
    </title>
</head>
<body>
<h>Author Page</h> <br><br>
<img src="/image/${image.getId()!}" height="100px" /> <br><br>

Email: ${author.getEmail()!} <br><br>
Номер телефона: ${author.getPhoneNumber()!} <br><br>
Дата регистрации на сайте: ${author.getRegistrationTime()!} <br><br>
Дата последней активности на сайте: ${author.getLastLogin()!} <br> <br>
Описание рода занятий:  <br><br>

<#if currentUser.isRegistered() && currentUser.getID() == author.getID()>
<form action = "/product/create" method="get">
    <input type = "submit" value = "Создать товар">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</#if>

Предлагаемые товары и услуги:
<#list listOfGoods as product>
<div>
    <p> <a href = "/product/${product.getID()}"> ${product.getName()} </a> </p>
</div>
</#list>

</body>
</html>