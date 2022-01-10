<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница авторизации</title>
</head>
<body>
<form action="validate" method="POST">
    <h2>Страница авторизации</h2>
    <p><b>Логин :</b>
        <input type="text" name="loginOut" size="30" required></p>
    <p><b>Пароль : </b>
        <input type="password" name="passwordOut" size="29" required></p>
    <button type="submit">Send</button>
</form>
</body>
</html>
