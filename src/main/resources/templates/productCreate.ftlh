<!DOCTUPE html>
<html>
<head>
    <title>
        NestComm
    </title>
</head>
<body>
<h1>Product create</h1>
<form action = "/product/create/new" method="post" enctype="multipart/form-data">
    Название товара: <input type = "text" name = "name" required/> <br><br>
    Описание объявления: <input type = "text" name = "description"  required/> <br><br>
    Категория товара: <input type = "text" name = "category" required/> <br><br>
    Цена: <input type = "number" name = "price" required /> <br><br>
    Город: <input type = "text" name = "city" required /> <br><br>
    Первая фотография: <input type = "file" name = "file1"  /> <br><br>
    Первая фотография: <input type = "file" name = "file2" /> <br><br>
    Первая фотография: <input type = "file" name = "file3" /> <br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type = "submit" value = "Добавить товар">
</form>

</body>
</html>