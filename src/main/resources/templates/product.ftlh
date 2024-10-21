<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm
    </title>
</head>
<body>
<h>List of goods</h>
<#list listOfGoods as product>
    <div>
        <p> <a href = "/product/${product.getID()}"> ${product.getName()} </a> </p>
    </div>
</#list>
<h1>Product create</h1>
<form action = "/product/add" method="post" enctype="multipart/form-data">
    Название товара: <input type = "text" name = "name" /> <br><br>
    Описание объявления: <input type = "text" name = "description" /> <br><br>
    Категория товара: <input type = "text" name = "category" /> <br><br>
    Цена: <input type = "number" name = "price" /> <br><br>
    Город: <input type = "text" name = "city" /> <br><br>
    Автор: <input type = "text" name = "author" /> <br><br>
    Первая фотография: <input type = "file" name = "file1" /> <br><br>
    Первая фотография: <input type = "file" name = "file2" /> <br><br>
    Первая фотография: <input type = "file" name = "file3" /> <br><br>
    <input type = "submit" value = "Добавить товар">
</form>
</body>
</html>
