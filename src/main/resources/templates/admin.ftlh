<!DOCTUPE html>
<html>
<head>
    <title>
        Users panel
    </title>
</head>
<body>
<h>Table</h>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Роли</th>
        <th>Статус</th>
        <th>Бан</th>
        <th>Подробнее</th>
    </tr>
    <#list users as user>
        <tr>
            <th>${user.email!}</th>
            <th>${user.getRoles()!}</th>
            <th>${user.isActive()!}</th>
            <#if user.getActive()>
            <th>
                <form action = "/admin/ban/${user.getEmail()}" method="get">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type = "submit" value = "Бан">
                </form>
            </th>
            </#if>
            <#if !user.getActive()>

                <th>
                    <form action = "/admin/unban/${user.getEmail()}" method="get">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type = "submit" value = "Разбан">
                    </form>
                </th>
            </#if>
            <th>
                <div>
                    <p> <a href = "/admin/user/${user.getEmail()}"> ${user.getEmail()} </a> </p>
                </div>
            </th>
        </tr>
    <#else>
        <h1>Пользователей нет</h1>
    </#list>
</table>
</body>
</html>