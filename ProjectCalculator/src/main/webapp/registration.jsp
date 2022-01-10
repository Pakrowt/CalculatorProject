<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница регистрации</title>
</head>
<body>
<form action="reg" method="POST">
    <h2>Страница регистрации</h2>
    <p><b>Ваше имя: </b> <br>
        <input type="text" name="name" size="30"></p>
    <p><b>Логин: </b><br>
        <input type="text" name="login" size="30"></p>
    <p><b>Пароль: </b><br>
        <input type="password" name="password" size="30"></p>
    <p><input type="submit" value="OK"
              onclick="window.location='login.jsp'"/></p>
</form>
</body>
</html>
