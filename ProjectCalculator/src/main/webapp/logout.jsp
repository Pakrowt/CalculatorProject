<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <form action="${pageContext.request.contextPath}/logout" method="POST">
        <button type="submit">Logout</button>
    </form>
</div>

