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
        <p> <a href = "/login"> Зарегистрироваться </a> </p>
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

</body>
</html>
