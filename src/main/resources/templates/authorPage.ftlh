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

Email: ${currentUser.getEmail()!} <br><br>
Номер телефона: ${currentUser.getPhoneNumber()!} <br><br>
Дата регистрации на сайте: ${currentUser.getRegistrationTime()!} <br><br>
Описание рода занятий:  <br><br>

Предлагаемые товары и услуги:
<#list listOfGoods as product>
    <div>
        <p> <a href = "/product/${product.getID()}"> ${product.getName()} </a> </p>
    </div>
</#list>

</body>
</html>