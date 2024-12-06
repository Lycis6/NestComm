<!DOCTUPE html>
<html>
<head>
    <title>
        Корзина пользователя
    </title>
</head>
<body>
<h>Заказанные продукты</h> <br><br>
<#if products?? && counts??>
<#list products as product>
    <!-- Карточка товара -->
    ${product.getName()!} <br><br>
    <form action = "/basket/setAmount/${product.getID()!}" method="post">
        Количество: <input type = "number" name = "amount" required /> <br><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type = "submit" value = "Изменить количество">
    </form>
    <form action = "/basket/delete/${product.getID()!}" method="get">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type = "submit" value = "Удалить из корзины">
    </form>
</#list>
<#list counts as count>
    <!-- Карточка товара -->
    ${count!}<br><br>
</#list>
</#if>
</body>
</html>