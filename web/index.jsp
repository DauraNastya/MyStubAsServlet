<%@ page import="ru.appline.logic.Model" %>
<%--
  Created by IntelliJ IDEA.
  User: Nasty
  Date: 25.03.2024
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>Домашняя страница по работе с пользователями</h1>
Введите ID пользователя (0 - для вывода всего списка пользователей)
<br/>
Доступно: <%
    Model model = Model.getInstance();
    out.print(model.getFromList().size());
%>
<br/>
<form method="get" action="get">
    <label>ID:
        <input type="text" name="id"><br/>
    </label>
    <br/>
    <button type="submit">Поиск</button>
</form>
<br/>
<a href="addUser.html">Создать нового пользователя</a>
<br/>
<br/>
<a href="putUser.html">Изменить данные отдельного пользователя</a>
<br/>
<br/>
<a href="deleteUser.html">Удалить пользователя по ID</a>
</body>
<head>
    <title>Title</title>
</head>
</html>
