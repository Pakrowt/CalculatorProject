<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=cp1251" %>

<!DOCTYPE html>
<html>
<head>
    <title>История вычислеий</title>
</head>

<body>
<h2>История вычислений: </h2> <br>

<blockquote>
    <table border="1">
        <thead>
        <td><b> resultId </b></td>
        <td><b> userId </b></td>
        <td><b> firstSide </b></td>
        <td><b> secondSide </b></td>
        <td><b> result </b></td>

        </thead>

        <c:forEach var="calculation" items="${calculations}">
            <tr>
                <td>${calculation.resultId} </td>
                <td>${calculation.userId} </td>
                <td>${calculation.firstSide} </td>
                <td>${calculation.secondSide} </td>
                <td>${calculation.result} </td>
                </form></td>
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
<p><input type="submit" value="Back to calculation"
          onclick="window.location='homePage.jsp'"/></p>


</body>
</html>