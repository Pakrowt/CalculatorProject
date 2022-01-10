<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<blockquote>
    <table border="1">
        <thead>
        <td><b> userId </b></td>
        <td><b> firstSide </b></td>
        <td><b> secondSide </b></td>
        <td><b> result </b></td>
        <td><b> . </b></td>
        </thead>

        <c:forEach var="calculation" items="${calculations}">
            <tr>
                <td>${calculation.userId} </td>
                <td>${calculation.firstSide} </td>
                <td>${calculation.secondSide} </td>
                <td>${calculation.result} </td>
                </form></td>
            </tr>
        </c:forEach>

    </table>
</blockquote>

</body>
</html>
