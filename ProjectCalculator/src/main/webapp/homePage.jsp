<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<%@include file="logout.jsp" %>
<h2>
    Здравствуйте, <%=application.getAttribute("name")%> ! Вы вошли в личный кабинет</h2>
<p>
<h2><a href="areaform.jsp">Рассчитать плошадь прямоугольника</a></h2>
<p>
<h2><a href="perimeterForm.html">Рассчитать периметер прямоугольника </a></h2>
<h4>В данный момент число онлайн-пользователей: <%=application.getAttribute("count") %>
</h4>
<body>
<h1> Калькулятор для расчета площади прямоугольника </h1>
<form action="area" method="POST">
    <p><b>Первая сторона: </b><br>
        <input type="text" required placeholder="first side" name="firstSide"></p>
    <p><b>Вторая сторона: </b><br>
        <input type="text" required placeholder="second side" name="secondSide"></p>
    <p><input class="button" type="submit" value="Confirm"></p>
</form>

<a href="<c:url value="/data"/>">История вычислений</a>

<h2>История вычислений : </h2> <br>

<blockquote>
    <table border="1">
        <thead>
        <td><b> resultId </b></td>
        <td><b> userId </b></td>
        <td><b> firstSide </b></td>
        <td><b> secondSide </b></td>
        <td><b> result </b></td>
        <td><b> delete </b></td>
        <td>
            <form method="POST" action='<c:url value="/deleteAll" />' style="display:inline;">
                <input type="hidden" name="userId" value="${calculation.userId}">
                <input type="submit" value="Delete All">
            </form>
        </td>
        </thead>

        <c:forEach var="calculation" items="${calculations}">
            <tr>
                <td>${calculation.resultId} </td>
                <td>${calculation.userId} </td>
                <td>${calculation.firstSide} </td>
                <td>${calculation.secondSide} </td>
                <td>${calculation.result} </td>
                <td>
                    <form method="POST" action='<c:url value="/delete" />' style="display:inline;">
                        <input type="hidden" name="resultId" value="${calculation.resultId}">
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
</blockquote>

</body>
</html>
