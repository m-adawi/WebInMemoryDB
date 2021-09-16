<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>WebInMemoryDB</title>

</head>
<body>
        <jsp:include page="/header.jsp" />
        <c:if test="${isAdmin}">
            <p><a href="management"> Manage users </a></p>
        </c:if>
        <c:if test="${isWriter}">
            <p><a href="write/insert"> Insert Records </a></p>
            <p><a href="write/delete"> Update Records </a></p>
            <p><a href="write/update"> Delete Records </a></p>
        </c:if>
        <p><a href="read/select"> Select Records </a></p>
        <p><a href="sql">SQL command</a></p>
        <jsp:include page="/footer.jsp" />
</body>
</html>