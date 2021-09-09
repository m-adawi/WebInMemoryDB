<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>${title}</title>

</head>
<body>
        <jsp:include page="/header.jsp" />
        <c:if test="${isAdmin}">
            <p><a href="management"> Manage users </a></p>
        </c:if>
        <p><a href="sql">SQL command</a></p>
        <jsp:include page="/footer.jsp" />
</body>
</html>